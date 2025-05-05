package com.centime.commonservice.dtos;

import jakarta.validation.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;


@Validated
public class NameRequest {
    @NotBlank
    private String name;

    @NotBlank
    private String surname;

    public NameRequest(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
