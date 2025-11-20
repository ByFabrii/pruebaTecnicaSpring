package com.fabrizziodev.pruebatecsupermercado.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long saleId;
    private LocalDate date;
    private String status;
    private BigDecimal total;

    @ManyToOne
    private Branch branch;

    @OneToMany (mappedBy = "sale")
    private List<SaleDetail> saleDetails = new ArrayList<>();
}
