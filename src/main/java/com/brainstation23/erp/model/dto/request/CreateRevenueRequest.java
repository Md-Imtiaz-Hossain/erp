package com.brainstation23.erp.model.dto.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;

@ToString
@Getter
@Setter
public class CreateRevenueRequest {
    @NotEmpty(message = "Enter total revenue.")
    private BigDecimal companyRevenue;
    @NotEmpty(message = "Revenue purpose must be fill up.")
    private String revenuePurpose;
    @NotEmpty(message = "Getting date of revenue required.")
    private String revenueGettingDate;
    @NotEmpty(message = "Payer information is required.")
    private String revenueFrom;
}
