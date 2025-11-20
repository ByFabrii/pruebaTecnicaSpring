package com.fabrizziodev.pruebatecsupermercado.repository;

import com.fabrizziodev.pruebatecsupermercado.model.Branch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BranchRepository extends JpaRepository <Branch, Long> {
    // JPA Repository automatic creates CRUD methods for us, if we need to create more methods, relations, or something like that, we could create here.
}
