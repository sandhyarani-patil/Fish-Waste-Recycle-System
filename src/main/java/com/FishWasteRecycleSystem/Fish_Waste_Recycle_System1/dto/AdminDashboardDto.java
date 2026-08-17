package com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.dto;

import lombok.Data;

@Data
public class AdminDashboardDto {

    private Long totalSellers;

    private Long totalCompanies;

    private Long totalListings;

    private Long totalOrders;

    private Long totalRequirements;

    private Long availableListings;

    private Long reservedListings;

    private Long soldListings;
}