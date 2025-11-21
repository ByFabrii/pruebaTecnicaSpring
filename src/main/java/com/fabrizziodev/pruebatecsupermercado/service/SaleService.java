package com.fabrizziodev.pruebatecsupermercado.service;

import com.fabrizziodev.pruebatecsupermercado.db.entity.Branch;
import com.fabrizziodev.pruebatecsupermercado.db.entity.Product;
import com.fabrizziodev.pruebatecsupermercado.db.entity.Sale;
import com.fabrizziodev.pruebatecsupermercado.db.entity.SaleDetail;
import com.fabrizziodev.pruebatecsupermercado.db.repository.BranchRepository;
import com.fabrizziodev.pruebatecsupermercado.db.repository.ProductRepository;
import com.fabrizziodev.pruebatecsupermercado.db.repository.SaleRepository;
import com.fabrizziodev.pruebatecsupermercado.exception.NotFoundException;
import com.fabrizziodev.pruebatecsupermercado.mapper.Mapper;
import com.fabrizziodev.pruebatecsupermercado.model.dto.SaleDTO;
import com.fabrizziodev.pruebatecsupermercado.model.dto.SaleDetailDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SaleService implements ISaleService {

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private BranchRepository branchRepository;

    @Override
    public List<SaleDTO> getSales() {
        List<Sale> sales = saleRepository.findAll();
        List<SaleDTO> salesDto = new ArrayList<>();
        SaleDTO dto;

        for (Sale sale : sales) {
            dto = Mapper.toDTO(sale);
            salesDto.add(dto);
        }

        return salesDto;
    }

    @Override
    public SaleDTO createSale(SaleDTO saleDto) {
        if ( saleDto == null ) throw new RuntimeException("SaleDTO is null");
        if ( saleDto.getBranchId() == null ) throw new RuntimeException("BranchId is null");
        if ( saleDto.getSaleDetails() == null || saleDto.getSaleDetails().isEmpty()) 
            throw new RuntimeException("SaleDetails is null or empty");

        // Search branch
        Branch branch = branchRepository.findById(saleDto.getBranchId()).orElse(null);
        if ( branch == null ) throw new NotFoundException("Branch not found");

        // Create sale
        Sale sale = new Sale();
        sale.setDate(saleDto.getDate());
        sale.setStatus(saleDto.getStatus());
        sale.setBranch(branch);
        sale.setTotal(saleDto.getTotal());

        // Get sale details list
        List<SaleDetail> saleDetails = new ArrayList<>();

        for ( SaleDetailDTO saleDetailDto : saleDto.getSaleDetails()) {
            Product product = productRepository.findByName(saleDetailDto.getProductName()).orElse(null);
            if ( product == null ) throw new NotFoundException("Product not found: " + saleDetailDto.getProductName());

            // Create sale detail
            SaleDetail saleDetail = new SaleDetail();
            saleDetail.setProduct(product);
            saleDetail.setQuantity(saleDetailDto.getProductQuantity());
            saleDetail.setUnitPrice(saleDetailDto.getPrice());
            saleDetail.setSale(sale);
            
            saleDetails.add(saleDetail);
        }

        // Set sale details to sale
        sale.setSaleDetails(saleDetails);

        // Save sale
        saleRepository.save(sale);

        // Return sale dto
        return Mapper.toDTO(sale);
    }

    @Override
    public SaleDTO updateSale(Long saleId, SaleDTO saleDto) {
        Sale sale = saleRepository.findById(saleId).orElse(null);
        if ( sale == null ) throw new NotFoundException("Sale not found");

        if (saleDto.getDate() != null) {
            sale.setDate(saleDto.getDate());
        }
        if (saleDto.getStatus() != null) {
            sale.setStatus(saleDto.getStatus());
        }
        if (saleDto.getTotal() != null) {
            sale.setTotal(saleDto.getTotal());
        }

        if (saleDto.getBranchId() != null) {
            Branch branch = branchRepository.findById(saleDto.getBranchId()).orElse(null);
            if ( branch == null ) throw new NotFoundException("Branch not found");
            sale.setBranch(branch);
        }

        saleRepository.save(sale);
        
        return Mapper.toDTO(sale);
    }

    @Override
    public void deleteSale(Long saleId) {
        if (!saleRepository.existsById(saleId)) {
            throw new NotFoundException("Sale not found");
        }
        saleRepository.deleteById(saleId);
    }
}
