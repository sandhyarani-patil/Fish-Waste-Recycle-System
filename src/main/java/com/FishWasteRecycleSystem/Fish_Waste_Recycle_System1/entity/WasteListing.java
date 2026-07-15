package com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.entity;

import com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.enums.FishWasteStatus;
import com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.enums.Unit;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "waste_listing")
public class WasteListing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long wasteListingId;
    private String fishType;
    private String wasteCategory;
    private BigDecimal quantity;

    @Enumerated(EnumType.STRING)
    private Unit unit;
    private BigDecimal pricePerKg;

    @Column(length = 1000)
    private String description;

    private String pickupLocation;
    private LocalDate availableDate;

    @Enumerated(EnumType.STRING)
    private FishWasteStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id")
    private Seller seller;


}
