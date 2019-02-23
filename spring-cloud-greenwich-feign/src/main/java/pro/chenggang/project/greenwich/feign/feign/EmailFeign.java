package pro.chenggang.project.greenwich.feign.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import pro.chenggang.project.greenwich.feign.config.MultiFileUploadConfig;
import pro.chenggang.project.greenwich.feign.param.EmailParam;

/**
 * @classDesc:
 * @author: chenggang
 * @createTime: 2019-02-23
 * @version: v1.0.0
 * @email: cg@choicesoft.com.cn
 */
@FeignClient(value = "provider",configuration = MultiFileUploadConfig.class)
public interface EmailFeign {

    @PostMapping(value = "/email",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE},consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity toEmail(@RequestBody EmailParam emailParam);
}
