package com.ved.CareerLaunch.service;


import com.ved.CareerLaunch.dto.response.AuthResponse;
import com.ved.CareerLaunch.dto.request.LoginRequest;
import com.ved.CareerLaunch.dto.request.RegisterRequest;

public interface AuthService {
    AuthResponse register(RegisterRequest request);
    AuthResponse login(LoginRequest request);
}