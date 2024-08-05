package com.example.capstone1.Controller;
import com.example.capstone1.Api.ApiResponse;
import com.example.capstone1.Model.Product;
import com.example.capstone1.Service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    //Get All Products
    @GetMapping("/get")
    public ResponseEntity getAllProducts() {
        return ResponseEntity.status(200).body(productService.getProducts());
    }

    //Add product
    @PostMapping("/add")
    public ResponseEntity addProduct(@Valid @RequestBody Product product , Errors errors) {
        if (errors.hasErrors()) {
            String msg = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(msg);
        }
        productService.addProduct(product);
        return ResponseEntity.status(201).body(new ApiResponse("Product added successfully"));
    }

    //Update product
    @PutMapping("/update/{productId}")
    public ResponseEntity updateProduct(@PathVariable int productId, @Valid @RequestBody Product product, Errors errors) {
        if (errors.hasErrors()) {
            String msg = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(msg);
        }
        boolean isUpdated = productService.updateProduct(productId, product);
        if (isUpdated) {
            return ResponseEntity.status(201).body(new ApiResponse("Product updated successfully"));
        }
        return ResponseEntity.status(404).body(new ApiResponse("Product not found"));
    }

    //Delete product
    @DeleteMapping("/delete/{productId}")
    public ResponseEntity deleteProduct(@PathVariable int productId) {
        boolean isDeleted = productService.deleteProduct(productId);
        if (isDeleted) {
            return ResponseEntity.status(201).body(new ApiResponse("Product deleted successfully"));
        }
        return ResponseEntity.status(404).body(new ApiResponse("Product not found"));
    }

    //User buy
    @PostMapping("/buy/{userId}/{productId}/{merchantId}")
    public ResponseEntity buyProduct(@PathVariable int userId, @PathVariable int productId, @PathVariable int merchantId) {
        String result = productService.purchase(userId, productId, merchantId);
        if (result.equals("Purchase successful")) {
            return ResponseEntity.status(200).body(new ApiResponse(result));
        } else {
            return ResponseEntity.status(400).body(new ApiResponse(result));
        }
    }

}
