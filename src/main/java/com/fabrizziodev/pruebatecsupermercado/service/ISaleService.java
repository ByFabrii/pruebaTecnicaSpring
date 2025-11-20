package com.fabrizziodev.pruebatecsupermercado.service;

import com.fabrizziodev.pruebatecsupermercado.dto.SaleDTO;

import java.util.List;

public interface ISaleService {

    List<SaleDTO> getSales();
    SaleDTO createSale(SaleDTO saleDto);
    SaleDTO updateSale(Long saleId, SaleDTO saleDto);
    void deleteSale(Long saleId);
}
