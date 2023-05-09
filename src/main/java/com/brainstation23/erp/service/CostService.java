package com.brainstation23.erp.service;

import com.brainstation23.erp.exception.custom.custom.NotFoundException;
import com.brainstation23.erp.mapper.CostMapper;
import com.brainstation23.erp.model.domain.Cost;
import com.brainstation23.erp.model.dto.request.CreateCostRequest;
import com.brainstation23.erp.model.dto.request.UpdateCostRequest;
import com.brainstation23.erp.persistence.entity.CostEntity;
import com.brainstation23.erp.persistence.repository.CostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class CostService {
    public static final String COST_NOT_FOUND = "Cost Not Found";
    private final CostMapper costMapper;
    private final CostRepository costRepository;

    public Page<Cost> getAll(Pageable pageable) {
        var entities = costRepository.findAll(pageable);
        return entities.map(costMapper::toDomain);
    }

    public Cost getOne(UUID id) {
        var entity = costRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(COST_NOT_FOUND));
        return costMapper.toDomain(entity);
    }

    public UUID createOne(CreateCostRequest createRequest) {
        var entity = new CostEntity();
        entity.setId(UUID.randomUUID())
                .setCompanyCost(createRequest.getCompanyCost())
                .setCostPurpose(createRequest.getCostPurpose())
                .setCostDate(createRequest.getCostDate())
                .setEmployeeId(createRequest.getEmployeeId())
                .setCostDetails(createRequest.getCostDetails())
        ;
        var revenueEntity = costRepository.save(entity);
        return revenueEntity.getId();
    }

    public void updateOne(UUID id, UpdateCostRequest updateRequest) {
        var entity = costRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(COST_NOT_FOUND));
        entity.
                setCompanyCost(updateRequest.getCompanyCost())
                .setCostPurpose(updateRequest.getCostPurpose())
                .setCostDate(updateRequest.getCostDate())
                .setEmployeeId(updateRequest.getEmployeeId())
                .setCostDetails(updateRequest.getCostDetails());
        costRepository.save(entity);
    }


}
