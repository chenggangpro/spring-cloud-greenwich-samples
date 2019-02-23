package pro.chenggang.project.greenwich.feign.config;

import com.alibaba.fastjson.JSON;
import feign.RequestTemplate;
import feign.codec.EncodeException;
import feign.codec.Encoder;
import feign.form.ContentType;
import feign.form.FormEncoder;
import feign.form.MultipartFormContentProcessor;
import feign.form.spring.SpringManyMultipartFilesWriter;
import feign.form.spring.SpringSingleMultipartFileWriter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import pro.chenggang.project.greenwich.feign.param.FeignFileSymbol;
import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.LinkedHashMap;

/**
 * @author chengg
 * @date 2018/8/29.
 */
public class MultiFileFormEncoder extends FormEncoder {

    public MultiFileFormEncoder() {
        this(new Encoder.Default());
    }

    public MultiFileFormEncoder(Encoder delegate) {
        super(delegate);
        MultipartFormContentProcessor processor = (MultipartFormContentProcessor) getContentProcessor(ContentType.MULTIPART);
        processor.addFirstWriter(new SpringSingleMultipartFileWriter());
        processor.addFirstWriter(new SpringManyMultipartFilesWriter());
    }

    @Override
    public void encode(Object object, Type bodyType, RequestTemplate template) throws EncodeException {
        if(! (bodyType instanceof Class)){
            super.encode(object, bodyType, template);
            return;
        }
        Class bodyClass = (Class) bodyType;
        if(!isTargetClassSubClass(bodyClass,FeignFileSymbol.class)){
            super.encode(object, bodyType, template);
            return;
        }
        Field[] fields = bodyClass.getDeclaredFields();
        LinkedHashMap data = new LinkedHashMap(fields.length,1);
        MultipartFile file;
        MultipartFile[] files;
        String temp;
        try{
            for(Field field : fields){
                field.setAccessible(true);
                if(field.getType().equals(MultipartFile.class)){
                    Object o = field.get(object);
                    if(null != o){
                        file = (MultipartFile) o;
                        data.put(file.getName(),o);
                    }
                }else if(field.getType().equals(MultipartFile[].class)){
                    Object o = field.get(object);
                    if(null != o){
                        data.put(field.getName(),o);
                    }
                }else if(isTargetClassSubClass(field.getType(),Collection.class)){
                    Object o = field.get(object);
                    if(null != o){
                        temp = JSON.toJSONString(o);
                        temp = StringUtils.substringBetween(temp,"[","]");
                        data.put(field.getName(),temp);
                    }
                }else{
                    Object o = field.get(object);
                    if(null != o){
                        data.put(field.getName(),o);
                    }
                }
            }
        }catch (IllegalAccessException e) {}
        super.encode(data, MAP_STRING_WILDCARD, template);

    }

    private boolean isTargetClassSubClass(Class bodyClass,Class targetClass){
        Type[] genericInterfaces = bodyClass.getGenericInterfaces();
        for(Type type : genericInterfaces){
            if(type instanceof ParameterizedTypeImpl && ((ParameterizedTypeImpl) type).getRawType().equals(targetClass)){
                return true;
            }
            if(type.equals(targetClass)){
                return true;
            }
        }
        return false;
    }
}
