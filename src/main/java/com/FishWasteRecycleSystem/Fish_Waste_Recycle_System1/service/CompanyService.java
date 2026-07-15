package com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.service;

import com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.dto.CompanyDto;
import com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.dto.CompanyRequestDto;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


public interface CompanyService {

    List<CompanyDto> getAllCompanies();

    CompanyDto getCompanyId(Long companyId);

    CompanyDto createNewCompany(@Valid CompanyRequestDto companyRequestDto);


    void deleteCompanyById(Long companyId);

    CompanyDto updateCompany(Long companyId, @Valid CompanyRequestDto companyRequestDto);

    CompanyDto updatePartialCompany(Long companyId, Map<String, Object> updates);
}
