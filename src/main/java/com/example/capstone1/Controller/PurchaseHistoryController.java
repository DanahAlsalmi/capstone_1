package com.example.capstone1.Controller;

import com.example.capstone1.Model.PurchaseHistory;
import com.example.capstone1.Service.PurchaseHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/purchase-history")
@RequiredArgsConstructor
public class PurchaseHistoryController {

    final PurchaseHistoryService purchaseHistoryService;

    //Purchase History
    @GetMapping("/user/{userId}")
    public ResponseEntity getUserPurchaseHistory(@PathVariable int userId) {
        ArrayList<PurchaseHistory> purchaseHistory = purchaseHistoryService.getUserPurchaseHistory(userId);
        return ResponseEntity.status(200).body(purchaseHistory);
    }
}
