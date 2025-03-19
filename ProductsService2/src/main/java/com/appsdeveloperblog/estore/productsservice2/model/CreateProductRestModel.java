package com.appsdeveloperblog.estore.productsservice2.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductRestModel {
//    @NotBlank(message = "Product title is a required field")
    private String title;
    @Min(message = "The minimum value allowed is 10.0", value = 10)
    private BigDecimal price;
    @Min(message = "The minimum quantity allowed is 1", value = 1)
    @Max(message = "The maximum quantity allowed is 10", value = 10)
    private int quantity;
}