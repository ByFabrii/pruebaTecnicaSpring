package com.fabrizziodev.pruebatecsupermercado.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaleDetailDTO {

    private Long saleDetailId;
    private String productName;
    private Integer productQuantity;
    private BigDecimal price;
    private BigDecimal subTotal;
}
