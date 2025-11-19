package com.fabrizziodev.PruebaTecSupermercado.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class SaleDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long saleDetailId;
    private Integer quantity;
    private BigDecimal unitPrice;

    @ManyToOne
    private Product product;

    @ManyToOne
    private Sale sale;
}
