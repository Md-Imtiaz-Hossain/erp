package com.brainstation23.erp.mapper;

import com.brainstation23.erp.model.domain.Revenue;
import com.brainstation23.erp.model.dto.request.CreateRevenueRequest;
import com.brainstation23.erp.persistence.entity.RevenueEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RevenueMapper {
	Revenue toDomain(RevenueEntity revenueEntity);

	RevenueEntity toEntity(CreateRevenueRequest request);
}
