package com.emmanuelanene.sequoia_air.services;

import com.emmanuelanene.sequoia_air.dtos.LoginRequest;
import com.emmanuelanene.sequoia_air.dtos.LoginResponse;
import com.emmanuelanene.sequoia_air.dtos.RegistrationRequest;
import com.emmanuelanene.sequoia_air.dtos.Response;

public interface AuthService {

    Response<?> register(RegistrationRequest registrationRequest);
    Response<LoginResponse> login(LoginRequest loginRequest);
}
