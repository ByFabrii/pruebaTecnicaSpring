package com.fabrizziodev.pruebatecsupermercado.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaleDetailDTO {

    @Positive(message = "The sale detail ID must be a positive number")
    private Long saleDetailId;

    @NotBlank(message = "The product name cannot be blank")
    @Size(min = 3, max = 100, message = "The product name must be between 3 and 50 characters")
    private String productName;

    @Positive(message = "The product quantity must be a positive number")
    @NotNull(message = "The product quantity cannot be null")
    private Integer productQuantity;

    @Positive(message = "The price must be a positive number")
    @NotNull(message = "The price cannot be null")
    private BigDecimal price;

    @Positive(message = "The sub-total must be a positive number")
    @NotNull(message = "The sub-total cannot be null")
    private BigDecimal subTotal;
}
