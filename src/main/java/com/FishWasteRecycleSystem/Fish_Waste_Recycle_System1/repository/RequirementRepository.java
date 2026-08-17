package com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.repository;

import com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.entity.Requirement;
import com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.enums.RequirementStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequirementRepository extends JpaRepository<Requirement,Long> {

    long countByCompanyCompanyId(Long companyId);

    long countByCompanyCompanyIdAndStatus(
            Long companyId,
            RequirementStatus status);
}
