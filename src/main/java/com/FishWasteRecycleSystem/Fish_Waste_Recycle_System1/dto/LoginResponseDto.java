package com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseDto {

    private String token;
    private String role;
    private String email;
}