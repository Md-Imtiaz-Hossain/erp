package com.brainstation23.erp.persistence.entity;

import com.brainstation23.erp.constant.EntityConstant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = EntityConstant.REVENUE)
public class RevenueEntity {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type = "uuid-char")
    private UUID id;
    private BigDecimal companyRevenue;
    private String revenuePurpose;
    private String revenueGettingDate;
    private String revenueFrom;
}
