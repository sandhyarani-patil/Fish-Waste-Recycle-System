package com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.service;

import com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.dto.RequirementDto;
import com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.dto.RequirementRequestDto;

import java.util.List;
import java.util.Map;

public interface RequirementService {

    List<RequirementDto> getAllRequirements();

    RequirementDto getRequirementById(Long requirementId);

    RequirementDto createNewRequirement(RequirementRequestDto requirementRequestDto);

    RequirementDto updateRequirement(Long requirementId,
                                     RequirementRequestDto requirementRequestDto);

    RequirementDto updatePartialRequirement(Long requirementId,
                                            Map<String, Object> updates);

    void deleteRequirementById(Long requirementId);
}