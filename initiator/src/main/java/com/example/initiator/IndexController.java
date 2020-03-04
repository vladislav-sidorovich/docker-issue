package com.example.initiator;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class IndexController {
    private Logger logger = LoggerFactory.getLogger(IndexController.class);


    @Value("${info.app.version:unknown}")
    private String version;

    @Value("${conf.local.rest.service.host}")
    private String localRestServiceHost;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping
    public Object index() {
        return Map.of("Hello", "Initiator version: " + version);
    }

    @GetMapping("/{path}")
    public Object call(@PathVariable("path") String path) {
        logger.info("Env DOCKER_CLIENT_TIMEOUT: {}", System.getenv("DOCKER_CLIENT_TIMEOUT"));
        logger.info("Env COMPOSE_HTTP_TIMEOUT: {}", System.getenv("COMPOSE_HTTP_TIMEOUT"));
        logger.info("Env DOCKER_TLS_VERIFY: {}", System.getenv("DOCKER_TLS_VERIFY"));

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> request = new HttpEntity<>(headers);
        ResponseEntity<String> exchange = restTemplate.exchange(localRestServiceHost + "/" + path, HttpMethod.GET, request, String.class);

        String body = exchange.getBody();
        logger.info("Response: {}", body);
        return Map.of("callResult", body);
    }
}
