package com.brainstation23.erp.model.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
public class Cost {
	private UUID id;
	private BigDecimal companyCost;
	private String costPurpose;
	private String costDate;
	private String costDetails;
	private String employeeId;
}
