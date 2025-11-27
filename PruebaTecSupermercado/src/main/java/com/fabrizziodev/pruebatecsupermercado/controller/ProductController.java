package com.fabrizziodev.pruebatecsupermercado.controller;

import com.fabrizziodev.pruebatecsupermercado.model.dto.ProductDTO;
import com.fabrizziodev.pruebatecsupermercado.service.IProductService;

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
@RequestMapping("/api/products")
@Validated
@Tag(name = "Product Controller", description = "All operations related to products")
public class ProductController {

    @Autowired
    private IProductService productService;

    @GetMapping
    @Operation(summary = "Get all products", description = "Get all products available in the database")
    public ResponseEntity<List<ProductDTO>> getProducts() {
        return ResponseEntity.ok(productService.getProducts());
    }

    @GetMapping("/{productId}")
    @Operation(summary = "Get a product by ID", description = "Get a product by ID")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable @Positive(message = "The product ID must be a positive number") Long productId) {
        return ResponseEntity.ok(productService.getProductById(productId));
    }

    @PostMapping
    @Operation(summary = "Create a new product", description = "Create a new product in the database")
    public ResponseEntity<ProductDTO> createProduct(@Valid @RequestBody ProductDTO productDTO) {
        ProductDTO product = productService.createProduct(productDTO);

        return ResponseEntity.created(URI.create("/api/products/" + product.getProductId())).body(product);
    }

    @PutMapping("/{productId}")
    @Operation(summary = "Update a product", description = "Update a product in the database")
    public ResponseEntity<ProductDTO> updateProducts(
            @PathVariable @Positive(message = "The product ID must be a positive number") Long productId,
            @Valid @RequestBody ProductDTO productDto) {
        return ResponseEntity.ok(productService.updateProduct(productId, productDto));
    }

    @DeleteMapping("/{productId}")
    @Operation(summary = "Delete a product", description = "Delete a product from the database")
    public ResponseEntity<Void> deleteProduct(
            @PathVariable @Positive(message = "The product ID must be a positive number") Long productId) {
        productService.deleteProduct(productId);

        return ResponseEntity.noContent().build();
    }

}
