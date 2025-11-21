package com.fabrizziodev.pruebatecsupermercado.service;

import java.util.List;

import com.fabrizziodev.pruebatecsupermercado.model.dto.ProductDTO;

public interface IProductService {

    List<ProductDTO> getProducts();
    ProductDTO createProduct(ProductDTO productDto);
    ProductDTO updateProduct(Long productId, ProductDTO productDto);
    void deleteProduct(Long productId);
}
