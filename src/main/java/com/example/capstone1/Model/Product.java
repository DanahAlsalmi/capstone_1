package com.example.capstone1.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Product {
    @NotNull(message = "ID must not be empty")
    private int id;

    @NotEmpty(message = "Name must not be empty")
    @Size(min = 3, message = "Name must be more than 3 characters")
    private String productName;

    @NotNull(message = "Price must be not Null")
    @Positive(message = "Price must be positive")
    private double productPrice;

    @NotNull(message = "Category ID must not be empty")
    private int productCategoryId;


//    private int productQuantity;
//    private String productDescription;

}
