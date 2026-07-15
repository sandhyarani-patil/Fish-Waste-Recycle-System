package com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Seller {

    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private  Long  sellerId;
    private String shopName;
    private Integer availableFishWasteKg;
    private String address;

    @OneToOne
    @JoinColumn(name="user_id")
    private User user;


}
