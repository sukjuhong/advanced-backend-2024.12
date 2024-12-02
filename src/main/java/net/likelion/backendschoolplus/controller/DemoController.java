package net.likelion.backendschoolplus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DemoController {

    @GetMapping("/hello")
    @ResponseBody
    public String hello() {
        return "<h1>Hello World!</h1>";
    }
}
