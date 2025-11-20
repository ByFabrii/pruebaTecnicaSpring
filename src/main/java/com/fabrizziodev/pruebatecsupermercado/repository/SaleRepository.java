package com.fabrizziodev.pruebatecsupermercado.repository;

import com.fabrizziodev.pruebatecsupermercado.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository <Sale, Long> {
    // JPA Repository automatic creates CRUD methods for us, if we need to create more methods, relations, or something like that, we could create here.
}
