package com.fabrizziodev.pruebatecsupermercado.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BranchDTO {
    private Long branchId;
    private String name;
    private String address;
}
