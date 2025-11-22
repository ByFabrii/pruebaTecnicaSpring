package com.fabrizziodev.pruebatecsupermercado.controller;

import com.fabrizziodev.pruebatecsupermercado.model.dto.ProductDTO;
import com.fabrizziodev.pruebatecsupermercado.service.IProductService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/products")
@Validated
public class ProductController {

    @Autowired
    private IProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getProducts() {
        return ResponseEntity.ok(productService.getProducts());
    }

    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@Valid @RequestBody ProductDTO productDTO){
        ProductDTO product = productService.createProduct(productDTO);

        return ResponseEntity.created(URI.create("/api/products/" + product.getProductId())).body(product);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<ProductDTO> updateProducts(@PathVariable @Positive(message = "The product ID must be a positive number") Long productId, @Valid @RequestBody ProductDTO productDto) {
        return ResponseEntity.ok(productService.updateProduct(productId, productDto));
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable @Positive(message = "The product ID must be a positive number") Long productId){
        productService.deleteProduct(productId);

        return ResponseEntity.noContent().build();
    }

}
