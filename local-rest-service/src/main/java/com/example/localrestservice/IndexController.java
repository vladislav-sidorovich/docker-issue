package com.example.localrestservice;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {
    @Value("${info.app.version:unknown}")
    private String version;


    @GetMapping
    public Object index() {
        return Map.of("Hello", "Initiator version: " + version);
    }

    @GetMapping("/{path}")
    public Object call(@PathVariable("path") String path) {
        return path + " and " + path;
    }
}