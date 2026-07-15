package com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.dto;

import com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDto {

    private Long companyId;
    private String companyName;
    private String registrationNo;
    private String address;
    private String contactNo;
    private Long collectionCapacityKg;
    private Long userId;


}
