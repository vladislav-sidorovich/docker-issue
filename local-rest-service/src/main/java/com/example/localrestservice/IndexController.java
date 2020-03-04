package com.example.localrestservice;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {
    private Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Value("${info.app.version:unknown}")
    private String version;

    @GetMapping
    public Object index() {
        return Map.of("Hello", "Initiator version: " + version);
    }

    @GetMapping("/{path}")
    public Object call(@PathVariable("path") String path) throws Exception {
        logger.info("Request {}", path);
        Thread.sleep(TimeUnit.MINUTES.toMillis(7));
        logger.info("Request {} processed", path);
        return path + " and " + path;
    }
}