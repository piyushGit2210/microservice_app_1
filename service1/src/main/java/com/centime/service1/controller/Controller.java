package com.centime.service1.controller;

import com.centime.commonservice.dtos.NameRequest;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;

@RestController
@RequestMapping("/service1")
public class Controller {
    private final RestTemplate restTemplate;
    private final Logger logger = LoggerFactory.getLogger(Controller.class);

    @Value("${service2.url}")
    private String service2Url;

    @Value("${service3.url}")
    private String service3Url;

    public Controller(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        logger.info("TraceID: {}, GET /health", MDC.get("traceId"));
        return ResponseEntity.ok("Up");
    }

    @PostMapping("/concat")
    public ResponseEntity<String> concat(@RequestBody @Valid NameRequest json) {
        logger.info("TraceID: {}, POST /concat called", MDC.get("traceId"));

        // Call Service 2
        String hello = restTemplate.getForObject(service2Url + "/hello", String.class);

        // Call Service 3
        String fullName = restTemplate.postForObject(service3Url + "/print", json, String.class);

        return ResponseEntity.ok(hello + " " + fullName);
    }
}


