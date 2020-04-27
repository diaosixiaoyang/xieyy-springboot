package com.xieyy.boot.api;

import com.xieyy.boot.config.Singleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SingletonController {

    @Autowired
    private Singleton singleton;

    @GetMapping("/singleton")
    public String instance() {
        System.out.println(Singleton.getSingleton());
        System.out.println(singleton);
        return "success";
    }
}
