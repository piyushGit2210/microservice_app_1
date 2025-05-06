package com.centime.service3.service;

import com.centime.commonservice.dtos.NameRequest;
import org.springframework.stereotype.Service;

@Service
public class AppendService {
    public static String getBody(NameRequest request) {
        return request.getName() + " " + request.getSurname();
    }
}
