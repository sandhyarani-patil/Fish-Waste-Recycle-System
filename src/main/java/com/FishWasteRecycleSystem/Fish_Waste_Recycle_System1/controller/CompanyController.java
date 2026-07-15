package com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.controller;

import com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.dto.CompanyDto;
import com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.dto.CompanyRequestDto;
import com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.service.CompanyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/company")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    @GetMapping
    public ResponseEntity<List<CompanyDto>> getAllCompanies(){
        return ResponseEntity.ok(companyService.getAllCompanies());
    }

    @GetMapping("/{companyId}")
    public ResponseEntity<CompanyDto> getCompanyId(@PathVariable Long companyId){
        return ResponseEntity.ok(companyService.getCompanyId(companyId));
    }

    @PostMapping
    public ResponseEntity<CompanyDto> createNewCompany(@RequestBody @Valid CompanyRequestDto companyRequestDto)
    {
        CompanyDto companyDto=companyService.createNewCompany(companyRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(companyDto);
    }

    @DeleteMapping("/{companyId}")
    public ResponseEntity<Void> deleteCompanyById(@PathVariable Long companyId)
    {
        companyService.deleteCompanyById(companyId);
        return  ResponseEntity.noContent().build();
    }

    @PutMapping("/{companyId}")
    public ResponseEntity<CompanyDto> updateSeller(@PathVariable Long companyId,@Valid @RequestBody CompanyRequestDto companyRequestDto)
    {
        return ResponseEntity.ok(companyService.updateCompany(companyId,companyRequestDto));
    }


    @PatchMapping("/{companyId}")
    public ResponseEntity<CompanyDto> updatePartialCompany(@PathVariable Long companyId, @RequestBody Map<String,Object> updates)
    {
        return ResponseEntity.ok(companyService.updatePartialCompany(companyId,updates));
    }


}


