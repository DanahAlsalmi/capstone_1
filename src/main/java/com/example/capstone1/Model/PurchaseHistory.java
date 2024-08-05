package com.example.capstone1.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class PurchaseHistory {

    private int userId;
    private int productId;
    private int merchantId;
    private int quantity;
    private double totalPrice;
    private LocalDateTime purchaseDate;

}
