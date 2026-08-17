package com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.dto;
import com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.enums.Role;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class UserRequestDto {

    @NotBlank(message = "name is required")
    @Size(min=3,max=30,message = "name should be of length 3 to 30 characters")
    private String name;


    @NotBlank(message = "Email is required")
    @Email(message = "Enter a valid email address")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 8, max = 20,
            message = "Password must be between 8 and 20 characters")
    private String password;


    @NotBlank(message = "Phone number is required")
    @Pattern(
            regexp = "^[6-9]\\d{9}$",
            message = "Enter a valid 10-digit phone number"
    )
    private String phoneNo;

    @NotNull(message = "Role is required")
    private Role role;
}
