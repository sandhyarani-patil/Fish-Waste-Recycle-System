package com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.dto;


import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequirementRequestDto {

    @NotBlank(message = "Waste type is required")
    @Size(min = 2, max = 50,
            message = "Waste type should be between 2 and 50 characters")
    private String wasteType;

    @NotNull(message = "Quantity is required")
    @Positive(message = "Quantity must be greater than 0")
    private Double quantity;

    @NotBlank(message = "Location is required")
    @Size(min = 3, max = 200, message = "Location must be between 3 and 200 characters")
    private String location;

    @NotNull(message = "Budget is required")
    @Positive(message = "Budget must be greater than 0")
    private BigDecimal budget;

    @Size(max = 500, message = "Description cannot exceed 500 characters")
    private String description;

    @FutureOrPresent(message = "Required before date cannot be in the past")
    private LocalDate requiredBefore;


    @NotNull(message = "Company ID is required")
    @Positive(message = "Company ID must be greater than 0")
    private Long companyId;
}