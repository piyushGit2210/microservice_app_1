package com.centime.service1.controller;

import com.centime.commonservice.dtos.NameRequest;
import com.centime.service1.service.ConcatService;
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

    private final ConcatService service;

    public Controller(ConcatService service) {
        this.service = service;
    }

    private final Logger logger = LoggerFactory.getLogger(Controller.class);

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        logger.info("TraceID: {}, GET /health", MDC.get("traceId"));
        return ResponseEntity.ok("Up");
    }

    @PostMapping("/concat")
    public ResponseEntity<String> concat(@RequestBody @Valid NameRequest json) {
        logger.info("TraceID: {}, POST /concat called", MDC.get("traceId"));

        String response = service.concatNameRequest(json);

        return ResponseEntity.ok(response);
    }
}


