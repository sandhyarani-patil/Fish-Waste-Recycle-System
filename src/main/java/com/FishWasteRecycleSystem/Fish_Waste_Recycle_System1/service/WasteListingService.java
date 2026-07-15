package com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.service;

import com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.dto.CompanyDto;
import com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.dto.CompanyRequestDto;
import com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.dto.WasteListingDto;
import com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.dto.WasteListingRequestDto;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Map;

public interface WasteListingService {

    List<WasteListingDto> getAllListing();

    WasteListingDto getWasteListingById(Long wasteListingId);

    WasteListingDto createNewWasteListing(@Valid WasteListingRequestDto wastelistingRequestDto);


    void deleteWasteListingById(Long wasteListingId);

    WasteListingDto updateWasteListing(Long wasteListingId, @Valid WasteListingRequestDto wasteListingRequestDto);

    WasteListingDto updatePartialWasteListing(Long wasteListingId, Map<String, Object> updates);
}
