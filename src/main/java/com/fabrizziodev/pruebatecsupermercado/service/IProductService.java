package com.fabrizziodev.pruebatecsupermercado.service;

import com.fabrizziodev.pruebatecsupermercado.dto.ProductDTO;

import java.util.List;

public interface IProductService {

    List<ProductDTO> getProducts();
    ProductDTO createProduct(ProductDTO productDto);
    ProductDTO updateProduct(Long productId, ProductDTO productDto);
    void deleteProduct(Long productId);
}
