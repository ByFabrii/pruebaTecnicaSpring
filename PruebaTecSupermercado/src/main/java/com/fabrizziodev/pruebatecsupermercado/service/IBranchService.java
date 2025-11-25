package com.fabrizziodev.pruebatecsupermercado.service;

import java.util.List;

import com.fabrizziodev.pruebatecsupermercado.model.dto.BranchDTO;

public interface IBranchService {

    List<BranchDTO> getBranches();
    BranchDTO createBranch(BranchDTO branchDto);
    BranchDTO updateBranch(Long branchId, BranchDTO branchDto);
    void deleteBranch(Long branchId);

}
