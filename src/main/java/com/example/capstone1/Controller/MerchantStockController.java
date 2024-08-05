package com.example.capstone1.Controller;
import com.example.capstone1.Api.ApiResponse;
import com.example.capstone1.Model.MerchantStock;
import com.example.capstone1.Service.MerchantStockService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/merchant-stock")
@RequiredArgsConstructor
public class MerchantStockController {

    private final MerchantStockService merchantStockService;

    //Get All merchant stock
    @GetMapping("/get")
    public ResponseEntity getMerchantStock() {
        return ResponseEntity.status(200).body(merchantStockService.getMerchantStocks());
    }

    //Add merchant stock
    @PostMapping("/add")
    public ResponseEntity addMerchantStock(@Valid @RequestBody MerchantStock merchantStock, Errors errors) {
        if (errors.hasErrors()) {
            String msg = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(msg);
        }
        merchantStockService.addMerchantStock(merchantStock);
        return ResponseEntity.status(200).body(new ApiResponse("Merchant Stock Added Successfully"));
    }

    //Update merchant stock
    @PutMapping("/update/{id}")
    public ResponseEntity updateMerchantStock(@PathVariable int id ,@Valid @RequestBody MerchantStock merchantStock, Errors errors) {
        if (errors.hasErrors()) {
            String msg = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(msg);
        }
        boolean isUpdated = merchantStockService.updateMerchantStock(id,merchantStock);
        if (isUpdated) {
            return ResponseEntity.status(200).body(new ApiResponse("Merchant Stock Updated"));
        }
        return ResponseEntity.status(404).body(new ApiResponse("Merchant Stock Not Found"));
    }

    //Delete merchant stock
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteMerchantStock(@PathVariable int id) {
        boolean isDeleted = merchantStockService.deleteMerchantStock(id);
        if (isDeleted) {
            return ResponseEntity.status(200).body(new ApiResponse("Merchant Stock Deleted"));
        }
        return ResponseEntity.status(404).body(new ApiResponse("Merchant Stock Not Found"));
    }

    // Add stock to existing Merchant Stock
    @PutMapping("/add-stock/{productId}/{merchantId}/{additionalStock}")
    public ResponseEntity addStock(@PathVariable int productId, @PathVariable int merchantId, @PathVariable int additionalStock) {
        boolean isAdded = merchantStockService.addStock(productId, merchantId, additionalStock);
        if (isAdded) {
            return ResponseEntity.status(200).body(new ApiResponse("Stock added successfully"));
        } else{
            return ResponseEntity.status(400).body(new ApiResponse("Invalid product ID or merchant ID"));
        }
    }
}
