package com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequestDto {

    @NotNull(message = "Listing ID is required")
    @Positive(message = "Listing ID must be greater than 0")
    private Long listingId;

    @NotNull(message = "Requirement ID is required")
    @Positive(message = "Requirement ID must be greater than 0")
    private Long requirementId;

    @NotNull(message = "Order quantity is required")
    @Positive(message = "Order quantity must be greater than 0")
    private BigDecimal orderQuantity;

    @NotNull(message = "Pickup date is required")
    @FutureOrPresent(message = "Pickup date cannot be in the past")
    private LocalDate pickupDate;
}