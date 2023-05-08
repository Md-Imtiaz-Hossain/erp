package com.brainstation23.erp.service;

import com.brainstation23.erp.exception.custom.custom.NotFoundException;
import com.brainstation23.erp.mapper.OrganizationMapper;
import com.brainstation23.erp.mapper.RevenueMapper;
import com.brainstation23.erp.model.domain.Revenue;
import com.brainstation23.erp.model.dto.request.CreateRevenueRequest;
import com.brainstation23.erp.model.dto.request.UpdateRevenueRequest;
import com.brainstation23.erp.persistence.entity.RevenueEntity;
import com.brainstation23.erp.persistence.repository.RevenueRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class RevenueService {
    public static final String REVENUE_NOT_FOUND = "Revenue Not Found";
    //    private final OrganizationRepository organizationRepository;
    private final RevenueRepository revenueRepository;
    private final RevenueMapper revenueMapper;

    public Page<Revenue> getAll(Pageable pageable) {
        var entities = revenueRepository.findAll(pageable);
        return entities.map(revenueMapper::toDomain);
    }

    public Revenue getOne(UUID id) {
        var entity = revenueRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(REVENUE_NOT_FOUND));
        return revenueMapper.toDomain(entity);
    }

    public UUID createOne(CreateRevenueRequest createRequest) {
        var entity = new RevenueEntity();
        entity.setId(UUID.randomUUID())
                .setCompanyRevenue(createRequest.getCompanyRevenue())
                .setRevenuePurpose(createRequest.getRevenuePurpose())
                .setRevenueGettingDate(createRequest.getRevenueGettingDate())
                .setRevenueFrom(createRequest.getRevenueFrom());
        var revenueEntity = revenueRepository.save(entity);
        return revenueEntity.getId();
    }

    public void updateOne(UUID id, UpdateRevenueRequest updateRequest) {
        var entity = revenueRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(REVENUE_NOT_FOUND));
        entity.setCompanyRevenue(updateRequest.getCompanyRevenue())
                .setRevenuePurpose(updateRequest.getRevenuePurpose())
                .setRevenueGettingDate(updateRequest.getRevenueGettingDate())
                .setRevenueFrom(updateRequest.getRevenueFrom());
        revenueRepository.save(entity);
    }

    public BigDecimal getTotalCompanyRevenue() {
        List<RevenueEntity> revenueEntities = revenueRepository.findAll();
        return revenueEntities.stream()
                .map(RevenueEntity::getCompanyRevenue)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
