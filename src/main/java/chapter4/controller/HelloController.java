package chapter4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController{
    @RequestMapping("/hello.do")
    public String index_jsp(ModelMap model){
        model.addAttribute("yan", "yan你好");
        return "hello";
    }
}