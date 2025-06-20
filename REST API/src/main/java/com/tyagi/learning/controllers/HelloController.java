package com.tyagi.learning.controllers;



import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloController {

    @GetMapping(path = "/hello")
    public String getHome(){
        return "Hello World";
    }
}
