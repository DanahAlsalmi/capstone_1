package com.example.capstone1.Controller;
import com.example.capstone1.Api.ApiResponse;
import com.example.capstone1.Model.Merchant;
import com.example.capstone1.Service.MerchantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/merchant")
@RequiredArgsConstructor
public class MerchantController {

    private final MerchantService merchantService;

    //Get All merchant
    @GetMapping("/merchants")
    public ResponseEntity getMerchants() {
        return ResponseEntity.status(200).body(merchantService.getMerchants());
    }

    //Add merchant
    @PostMapping("/add")
    public ResponseEntity addMerchant(@Valid @RequestBody Merchant merchant, Errors errors) {
        if (errors.hasErrors()) {
            String msg = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(msg);
        }
        merchantService.addMerchant(merchant);
        return ResponseEntity.status(201).body(new ApiResponse("Merchant added successfully"));
    }

    //Update merchant
    @PutMapping("/update/{merchantId}")
    public ResponseEntity updateMerchant(@PathVariable int merchantId, @Valid @RequestBody Merchant merchant, Errors errors) {
        if (errors.hasErrors()) {
            String msg = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(msg);
        }
        boolean isUpdated = merchantService.updateMerchant(merchantId, merchant);
        if (isUpdated) {
            return ResponseEntity.status(201).body(new ApiResponse("Merchant updated successfully"));
        }
        return ResponseEntity.status(404).body(new ApiResponse("Merchant not found"));
    }

    //Delete merchant
    @DeleteMapping("/delete/{merchantId}")
    public ResponseEntity deleteMerchant(@PathVariable int merchantId) {
        boolean isDeleted = merchantService.deleteMerchant(merchantId);
        if (isDeleted) {
            return ResponseEntity.status(201).body(new ApiResponse("Merchant deleted successfully"));
        }
        return ResponseEntity.status(404).body(new ApiResponse("Merchant not found"));
    }

}
