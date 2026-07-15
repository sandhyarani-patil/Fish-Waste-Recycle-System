package com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.controller;

import com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.dto.WasteListingDto;
import com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.dto.WasteListingRequestDto;
import com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.service.WasteListingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/wastelistings")
@RequiredArgsConstructor
public class WasteListingController {

    private final WasteListingService wasteListingService;

    // Get All Listings
    @GetMapping
    public ResponseEntity<List<WasteListingDto>> getAllListings() {
        return ResponseEntity.ok(wasteListingService.getAllListing());
    }

    // Get Listing By Id
    @GetMapping("/{wasteListingId}")
    public ResponseEntity<WasteListingDto> getWasteListingById(
            @PathVariable Long wasteListingId) {

        return ResponseEntity.ok(
                wasteListingService.getWasteListingById(wasteListingId));
    }

    // Create Listing
    @PostMapping
    public ResponseEntity<WasteListingDto> createWasteListing(
            @Valid @RequestBody WasteListingRequestDto requestDto) {

        WasteListingDto createdListing =
                wasteListingService.createNewWasteListing(requestDto);

        return new ResponseEntity<>(createdListing, HttpStatus.CREATED);
    }

    // Update Complete Listing
    @PutMapping("/{wasteListingId}")
    public ResponseEntity<WasteListingDto> updateWasteListing(
            @PathVariable Long wasteListingId,
            @Valid @RequestBody WasteListingRequestDto requestDto) {

        return ResponseEntity.ok(
                wasteListingService.updateWasteListing(
                        wasteListingId,
                        requestDto));
    }

    // Partial Update
    @PatchMapping("/{wasteListingId}")
    public ResponseEntity<WasteListingDto> updatePartialWasteListing(
            @PathVariable Long wasteListingId,
            @RequestBody Map<String, Object> updates) {

        return ResponseEntity.ok(
                wasteListingService.updatePartialWasteListing(
                        wasteListingId,
                        updates));
    }

    // Delete Listing
    @DeleteMapping("/{wasteListingId}")
    public ResponseEntity<Void> deleteWasteListing(
            @PathVariable Long wasteListingId) {

        wasteListingService.deleteWasteListingById(wasteListingId);

        return ResponseEntity.noContent().build();
    }
}
