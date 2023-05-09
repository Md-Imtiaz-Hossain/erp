package com.brainstation23.erp.mapper;

import com.brainstation23.erp.model.domain.Cost;
import com.brainstation23.erp.model.dto.request.CreateCostRequest;
import com.brainstation23.erp.persistence.entity.CostEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CostMapper {
	Cost toDomain(CostEntity costEntity);

	CostEntity toEntity(CreateCostRequest request);
}
