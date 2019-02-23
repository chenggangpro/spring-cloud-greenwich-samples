package pro.chenggang.project.greenwich.feign.controller;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import pro.chenggang.project.greenwich.feign.feign.EmailFeign;
import pro.chenggang.project.greenwich.feign.param.EmailParam;
import pro.chenggang.project.greenwich.feign.support.MockMultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

/**
 * @classDesc:
 * @author: chenggang
 * @createTime: 2019-02-18
 * @version: v1.0.0
 */
@RestController
@Slf4j
public class DemoController {

    @Autowired
    private EmailFeign emailFeign;

    @GetMapping("/demo")
    public String toDemo(){
        return "success";
    }

    @PostMapping("/test")
    public ResponseEntity toTest() throws Exception {
        File file1 = new File("/Users/evans/GitHub/logs/feign/feign.log");
        File file2 = new File("/Users/evans/GitHub/logs/provider/provider.log");
        InputStream inputStream1 = new FileInputStream(file1);
        InputStream inputStream2 = new FileInputStream(file2);
        MockMultipartFile mockMultipartFile1 = new MockMultipartFile("attachmentFiles","file1",inputStream1);
        MockMultipartFile mockMultipartFile2 = new MockMultipartFile("attachmentFiles","file2",inputStream2);
        MultipartFile[] multipartFiles = new MultipartFile[]{mockMultipartFile1,mockMultipartFile2};
        List<String> receiver = Lists.newArrayList("receiver1","receiver2","receiver3");
        List<String> ccReceiver = Lists.newArrayList("ccReceiver1","ccReceiver2","ccReceiver3");
        List<String> bccReceiver = Lists.newArrayList("bccReceiver1","bccReceiver2","bccReceiver3");
        EmailParam emailParam = new EmailParam();
        emailParam.setReceiver(receiver);
        emailParam.setCcReceiver(ccReceiver);
        emailParam.setBccReceiver(bccReceiver);
        emailParam.setSubject("Subject");
        emailParam.setMailContent("Content");
        emailParam.setAttachmentFiles(multipartFiles);
        ResponseEntity responseEntity = emailFeign.toEmail(emailParam);
        log.debug("[Test]Email::{},Response:{}",emailParam,responseEntity);
        return responseEntity;
    }
}
