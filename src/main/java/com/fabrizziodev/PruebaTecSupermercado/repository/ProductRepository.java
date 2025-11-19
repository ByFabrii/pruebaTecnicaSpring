package com.fabrizziodev.PruebaTecSupermercado.repository;

import com.fabrizziodev.PruebaTecSupermercado.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository <Product, Long> {
    // JPA Repository automatic creates CRUD methods for us, if we need to create more methods, relations, or something like that, we could create here.
}
