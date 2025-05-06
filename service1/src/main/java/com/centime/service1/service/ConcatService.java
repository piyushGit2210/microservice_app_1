package com.centime.service1.service;

import com.centime.commonservice.dtos.NameRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ConcatService {
    private final RestTemplate restTemplate;

    public ConcatService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
//    public ConcatService(RestTemplateBuilder builder) {
//        this.restTemplate = builder.build();
//    }
    @Value("${service2.url}")
    private String service2Url;

    @Value("${service3.url}")
    private String service3Url;
    public String concatNameRequest(NameRequest request){
        //Call service 2
        String hello = restTemplate.getForObject(service2Url + "/hello", String.class);

        // Call Service 3
        String fullName = restTemplate.postForObject(service3Url + "/print", request, String.class);

        return hello+" "+ fullName;

    }
}
