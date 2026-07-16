package com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.entity;

import com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.enums.RequirementStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

    @Entity
    @Table(name = "requirements")
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class Requirement {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long requirementId;

        private String wasteType;

        private Double quantity;

        private String location;

        private BigDecimal budget;

        @Column(length = 500)
        private String description;

        private LocalDate requiredBefore;

        @Enumerated(EnumType.STRING)
        private RequirementStatus status;

        @CreationTimestamp
        private LocalDateTime createdAt;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "company_id")
        private Company company;
    }

