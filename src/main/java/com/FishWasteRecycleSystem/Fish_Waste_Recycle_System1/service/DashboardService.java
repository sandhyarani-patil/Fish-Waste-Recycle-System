package com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.service;

import com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.dto.AdminDashboardDto;
import com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.dto.CompanyDashboardDto;
import com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.dto.SellerDashboardDto;

public interface DashboardService {

    SellerDashboardDto getSellerDashboard(Long sellerId);
    AdminDashboardDto getAdminDashboard();
    CompanyDashboardDto getCompanyDashboard(Long companyId);
}
