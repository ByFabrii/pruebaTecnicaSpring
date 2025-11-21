package com.fabrizziodev.pruebatecsupermercado.service;

import java.util.List;

import com.fabrizziodev.pruebatecsupermercado.model.dto.SaleDTO;

public interface ISaleService {

    List<SaleDTO> getSales();
    SaleDTO createSale(SaleDTO saleDto);
    SaleDTO updateSale(Long saleId, SaleDTO saleDto);
    void deleteSale(Long saleId);
}
