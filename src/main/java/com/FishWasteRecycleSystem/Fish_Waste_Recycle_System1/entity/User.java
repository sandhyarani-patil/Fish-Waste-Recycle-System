package com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.entity;

import com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.enums.Role;
import jakarta.persistence.*;

import lombok.Data;

@Entity
@Table
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true,nullable = false)
    private String email;
    private String password;
    private String phoneNo;

    @Enumerated(EnumType.STRING)
    private Role role;
}
