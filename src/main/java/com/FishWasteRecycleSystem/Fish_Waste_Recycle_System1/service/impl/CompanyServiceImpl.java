package com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.service.impl;

import com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.exception.BadRequestException;
import com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.exception.DuplicateResourceException;
import com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.exception.ResourceNotFoundException;
import com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.dto.CompanyDto;
import com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.dto.CompanyRequestDto;
import com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.entity.Company;
import com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.entity.User;
import com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.enums.Role;
import com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.repository.CompanyRepository;
import com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.repository.UserRepository;
import com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<CompanyDto> getAllCompanies(){

        List<Company> companies=companyRepository.findAll();

        List <CompanyDto> companyDtoList=companies
                .stream()
                .map(company->modelMapper.map(company,CompanyDto.class))
                .toList();
        return companyDtoList;
    }

    @Override
    public CompanyDto getCompanyId(Long companyId) {
        Company company=companyRepository.findById(companyId).orElseThrow(()->new ResourceNotFoundException("Company not found with id: " + companyId));
        return modelMapper.map(company,CompanyDto.class);
    }

    @Override
    public CompanyDto createNewCompany(CompanyRequestDto companyRequestDto) {

        User user = userRepository.findById(companyRequestDto.getUserId())
                .orElseThrow(() ->  new ResourceNotFoundException("User not found with id: " + companyRequestDto.getUserId()));;

        if (user.getRole() != Role.COMPANY) {
            throw new BadRequestException("User is not registered as a company");
        }
        if (companyRepository.existsByRegistrationNo(companyRequestDto.getRegistrationNo())) {
            throw new DuplicateResourceException("Registration number already exists.");
        }

        if (companyRepository.existsByUser_Id(companyRequestDto.getUserId())) {
            throw new DuplicateResourceException("Company profile already exists for this user.");
        }

        // ModelMapper ऐवजी मॅन्युअल मॅपिंग (सुरक्षित मार्ग)
        Company newCompany = new Company();
        newCompany.setCompanyName(companyRequestDto.getCompanyName());
        newCompany.setRegistrationNo(companyRequestDto.getRegistrationNo());
        newCompany.setAddress(companyRequestDto.getAddress());
        newCompany.setContactNo(companyRequestDto.getContactNo());
        newCompany.setCollectionCapacityKg(companyRequestDto.getCollectionCapacityKg());

        // युझर मॅप करणे
        newCompany.setUser(user);

        Company savedCompany = companyRepository.save(newCompany);

        return modelMapper.map(savedCompany, CompanyDto.class);
    }

    @Override
    public void deleteCompanyById(Long companyId) {
        if(!companyRepository.existsById(companyId))
        {
            throw new ResourceNotFoundException("Company not found with id: " + companyId);
        }
        companyRepository.deleteById(companyId);
    }

    @Override
    public CompanyDto updateCompany(Long companyId, CompanyRequestDto companyRequestDto) {

        Company company = companyRepository.findById(companyId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException("Company not found with id: " + companyId));
        company.setCompanyName(companyRequestDto.getCompanyName());
        company.setRegistrationNo(companyRequestDto.getRegistrationNo());
        company.setAddress(companyRequestDto.getAddress());
        company.setContactNo(companyRequestDto.getContactNo());
        company.setCollectionCapacityKg(companyRequestDto.getCollectionCapacityKg());

        Company updatedCompany = companyRepository.save(company);

        return modelMapper.map(updatedCompany, CompanyDto.class);
    }

    @Override
    public CompanyDto updatePartialCompany(Long companyId, Map<String, Object> updates) {
        Company company = companyRepository.findById(companyId).
                orElseThrow(() ->
                        new ResourceNotFoundException("Company not found with id: " + companyId));
        updates.forEach((field, value) -> {
            switch (field) {

                case "companyName":
                    company.setCompanyName((String) value);
                    break;

                case "registrationNo":
                    company.setRegistrationNo((String) value);
                    break;

                case "address":
                    company.setAddress((String) value);
                    break;

                case "contactNo":
                    company.setContactNo((String) value);
                    break;

                case "collectionCapacityKg":
                    company.setCollectionCapacityKg(((Number) value).longValue());
                    break;

                default:
                    throw new BadRequestException("Field '" + field + "' is not supported for update");
            }
        });
        Company savedCompany=companyRepository.save(company);
        return modelMapper.map(savedCompany,CompanyDto.class);
    }


}
