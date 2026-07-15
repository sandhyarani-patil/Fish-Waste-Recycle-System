package com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.dto;


import com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.enums.FishWasteStatus;
import com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.enums.Unit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WasteListingDto {

    private Long id;
    private String fishType;
    private String wasteCategory;
    private BigDecimal quantity;
    private Unit unit;
    private BigDecimal pricePerKg;
    private String description;

    private String pickupLocation;
    private LocalDate availableDate;
    private FishWasteStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long sellerId;

}
