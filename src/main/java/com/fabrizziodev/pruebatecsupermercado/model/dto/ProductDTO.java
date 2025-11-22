package com.fabrizziodev.pruebatecsupermercado.model.dto;
import java.math.BigDecimal;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Builder;


@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDTO {

    @Positive(message = "The product ID must be a positive number")
    private Long productId;
    
    @NotBlank(message = "The product name cannot be blank")
    @Size(min = 3, max = 50, message = "The product name must be between 3 and 50 characters")
    private String name;
    
    @NotBlank(message = "The product category cannot be blank")
    @Size(min = 3, max = 50, message = "The product category must be between 3 and 50 characters")
    private String category;
    
    @Positive(message = "The product price must be a positive number")
    @NotNull(message = "The product price cannot be null")
    private BigDecimal price;
    
    @Positive(message = "The product quantity must be a positive number")
    @Min(value = 0, message = "The product quantity must be at least 1")
    private int quantity;
}
