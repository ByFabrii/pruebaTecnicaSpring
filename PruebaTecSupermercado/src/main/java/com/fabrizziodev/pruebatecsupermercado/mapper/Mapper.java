package com.fabrizziodev.pruebatecsupermercado.mapper;

import java.math.BigDecimal;
import java.util.stream.Collectors;

import com.fabrizziodev.pruebatecsupermercado.db.entity.Branch;
import com.fabrizziodev.pruebatecsupermercado.db.entity.Product;
import com.fabrizziodev.pruebatecsupermercado.db.entity.Sale;
import com.fabrizziodev.pruebatecsupermercado.model.dto.BranchDTO;
import com.fabrizziodev.pruebatecsupermercado.model.dto.ProductDTO;
import com.fabrizziodev.pruebatecsupermercado.model.dto.SaleDTO;
import com.fabrizziodev.pruebatecsupermercado.model.dto.SaleDetailDTO;

public class Mapper {

    // Map from Product to ProductDto
    public static ProductDTO toDTO(Product product) {
        if (product == null)
            return null;

        return ProductDTO.builder()
                .productId(product.getProductId())
                .name(product.getName())
                .category(product.getCategory())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .build();

    }

    // Map from Sale to SaleDto
    public static SaleDTO toDTO(Sale sale) {
        if (sale == null)
            return null;

        var saleDetails = sale.getSaleDetails().stream().map(detail -> SaleDetailDTO.builder()
                .saleDetailId(detail.getProduct().getProductId())
                .productName(detail.getProduct().getName())
                .productQuantity(detail.getQuantity())
                .price(detail.getUnitPrice())
                .subTotal(detail.getUnitPrice().multiply(BigDecimal.valueOf(detail.getQuantity())))
                .build()).collect(Collectors.toList());

        var total = saleDetails.stream().map(SaleDetailDTO::getSubTotal).reduce(BigDecimal.ZERO, BigDecimal::add);

        return SaleDTO.builder()
                .saleId(sale.getSaleId())
                .date(sale.getDate())
                .branchId(sale.getBranch().getBranchId())
                .status(sale.getStatus())
                .saleDetails(saleDetails)
                .total(total)
                .build();
    }

    // Map from Branch to BranchDto
    public static BranchDTO toDTO(Branch branch) {
        if (branch == null)
            return null;

        return BranchDTO.builder()
                .branchId(branch.getBranchId())
                .name(branch.getName())
                .address(branch.getAddress())
                .build();
    }
}
