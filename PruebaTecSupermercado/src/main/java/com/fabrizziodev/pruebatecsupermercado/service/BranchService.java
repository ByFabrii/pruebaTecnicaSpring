package com.fabrizziodev.pruebatecsupermercado.service;

import com.fabrizziodev.pruebatecsupermercado.db.entity.Branch;
import com.fabrizziodev.pruebatecsupermercado.db.repository.BranchRepository;
import com.fabrizziodev.pruebatecsupermercado.exception.NotFoundException;
import com.fabrizziodev.pruebatecsupermercado.mapper.Mapper;
import com.fabrizziodev.pruebatecsupermercado.model.dto.BranchDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BranchService implements IBranchService {

    @Autowired
    private BranchRepository branchRepository;

    @Override
    public List<BranchDTO> getBranches() {
        return branchRepository.findAll().stream().map(Mapper::toDTO).toList();
    }

    @Override
    public BranchDTO createBranch(BranchDTO branchDto) {
        Branch branch = Branch.builder()
                .name(branchDto.getName())
                .address(branchDto.getAddress())
                .build();

        return Mapper.toDTO(branchRepository.save(branch));
    }

    @Override
    public BranchDTO updateBranch(Long branchId, BranchDTO branchDto) {
        Branch branch = branchRepository.findById(branchId).orElseThrow(() -> new NotFoundException("Branch not found"));

        branch.setName(branchDto.getName());
        branch.setAddress(branchDto.getAddress());
        
        return Mapper.toDTO(branchRepository.save(branch));
    }

    @Override
    public void deleteBranch(Long branchId) {
        if (!branchRepository.existsById(branchId)) {
            throw new NotFoundException("Branch not found");
        }
        branchRepository.deleteById(branchId);
    }
}
