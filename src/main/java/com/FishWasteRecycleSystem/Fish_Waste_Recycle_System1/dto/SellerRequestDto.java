package com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SellerRequestDto {

    @NotBlank(message = "Shop name is required")
    @Size(min = 3, max = 100, message = "Shop name should be between 3 and 100 characters")
    private String shopName;

    //@NotNull(message = "Available fish waste quantity is required")
    @Positive(message = "Available fish waste quantity must be greater than 0")
    private Integer availableFishWasteKg;

    @NotBlank(message = "address is required")
    @Size(min = 2, max = 100, message = "Address should be between 2 and 100 characters")
    private String address;

    @NotNull(message = "User ID is required")
    @Positive(message = "User ID must be a positive number")
    private Long userId;
}
