package com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.controller;

import com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.dto.LoginRequestDto;
import com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.dto.LoginResponseDto;
import com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public LoginResponseDto login(@RequestBody LoginRequestDto request) {
        return authService.login(request);
    }
}