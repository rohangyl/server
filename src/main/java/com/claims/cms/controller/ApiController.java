package com.claims.cms.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {
    @GetMapping("/api-docs")
    public String apiDocs() {
        return "This is your API documentation. You can customize it as needed.";
    }
}
