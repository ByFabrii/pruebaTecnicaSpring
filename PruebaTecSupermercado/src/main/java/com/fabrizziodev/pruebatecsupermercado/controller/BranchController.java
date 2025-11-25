package com.fabrizziodev.pruebatecsupermercado.controller;

import com.fabrizziodev.pruebatecsupermercado.model.dto.BranchDTO;
import com.fabrizziodev.pruebatecsupermercado.service.IBranchService;

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
public class BranchController {

    @Autowired
    private IBranchService branchService;

    @GetMapping
    public ResponseEntity<List<BranchDTO>> getBranches() {
        return ResponseEntity.ok(branchService.getBranches());
    }

    @PostMapping
    public ResponseEntity<BranchDTO> createBranch(@Valid @RequestBody BranchDTO branchDTO) {
        BranchDTO branch = branchService.createBranch(branchDTO);

        return ResponseEntity.created(URI.create("/api/branch/" + branch.getBranchId())).body(branch);
    }

    @PutMapping("/{branchId}")
    public ResponseEntity<BranchDTO> updateBranch(@PathVariable @Positive(message = "The branch ID must be a positive number") Long branchId, @Valid @RequestBody BranchDTO branchDTO) {
        return ResponseEntity.ok(branchService.updateBranch(branchId, branchDTO));
    }

    @DeleteMapping("/{branchId}")
    public ResponseEntity<Void> deleteBranch(@PathVariable @Positive(message = "The branch ID must be a positive number") Long branchId) {
        branchService.deleteBranch(branchId);

        return ResponseEntity.noContent().build();
    }
}
