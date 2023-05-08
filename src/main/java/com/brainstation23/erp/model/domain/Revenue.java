package com.brainstation23.erp.model.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
public class Revenue {
	private UUID id;
	private BigDecimal companyRevenue;
	private String revenuePurpose;
	private String revenueGettingDate;
	private String revenueFrom;
}
