package com.fabrizziodev.pruebatecsupermercado.service;

import com.fabrizziodev.pruebatecsupermercado.db.entity.Product;
import com.fabrizziodev.pruebatecsupermercado.db.repository.ProductRepository;
import com.fabrizziodev.pruebatecsupermercado.exception.NotFoundException;
import com.fabrizziodev.pruebatecsupermercado.mapper.Mapper;
import com.fabrizziodev.pruebatecsupermercado.model.dto.ProductDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<ProductDTO> getProducts() {
        return productRepository.findAll().stream().map(Mapper::toDTO).toList();
    }

    @Override
    public ProductDTO getProductById(Long productId) {
        return productRepository.findById(productId).map(Mapper::toDTO)
                .orElseThrow(() -> new NotFoundException("Product not found"));
    }

    @Override
    public ProductDTO createProduct(ProductDTO productDto) {
        var product = Product.builder()
                .name(productDto.getName())
                .category(productDto.getCategory())
                .price(productDto.getPrice())
                .quantity(productDto.getQuantity())
                .build();
        return Mapper.toDTO(productRepository.save(product));
    }

    @Override
    public ProductDTO updateProduct(Long productId, ProductDTO productDto) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException("Product not found"));

        product.setName(productDto.getName());
        product.setCategory(productDto.getCategory());
        product.setPrice(productDto.getPrice());
        product.setQuantity(productDto.getQuantity());
        return Mapper.toDTO(productRepository.save(product));
    }

    @Override
    public void deleteProduct(Long productId) {
        if (!productRepository.existsById(productId)) {
            throw new NotFoundException("Product not found");
        }

        productRepository.deleteById(productId);
    }
}
