package com.fabrizziodev.pruebatecsupermercado.controller;

import com.fabrizziodev.pruebatecsupermercado.dto.SaleDTO;
import com.fabrizziodev.pruebatecsupermercado.service.ISaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/sale")
public class SaleController {

    @Autowired
    private ISaleService saleService;

    @GetMapping
    public ResponseEntity<List<SaleDTO>> getSales() {
        return ResponseEntity.ok(saleService.getSales());
    }

    @PostMapping
    public ResponseEntity<SaleDTO> createSale(@RequestBody SaleDTO saleDTO) {
        SaleDTO sale = saleService.createSale(saleDTO);

        return ResponseEntity.created(URI.create("/api/sale/" + sale.getSaleId())).body(sale);
    }

    @PutMapping("/{saleId}")
    public ResponseEntity<SaleDTO> updateSale(@PathVariable Long saleId, @RequestBody SaleDTO saleDTO){
        return ResponseEntity.ok(saleService.updateSale(saleId, saleDTO));
    }

    @DeleteMapping("/{saleId}")
    public ResponseEntity<Void> deleteSale(@PathVariable Long saleId) {
        saleService.deleteSale(saleId);
        return ResponseEntity.noContent().build();
    }
}
