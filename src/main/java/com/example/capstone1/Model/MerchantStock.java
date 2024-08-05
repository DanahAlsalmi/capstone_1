package com.example.capstone1.Model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MerchantStock {

    @NotNull(message = "ID must not be empty")
    private int id;

    @NotNull(message = "Product ID must not be empty")
    private int productId;

    @NotNull(message = "Merchant ID must not be empty")
    private int merchantId;

    @NotNull(message = "Stock must be not null")
    @Min(value = 10, message = "Stock must be more than 10 at start")
    private int stock;



}
