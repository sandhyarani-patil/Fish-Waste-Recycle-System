package com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.dto;

import com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.enums.RequirementStatus;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequirementDto {

    private Long requirementId;

    private String wasteType;

    private Double quantity;

    private String location;

    private BigDecimal budget;

    private String description;

    private LocalDate requiredBefore;

    private RequirementStatus status;


    private LocalDateTime createdAt;

    private Long companyId;

    private String companyName;
}
