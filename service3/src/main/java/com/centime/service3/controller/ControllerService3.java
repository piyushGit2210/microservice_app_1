package com.centime.service3.controller;

import com.centime.commonservice.dtos.NameRequest;
import com.centime.service3.service.AppendService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/service3")
public class ControllerService3 {


    private AppendService appendService;

    public ControllerService3(AppendService appendService) {
        this.appendService = appendService;
    }

    private final Logger logger = LoggerFactory.getLogger(ControllerService3.class);

    @PostMapping("/print")
    public ResponseEntity<String> printName(@RequestBody @Valid NameRequest request) {
        logger.info("TraceID: {}, POST /print called with {}", MDC.get("traceId"), request.toString());
        String response = appendService.getBody(request);
        return ResponseEntity.ok(response);
    }

}
