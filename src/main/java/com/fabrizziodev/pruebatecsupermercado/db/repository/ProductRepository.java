package com.fabrizziodev.pruebatecsupermercado.db.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fabrizziodev.pruebatecsupermercado.db.entity.Product;

public interface ProductRepository extends JpaRepository <Product, Long> {
    // JPA Repository automatic creates CRUD methods for us, if we need to create more methods, relations, or something like that, we could create here.

    // Search product by name
    Optional<Product> findByName(String name);
}
