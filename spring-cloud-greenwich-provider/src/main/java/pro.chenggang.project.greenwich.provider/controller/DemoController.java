package pro.chenggang.project.greenwich.provider.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import pro.chenggang.project.greenwich.provider.param.EmailParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @classDesc:
 * @author: chenggang
 * @createTime: 2019-02-18
 * @version: v1.0.0
 */
@Slf4j
@RestController
public class DemoController {

    @GetMapping("/demo")
    public String toDemo(){
        return "success";
    }

    @PostMapping(value = "/email")
    public Object toEmail(@Validated EmailParam emailParam, @RequestPart MultipartFile[] attachmentFiles){
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        log.debug("[HttpHeader]:{}",request.getHeader(HttpHeaders.AUTHORIZATION));
        log.debug("[Email Param]:{}",emailParam);
        log.debug("[MultipartFile]:{}", Arrays.toString(attachmentFiles));
        Map<String, Object> result = new HashMap<>(2,1);
        result.put("param",emailParam);
        result.put("files",Arrays.toString(attachmentFiles));
        return result;
    }
}
