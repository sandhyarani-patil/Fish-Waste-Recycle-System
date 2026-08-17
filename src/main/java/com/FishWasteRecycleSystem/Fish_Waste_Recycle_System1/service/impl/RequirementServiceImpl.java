package com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.service.impl;

import com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.exception.BadRequestException;
import com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.exception.ResourceNotFoundException;
import com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.dto.RequirementDto;
import com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.dto.RequirementRequestDto;
import com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.entity.Company;
import com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.entity.Requirement;
import com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.enums.RequirementStatus;
import com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.repository.CompanyRepository;
import com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.repository.OrderRepository;
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
    private final OrderRepository orderRepository;
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
                        new ResourceNotFoundException("Requirement not found with id: " + requirementId));
        RequirementDto dto = modelMapper.map(requirement,RequirementDto.class);
        dto.setCompanyId(requirement.getCompany().getCompanyId());
        dto.setCompanyName(requirement.getCompany().getCompanyName());

        return dto;
    }

    @Override
    public RequirementDto createNewRequirement(RequirementRequestDto requirementRequestDto) {

        Company company = companyRepository.findById(requirementRequestDto.getCompanyId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Company not found with id: " + requirementRequestDto.getCompanyId()));

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
            throw new ResourceNotFoundException(
                    "Requirement not found with id: " + requirementId);
        }

        if (orderRepository.existsByRequirementRequirementId(requirementId)) {
            throw new BadRequestException(
                    "Requirement cannot be deleted because it is already used in an order.");
        }

        requirementRepository.deleteById(requirementId);
    }

    @Override
    public RequirementDto updateRequirement(Long requiementId,
                                              RequirementRequestDto requirementRequestDto) {

        Requirement requirement = requirementRepository.findById(requiementId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Requirement not found with id: " + requiementId));

        requirement.setWasteType(requirementRequestDto.getWasteType());
        requirement.setQuantity(requirementRequestDto.getQuantity());
        requirement.setLocation(requirementRequestDto.getLocation());
        requirement.setBudget(requirementRequestDto.getBudget());
        requirement.setDescription(requirementRequestDto.getDescription());
        requirement.setRequiredBefore(requirementRequestDto.getRequiredBefore());

       Company company = companyRepository.findById(requirementRequestDto.getCompanyId())
               .orElseThrow(() ->
                       new ResourceNotFoundException(
                               "Company not found with id: " + requirementRequestDto.getCompanyId()));

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
                       new ResourceNotFoundException(
                               "Requirement not found with id: " + requirementId));

        updates.forEach((field, value) -> {

            switch (field) {

                case "companyId" -> {
                    Company company = companyRepository.findById(Long.valueOf(value.toString()))
                            .orElseThrow(() ->
                                    new ResourceNotFoundException(
                                            "Company not found with id: " + value));
                    requirement.setCompany(company);
                }

                case "wasteType" -> {
                    String wasteType = value.toString().trim();

                    if (wasteType.isBlank()) {
                        throw new BadRequestException("Waste type is required.");
                    }

                    requirement.setWasteType(wasteType);
                }

                case "quantity" -> {
                    Double quantity = ((Number) value).doubleValue();

                    if (quantity <= 0) {
                        throw new BadRequestException("Quantity must be greater than 0.");
                    }

                    requirement.setQuantity(quantity);
                }
                case "location" -> {
                    String location = value.toString().trim();

                    if (location.isBlank()) {
                        throw new BadRequestException("Location is required.");
                    }

                    requirement.setLocation(location);
                }
                case "budget" -> {
                    BigDecimal budget = new BigDecimal(value.toString());

                    if (budget.compareTo(BigDecimal.ZERO) <= 0) {
                        throw new BadRequestException("Budget must be greater than 0.");
                    }

                    requirement.setBudget(budget);
                }
                case "description" -> {
                    String description = value.toString().trim();

                    if (description.isBlank()) {
                        throw new BadRequestException("Description is required.");
                    }

                    requirement.setDescription(description);
                }

                case "requiredBefore" -> {
                    LocalDate date = LocalDate.parse(value.toString());

                    if (date.isBefore(LocalDate.now())) {
                        throw new BadRequestException("Required before date cannot be in the past.");
                    }

                    requirement.setRequiredBefore(date);
                }

                case "status" -> requirement.setStatus(RequirementStatus.valueOf(value.toString()));

                default -> throw new BadRequestException(
                        "Field '" + field + "' is not supported for update");
            }
            Requirement savedRequirement = requirementRepository.save(requirement);
        });

        requirement.setCreatedAt(LocalDateTime.now());

       Requirement savedRequirement = requirementRepository.save(requirement);

        RequirementDto dto = modelMapper.map(savedRequirement,RequirementDto.class);
        dto.setCompanyId(savedRequirement.getCompany().getCompanyId());
        dto.setCompanyName(savedRequirement.getCompany().getCompanyName());

        return dto;
    }


}
