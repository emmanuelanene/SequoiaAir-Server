package com.emmanuelanene.sequoia_air.dtos;

import lombok.Data;

import java.util.List;

@Data
public class LoginResponse {

    private String token;
    private List<String> roles;
}
