package com.fabrizziodev.pruebatecsupermercado.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fabrizziodev.pruebatecsupermercado.db.entity.Sale;

public interface SaleRepository extends JpaRepository <Sale, Long> {
    // JPA Repository automatic creates CRUD methods for us, if we need to create more methods, relations, or something like that, we could create here.
}
