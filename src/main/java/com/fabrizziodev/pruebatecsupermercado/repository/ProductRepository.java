package com.fabrizziodev.pruebatecsupermercado.repository;

import com.fabrizziodev.pruebatecsupermercado.model.Product;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository <Product, Long> {
    // JPA Repository automatic creates CRUD methods for us, if we need to create more methods, relations, or something like that, we could create here.

    // Search product by name
    Optional<Product> findByName(String name);
}
