package com.fabrizziodev.pruebatecsupermercado.db.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
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
