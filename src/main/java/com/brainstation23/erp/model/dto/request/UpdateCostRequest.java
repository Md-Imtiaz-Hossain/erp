package com.brainstation23.erp.model.dto.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;

@ToString
@Getter
@Setter
public class UpdateCostRequest {
    private BigDecimal companyCost;
    @NotEmpty(message = "Cost purpose must be fill up.")
    private String costPurpose;
    @NotEmpty(message = "Date of cost required.")
    private String costDate;
    @NotEmpty(message = "Cost details is required.")
    private String costDetails;
    private String employeeId;
}
