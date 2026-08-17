package com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.service.impl;

import com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.dto.LoginRequestDto;
import com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.dto.LoginResponseDto;
import com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.entity.User;
import com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.exception.BadRequestException;
import com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.exception.UnauthorizedException;
import com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.repository.UserRepository;
import com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.service.AuthService;
import com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.service.JwtService;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public LoginResponseDto login(LoginRequestDto request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new UnauthorizedException("Invalid Email"));

        if (!passwordEncoder.matches(
                request.getPassword(),
                user.getPassword())) {

            throw new UnauthorizedException("Invalid Password");
        }

        String token = jwtService.generateToken(user.getEmail());

        return new LoginResponseDto(
                token,
                user.getRole().name(),
                user.getEmail()
        );
    }
}