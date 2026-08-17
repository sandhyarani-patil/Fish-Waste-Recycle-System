package com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.service;

import com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.dto.LoginRequestDto;
import com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.dto.LoginResponseDto;

public interface AuthService {

    LoginResponseDto login(LoginRequestDto request);
}