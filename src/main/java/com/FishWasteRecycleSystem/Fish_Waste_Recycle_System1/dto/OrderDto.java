package com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.dto;

import com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.entity.Requirement;
import com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.entity.WasteListing;
import com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.enums.OrderStatus;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

    private Long orderId;

    private Long wasteListingId;

    private Long requirementId;

    private BigDecimal orderQuantity;

    private BigDecimal totalAmount;

    private LocalDate pickupDate;

    private OrderStatus status;

    private LocalDateTime orderedAt;
}
