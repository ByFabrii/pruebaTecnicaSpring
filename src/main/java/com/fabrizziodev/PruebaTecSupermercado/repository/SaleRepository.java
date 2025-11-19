package com.fabrizziodev.PruebaTecSupermercado.repository;

import com.fabrizziodev.PruebaTecSupermercado.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository <Sale, Long> {
    // JPA Repository automatic creates CRUD methods for us, if we need to create more methods, relations, or something like that, we could create here.
}
