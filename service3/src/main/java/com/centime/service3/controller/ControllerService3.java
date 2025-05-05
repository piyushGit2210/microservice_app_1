package com.centime.service3.controller;

import com.centime.commonservice.dtos.NameRequest;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/service3")
public class ControllerService3 {

    private final Logger logger = LoggerFactory.getLogger(ControllerService3.class);

    @PostMapping("/print")
    public ResponseEntity<String> printName(@RequestBody @Valid NameRequest request) {
        logger.info("TraceID: {}, POST /print called with {}", MDC.get("traceId"), request);
        return ResponseEntity.ok(request.getName()+ " " + request.getSurname());
    }

}
