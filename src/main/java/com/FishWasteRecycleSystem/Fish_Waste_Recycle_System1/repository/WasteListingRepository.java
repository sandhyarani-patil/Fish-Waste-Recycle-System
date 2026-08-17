package com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.repository;

import com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.entity.WasteListing;
import com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.enums.FishWasteStatus;

import org.springframework.data.jpa.repository.JpaRepository;


public interface WasteListingRepository extends JpaRepository<WasteListing,Long> {

    long countBySellerSellerId(Long sellerId);

    long countBySellerSellerIdAndStatus(Long sellerId, FishWasteStatus status);

    long countByStatus(FishWasteStatus status);


    }

