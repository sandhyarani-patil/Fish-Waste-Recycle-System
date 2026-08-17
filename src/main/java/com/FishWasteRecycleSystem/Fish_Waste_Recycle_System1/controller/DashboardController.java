package com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.controller;

import com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.dto.AdminDashboardDto;
import com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.dto.CompanyDashboardDto;
import com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.dto.SellerDashboardDto;
import com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/dashboard")
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping("/seller/{sellerId}")
    public ResponseEntity<SellerDashboardDto> getSellerDashboard(
            @PathVariable Long sellerId) {

        return ResponseEntity.ok(
                dashboardService.getSellerDashboard(sellerId)
        );
    }

    @GetMapping("/admin")
    public ResponseEntity<AdminDashboardDto> getAdminDashboard() {

        return ResponseEntity.ok(
                dashboardService.getAdminDashboard()
        );
    }

    @GetMapping("/company/{companyId}")
    public ResponseEntity<CompanyDashboardDto> getCompanyDashboard(
            @PathVariable Long companyId) {

        return ResponseEntity.ok(
                dashboardService.getCompanyDashboard(companyId)
        );
    }
}