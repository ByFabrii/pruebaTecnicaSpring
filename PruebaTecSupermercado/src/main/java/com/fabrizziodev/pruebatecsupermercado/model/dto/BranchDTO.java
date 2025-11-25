package com.fabrizziodev.pruebatecsupermercado.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BranchDTO {

    @Positive(message = "The branch ID must be a positive number")
    @Schema(description = "The branch ID", example = "1")
    private Long branchId;

    @NotBlank(message = "The branch name cannot be blank")
    @Size(min = 3, max = 50, message = "The branch name must be between 3 and 50 characters")
    @Schema(description = "The branch name", example = "Branch 1")
    private String name;

    @NotBlank(message = "The branch address cannot be blank")
    @Size(min = 3, max = 150, message = "The branch address must be between 3 and 150 characters")
    @Schema(description = "The branch address", example = "Address 1")
    private String address;
}
