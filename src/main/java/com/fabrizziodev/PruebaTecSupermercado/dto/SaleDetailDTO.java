package com.fabrizziodev.PruebaTecSupermercado.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class SaleDetailDTO {

    private Long saleDetailId;
    private String productName;
    private Integer productQuantity;
    private BigDecimal price;
    private BigDecimal subTotal;
}
