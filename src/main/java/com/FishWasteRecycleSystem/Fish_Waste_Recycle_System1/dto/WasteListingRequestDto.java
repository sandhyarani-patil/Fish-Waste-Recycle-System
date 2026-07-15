package com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.dto;

import com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.enums.Unit;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class WasteListingRequestDto {

    @NotBlank(message = "Fish type is required")
    @Size(max = 50, message = "Fish type cannot exceed 50 characters")
    private String fishType;

    @NotBlank(message = "Waste category is required")
    @Size(max = 50, message = "Waste category cannot exceed 50 characters")
    private String wasteCategory;

    @NotNull(message = "Quantity is required")
    @DecimalMin(value = "0.1", inclusive = true, message = "Quantity must be greater than 0")
    private BigDecimal quantity;

    @NotNull(message = "Unit is required")
    private Unit unit;

    @NotNull(message = "Price per Kg is required")
    @DecimalMin(value = "1.0", inclusive = true, message = "Price must be greater than 0")
    private BigDecimal pricePerKg;

    @NotBlank(message = "Description is required")
    @Size(max = 500, message = "Description cannot exceed 500 characters")
    private String description;

    @NotBlank(message = "Pickup location is required")
    @Size(max = 100, message = "Pickup location cannot exceed 100 characters")
    private String pickupLocation;

    @NotNull(message = "Available date is required")
    @FutureOrPresent(message = "Available date cannot be in the past")
    private LocalDate availableDate;

    @NotNull(message = "Seller Id is required")
    @Positive(message = "Seller Id must be a positive number")
    private Long sellerId;
}