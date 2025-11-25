package com.fabrizziodev.pruebatecsupermercado.controller;

import com.fabrizziodev.pruebatecsupermercado.model.dto.BranchDTO;
import com.fabrizziodev.pruebatecsupermercado.service.IBranchService;

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
@RequestMapping("/api/branch")
@Validated
@Tag(name = "Branch Controller", description = "All operations related to branches")
public class BranchController {

    @Autowired
    private IBranchService branchService;

    @GetMapping
    @Operation(summary = "Get all branches", description = "Get all branches available in the database")
    public ResponseEntity<List<BranchDTO>> getBranches() {
        return ResponseEntity.ok(branchService.getBranches());
    }

    @PostMapping
    @Operation(summary = "Create a new branch", description = "Create a new branch in the database")
    public ResponseEntity<BranchDTO> createBranch(@Valid @RequestBody BranchDTO branchDTO) {
        BranchDTO branch = branchService.createBranch(branchDTO);

        return ResponseEntity.created(URI.create("/api/branch/" + branch.getBranchId())).body(branch);
    }

    @PutMapping("/{branchId}")
    @Operation(summary = "Update a branch", description = "Update a branch in the database")
    public ResponseEntity<BranchDTO> updateBranch(
            @PathVariable @Positive(message = "The branch ID must be a positive number") Long branchId,
            @Valid @RequestBody BranchDTO branchDTO) {
        return ResponseEntity.ok(branchService.updateBranch(branchId, branchDTO));
    }

    @DeleteMapping("/{branchId}")
    @Operation(summary = "Delete a branch", description = "Delete a branch from the database")
    public ResponseEntity<Void> deleteBranch(
            @PathVariable @Positive(message = "The branch ID must be a positive number") Long branchId) {
        branchService.deleteBranch(branchId);

        return ResponseEntity.noContent().build();
    }
}
