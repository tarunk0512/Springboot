package com.example.tarun.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class GetController {

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello, World!";
    }

        @GetMapping("/hello/auth")
        public String sayHelloAuth() {
            return "Hello, authenticated user!";
        }
    }

