package com.fabrizziodev.pruebatecsupermercado.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaleDTO {

    private Long saleId;
    private LocalDate date;
    private String status;
    private BigDecimal total;
    private Long branchId;
    private List<SaleDetailDTO> saleDetails;
}
