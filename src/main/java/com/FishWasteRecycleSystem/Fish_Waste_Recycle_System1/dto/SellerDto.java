package com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class SellerDto {

    private  Long  sellerId;
    private String shopName;
    private Integer availableFishWasteKg;
    private String address;
    private Long userId;

}


