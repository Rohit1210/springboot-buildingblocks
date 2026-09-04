package com.stacksimplify.restservices.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @GetMapping("/hello-world")
    public String HelloWorld(){
        return "Hello World";
    }

    @GetMapping("/hello-world-bean")
    public UserDetails helloWorldBean() {
        return new UserDetails("Rohit", "Tiwari", "Bhopal");
    }

}
