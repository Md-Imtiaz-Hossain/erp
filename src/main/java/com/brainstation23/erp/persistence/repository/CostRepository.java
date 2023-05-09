package com.brainstation23.erp.persistence.repository;

import com.brainstation23.erp.persistence.entity.CostEntity;
import com.brainstation23.erp.persistence.entity.RevenueEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CostRepository extends JpaRepository<CostEntity, UUID> {


}
