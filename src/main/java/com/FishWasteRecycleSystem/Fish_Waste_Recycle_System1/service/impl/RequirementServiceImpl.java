package com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.service.impl;

import com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.dto.RequirementDto;
import com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.dto.RequirementRequestDto;
import com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.entity.Company;
import com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.entity.Requirement;
import com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.enums.RequirementStatus;
import com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.repository.CompanyRepository;
import com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.repository.RequirementRepository;
import com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.service.RequirementService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class RequirementServiceImpl implements RequirementService {

    private final RequirementRepository requirementRepository;
    private final CompanyRepository companyRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<RequirementDto> getAllRequirements() {

        return requirementRepository.findAll()
                .stream()
                .map(requirement -> {
                   RequirementDto dto = modelMapper.map(requirement, RequirementDto.class);
                    dto.setCompanyId(requirement.getCompany().getCompanyId());
                    dto.setCompanyName(requirement.getCompany().getCompanyName());
                    return dto;
                })
                .toList();
    }

    @Override
    public RequirementDto getRequirementById(Long requirementId) {

        Requirement requirement = requirementRepository.findById(requirementId)
                .orElseThrow(() ->
                        new IllegalArgumentException("Requirement not found with id: " + requirementId));

        RequirementDto dto = modelMapper.map(requirement,RequirementDto.class);
        dto.setCompanyId(requirement.getCompany().getCompanyId());
        dto.setCompanyName(requirement.getCompany().getCompanyName());

        return dto;
    }

    @Override
    public RequirementDto createNewRequirement(RequirementRequestDto requirementRequestDto) {

        Company company = companyRepository.findById(requirementRequestDto.getCompanyId())
                .orElseThrow(() -> new IllegalArgumentException("Company not found"));

        Requirement newRequirement = new Requirement();

        newRequirement .setWasteType(requirementRequestDto.getWasteType());
        newRequirement .setQuantity(requirementRequestDto.getQuantity());
        newRequirement .setLocation(requirementRequestDto.getLocation());
        newRequirement .setBudget(requirementRequestDto.getBudget());
        newRequirement .setDescription(requirementRequestDto.getDescription());
        newRequirement .setRequiredBefore(requirementRequestDto.getRequiredBefore());

        newRequirement.setStatus(RequirementStatus.OPEN);

        newRequirement .setCompany(company);

        Requirement savedRequirement = requirementRepository.save(newRequirement);

       RequirementDto dto = modelMapper.map(savedRequirement, RequirementDto.class);
        dto.setCompanyId(savedRequirement.getCompany().getCompanyId());
        dto.setCompanyName(savedRequirement.getCompany().getCompanyName());

        return dto;
    }

    @Override
    public void deleteRequirementById(Long requirementId) {

        if (!requirementRepository.existsById(requirementId)) {
            throw new IllegalArgumentException("Requirement does not exist with id: " + requirementId);
        }

      requirementRepository.deleteById(requirementId);
    }

    @Override
    public RequirementDto updateRequirement(Long requiementId,
                                              RequirementRequestDto requirementRequestDto) {

        Requirement requirement = requirementRepository.findById(requiementId)
                .orElseThrow(() ->
                        new IllegalArgumentException("Requirement not found with id: " + requiementId));

        requirement.setWasteType(requirementRequestDto.getWasteType());
        requirement.setQuantity(requirementRequestDto.getQuantity());
        requirement.setLocation(requirementRequestDto.getLocation());
        requirement.setBudget(requirementRequestDto.getBudget());
        requirement.setDescription(requirementRequestDto.getDescription());
        requirement.setRequiredBefore(requirementRequestDto.getRequiredBefore());

       Company company = companyRepository.findById(requirementRequestDto.getCompanyId())
                .orElseThrow(() -> new IllegalArgumentException("Company not found"));

        requirement.setCompany(company);


        Requirement updateRequirement= requirementRepository.save(requirement);

        RequirementDto dto = modelMapper.map(updateRequirement, RequirementDto.class);
        dto.setCompanyId(requirement.getCompany().getCompanyId());
        dto.setCompanyName(requirement.getCompany().getCompanyName());

        return dto;
    }

    @Override
    public RequirementDto updatePartialRequirement(Long requirementId,
                                                     Map<String, Object> updates) {

       Requirement requirement = requirementRepository.findById(requirementId)
                .orElseThrow(() ->
                        new IllegalArgumentException("Requirement not found with id: " + requirementId));

        updates.forEach((field, value) -> {

            switch (field) {

                case "companyId" -> {
                    Company company = companyRepository.findById(Long.valueOf(value.toString()))
                            .orElseThrow(() -> new IllegalArgumentException("Company not found"));
                    requirement.setCompany(company);
                }

                case "wasteType" -> requirement.setWasteType((String) value);

                case "quantity" -> requirement.setQuantity((Double) value);

                case "location" -> requirement.setLocation((String) value);

                case "budget" -> requirement.setBudget((BigDecimal) value);

                case "description" ->requirement.setDescription((String) value);

                case "requiredBefore" -> requirement.setRequiredBefore(LocalDate.parse(value.toString()));

                case "status" -> requirement.setStatus(RequirementStatus.valueOf(value.toString()));

                default -> throw new IllegalArgumentException("Field '" + field + "' is not supported");
            }
        });

        requirement.setCreatedAt(LocalDateTime.now());

       Requirement savedRequirement = requirementRepository.save(requirement);

        RequirementDto dto = modelMapper.map(savedRequirement,RequirementDto.class);
        dto.setCompanyId(savedRequirement.getCompany().getCompanyId());
        dto.setCompanyName(savedRequirement.getCompany().getCompanyName());

        return dto;
    }


}
