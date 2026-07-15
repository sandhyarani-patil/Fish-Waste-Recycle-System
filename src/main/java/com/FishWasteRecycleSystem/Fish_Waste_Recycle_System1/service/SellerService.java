package com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.service;

import com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.dto.SellerDto;
import com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.dto.SellerRequestDto;
import jakarta.validation.Valid;


import java.util.List;
import java.util.Map;


public interface SellerService {

    List<SellerDto> getAllSellers();

    SellerDto getSellerById(Long sellerId);

    SellerDto createNewSeller(SellerRequestDto sellerRequestDto);

    void deleteSellerById(Long sellerId);

    SellerDto updateSeller(Long sellerId, @Valid SellerRequestDto sellerRequestDto);

    SellerDto updatePartialSeller(Long sellerId, Map<String, Object> updates);
}
