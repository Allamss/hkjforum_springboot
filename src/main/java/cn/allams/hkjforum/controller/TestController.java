package cn.allams.hkjforum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import sun.net.idn.Punycode;

@Controller
public class TestController {

    @RequestMapping("/hello")
    public String hello(){
        return "index";
    }



}
