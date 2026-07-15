package com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.dto;
import com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.enums.Role;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class UserRequestDto {

    @NotBlank(message = "name is required")
    @Size(min=3,max=30,message = "name should be of length 3 to 30 characters")
    private String name;


    @Email
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank
    @Size(min=8, max=20)
    private String password;


    @NotNull
    @Pattern(regexp = "^[6-9]\\d{9}$", message = "Enter a valid 10-digit phone number")
    private String phoneNo;

    private Role role;
}
