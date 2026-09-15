package com.stacksimplify.restservices.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class HelloWorldController {

    @Autowired
    private ResourceBundleMessageSource messageSource;

    @GetMapping("/hello-world")
    public String HelloWorld(){
        return "Hello World";
    }

    @GetMapping("/hello-world-bean")
    public UserDetails helloWorldBean() {
        return new UserDetails("Rohit", "Tiwari", "Bhopal");
    }

    @GetMapping("hello-int")
    public String getMessagesInI18Format(@RequestHeader(name = "accept-Language", required = false)
                                             String locale) {
        return messageSource.getMessage("label.hello",null, new Locale(locale));
    }

    @GetMapping("hello-int2")
    public String getMessagesInI18Format() {
        return messageSource.getMessage("label.hello",null, LocaleContextHolder.getLocale());
    }

}
