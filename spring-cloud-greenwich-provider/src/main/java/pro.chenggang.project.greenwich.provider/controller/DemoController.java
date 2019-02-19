package pro.chenggang.project.greenwich.provider.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @classDesc:
 * @author: chenggang
 * @createTime: 2019-02-18
 * @version: v1.0.0
 */
@RestController
public class DemoController {

    @GetMapping("/demo")
    public String toDemo(){
        return "success";
    }
}
