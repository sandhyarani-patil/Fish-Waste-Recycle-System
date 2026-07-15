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
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long companyId;

    private String companyName;

    @Column(unique = true,nullable = false)
    private String registrationNo;
    private String address;
    private String contactNo;
    private Long collectionCapacityKg;

    @OneToOne
    @JoinColumn(name="user_id")
    private  User user;
}
