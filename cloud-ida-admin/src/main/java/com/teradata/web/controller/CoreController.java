package com.teradata.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CoreController {

    @GetMapping("/index")
    public String index(){
        return "login";
    }


    @GetMapping("/page/{page}")
    public String jump(@PathVariable("page")String page) {
        return page;
    }
}
