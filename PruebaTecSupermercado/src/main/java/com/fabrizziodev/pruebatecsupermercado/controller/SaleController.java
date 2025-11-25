package com.fabrizziodev.pruebatecsupermercado.controller;

import com.fabrizziodev.pruebatecsupermercado.model.dto.SaleDTO;
import com.fabrizziodev.pruebatecsupermercado.service.ISaleService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/sale")
@Validated
@Tag(name = "Sale Controller", description = "All operations related to sales")
public class SaleController {

    @Autowired
    private ISaleService saleService;

    @GetMapping
    @Operation(summary = "Get all sales", description = "Get all sales available in the database")
    public ResponseEntity<List<SaleDTO>> getSales() {
        return ResponseEntity.ok(saleService.getSales());
    }

    @PostMapping
    @Operation(summary = "Create a new sale", description = "Create a new sale in the database")
    public ResponseEntity<SaleDTO> createSale(@Valid @RequestBody SaleDTO saleDTO) {
        SaleDTO sale = saleService.createSale(saleDTO);

        return ResponseEntity.created(URI.create("/api/sale/" + sale.getSaleId())).body(sale);
    }

    @PutMapping("/{saleId}")
    @Operation(summary = "Update a sale", description = "Update a sale in the database")
    public ResponseEntity<SaleDTO> updateSale(
            @PathVariable @Positive(message = "The sale ID must be a positive number") Long saleId,
            @Valid @RequestBody SaleDTO saleDTO) {
        return ResponseEntity.ok(saleService.updateSale(saleId, saleDTO));
    }

    @DeleteMapping("/{saleId}")
    @Operation(summary = "Delete a sale", description = "Delete a sale from the database")
    public ResponseEntity<Void> deleteSale(
            @PathVariable @Positive(message = "The sale ID must be a positive number") Long saleId) {
        saleService.deleteSale(saleId);
        return ResponseEntity.noContent().build();
    }
}
