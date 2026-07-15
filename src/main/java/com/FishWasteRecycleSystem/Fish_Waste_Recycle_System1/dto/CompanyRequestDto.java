package com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyRequestDto {

    @NotBlank(message = "Company name is required")
    @Size(min = 2, max = 100, message = "Company name must be between 2 and 100 characters")
    private String companyName;


    @NotBlank(message = "Registration number is required")
    @Size(min = 3, max = 50, message = "Registration number must be between 3 and 50 characters")
    private String registrationNo;


    @NotBlank(message = "Address is required")
    private String address;


    @NotBlank(message = "Contact number is required")
    @Pattern(regexp = "^[0-9]{10}$", message = "Contact number must contain exactly 10 digits")
    private String contactNo;


    @NotNull(message = "Collection capacity is required")
    @Min(value = 1, message = "Collection capacity must be greater than 0")
    private Long collectionCapacityKg;


    @NotNull(message = "User id is required")
    private Long userId;
}