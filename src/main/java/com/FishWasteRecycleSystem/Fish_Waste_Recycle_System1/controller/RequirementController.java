package com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.controller;

import com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.dto.RequirementDto;
import com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.dto.RequirementRequestDto;
import com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.dto.WasteListingDto;
import com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.service.RequirementService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/requirements")
@RequiredArgsConstructor
public class RequirementController {

    private final RequirementService requirementService;


    // Get All Listings
    @GetMapping
    public ResponseEntity<List<RequirementDto>> getAllRequirements() {
        return ResponseEntity.ok(requirementService.getAllRequirements());
    }

    // Get Listing By Id
    @GetMapping("/{requirementId}")
    public ResponseEntity<RequirementDto> getRequirementById(
            @PathVariable Long requirementId) {

        return ResponseEntity.ok(
               requirementService.getRequirementById(requirementId));
    }

    // Create Listing
    @PostMapping
    public ResponseEntity<RequirementDto> createNewRequirement(
            @Valid @RequestBody RequirementRequestDto requirementRequestDto) {

        RequirementDto createdRequirement =
               requirementService.createNewRequirement(requirementRequestDto);

        return new ResponseEntity<>(createdRequirement, HttpStatus.CREATED);
    }

    // Update Complete Listing
    @PutMapping("/{requirementId}")
    public ResponseEntity<RequirementDto> updateRequirement(
            @PathVariable Long requirementId,
            @Valid @RequestBody RequirementRequestDto requestDto) {

        return ResponseEntity.ok(
                requirementService.updateRequirement(
                        requirementId,
                        requestDto));
    }

    // Partial Update
    @PatchMapping("/{requirementId}")
    public ResponseEntity<RequirementDto> updatePartialRequirement(
            @PathVariable Long requirementId,
            @RequestBody Map<String, Object> updates) {

        return ResponseEntity.ok(
                requirementService.updatePartialRequirement(
                        requirementId,
                        updates));
    }

    // Delete Listing
    @DeleteMapping("/{requirementId}")
    public ResponseEntity<Void> deleteRequirement(
            @PathVariable Long requirementId) {

        requirementService.deleteRequirementById(requirementId);

        return ResponseEntity.noContent().build();
    }
}