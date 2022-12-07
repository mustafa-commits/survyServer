package com.postsurvey.demo.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class mainController {


    @ResponseBody
    @CrossOrigin
    @GetMapping("/V1/hello")
    public String getmessage() {
        System.out.println("request");
        return "connected_to";
    }



}
