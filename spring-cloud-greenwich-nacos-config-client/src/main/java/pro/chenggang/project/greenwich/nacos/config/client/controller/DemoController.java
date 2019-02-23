package pro.chenggang.project.greenwich.nacos.config.client.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
