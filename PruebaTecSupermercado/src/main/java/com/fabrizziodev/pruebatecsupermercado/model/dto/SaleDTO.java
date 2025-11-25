package com.fabrizziodev.pruebatecsupermercado.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaleDTO {

    @Positive(message = "The sale ID must be a positive number")
    @Schema(description = "The sale ID", example = "1")
    private Long saleId;

    @NotNull(message = "The date cannot be null")
    @Schema(description = "The sale date", example = "2023-01-01")
    private LocalDate date;

    @NotBlank(message = "The status cannot be blank")
    @Schema(description = "The sale status", example = "PENDING")
    private String status;

    @PositiveOrZero(message = "The total must be a positive number or zero")
    @NotNull(message = "The total cannot be null")
    @Schema(description = "The sale total", example = "10.00")
    private BigDecimal total;

    @Positive(message = "The branch ID must be a positive number")
    @NotNull(message = "The branch ID cannot be null")
    @Schema(description = "The branch ID", example = "1")
    private Long branchId;

    @NotEmpty(message = "The sale details list cannot be empty")
    @Schema(description = "The sale details list", example = "[1, 2, 3]")
    private List<SaleDetailDTO> saleDetails;
}
