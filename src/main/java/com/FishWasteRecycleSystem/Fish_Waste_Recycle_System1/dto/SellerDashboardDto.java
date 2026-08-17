package com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.dto;

import lombok.Data;

@Data
public class SellerDashboardDto {

    private Long totalListings;

    private Long availableListings;

    private Long soldListings;

    private Long reservedListings;

    private Integer availableFishWasteKg;

    private Long totalOrders;
}
