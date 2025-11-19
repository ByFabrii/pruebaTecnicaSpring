package com.fabrizziodev.PruebaTecSupermercado.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class SaleDTO {

    private Long saleId;
    private LocalDate date;
    private String status;
    private BigDecimal total;
    private Long branchId;
    private List<SaleDetailDTO> saleDetails;
}
