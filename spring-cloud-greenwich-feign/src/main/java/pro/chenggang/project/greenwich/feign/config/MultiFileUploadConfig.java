package pro.chenggang.project.greenwich.feign.config;

import feign.Request;
import feign.Retryer;
import feign.codec.Encoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;

/**
 * @author chengg
 * @date 2018/8/29.
 */
@Configuration
public class MultiFileUploadConfig {

    public static int connectTimeOutMillis = 60000;
    public static int readTimeOutMillis = 120000;

    @Autowired
    private ObjectFactory<HttpMessageConverters> messageConverters;

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public Encoder feignFileEncoder() {
        return new MultiFileFormEncoder(new SpringEncoder(messageConverters));
    }

    @Primary
    @Bean
    public Retryer feignFileRetryer(){
        return Retryer.NEVER_RETRY;
    }

    @Bean
    public Request.Options feignFileOptions() {
        return new Request.Options(connectTimeOutMillis, readTimeOutMillis);
    }
}
