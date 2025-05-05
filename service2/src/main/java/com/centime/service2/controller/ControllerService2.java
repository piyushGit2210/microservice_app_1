package com.centime.service2.controller;

import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;


@RestController
@RequestMapping("/service2")
public class ControllerService2 {

    private final Logger logger = LoggerFactory.getLogger(ControllerService2.class);

    @GetMapping("/hello")
    public ResponseEntity<String> sayHello(){
        logger.info("Trace ID : {} GET /hello called", MDC.get("traceId"));
        return  ResponseEntity.ok("Hello");
    }

}
