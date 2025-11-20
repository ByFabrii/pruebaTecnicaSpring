package com.fabrizziodev.pruebatecsupermercado.service;

import com.fabrizziodev.pruebatecsupermercado.dto.BranchDTO;
import com.fabrizziodev.pruebatecsupermercado.model.Branch;

import java.util.List;

public interface IBranchService {

    List<BranchDTO> getBranches();
    BranchDTO createBranch(BranchDTO branchDto);
    BranchDTO updateBranch(Long branchId, BranchDTO branchDto);
    void deleteBranch(Long branchId);

}
