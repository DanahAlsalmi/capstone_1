package com.example.capstone1.Service;

import com.example.capstone1.Model.MerchantStock;
import com.example.capstone1.Model.Product;
import com.example.capstone1.Model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class ProductService {


    private final ArrayList<Product> products = new ArrayList<>();
    private final UserService userService;
    private final MerchantStockService merchantStockService;
    private final PurchaseHistoryService purchaseHistoryService;

    //Get Products
    public ArrayList<Product> getProducts() {
        return products;
    }

    //Add Product
    public void addProduct(Product product) {
        products.add(product);
    }

    //Update product
    public boolean updateProduct(int id, Product product) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == id) {
                products.set(i, product);
                return true;
            }
        }
        return false;
    }

    //Delete product
    public boolean deleteProduct(int id) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == id) {
                products.remove(i);
                return true;
            }
        }
        return false;
    }

    // Get product by ID
    public Product getProductById(int productId) {
        for (Product product : products) {
            if (product.getId() == productId) {
                return product;
            }
        }
        return null;
    }

    //User Buy
    public String purchase(int userId, int productId, int merchantId) {
        // Validate user ID
        User user = null;
        for (User u : userService.getUsers()) {
            if (u.getId() == userId) {
                user = u;
                break;
            }
        }
        if (user == null) {
            return "Invalid user ID";
        }

        if ("Admin".equalsIgnoreCase(user.getRole())) {
            return "Admins cannot make purchases";
        }

        // Validate product ID
        Product product = null;
        for (Product p : products) {
            if (p.getId() == productId) {
                product = p;
                break;
            }
        }
        if (product == null) {
            return "Invalid product ID";
        }

        // Validate merchant ID and check stock availability
        MerchantStock merchantStock = null;
        for (MerchantStock stock : merchantStockService.getMerchantStocks()) {
            if (stock.getProductId() == productId && stock.getMerchantId() == merchantId) {
                merchantStock = stock;
                break;
            }
        }
        if (merchantStock == null) {
            return "Invalid merchant ID or product not available with this merchant";
        }

        // Check stock availability
        if (merchantStock.getStock() <= 0) {
            return "Product out of stock";
        }

        // Check user balance
        double productPrice = product.getProductPrice();
        if (user.getBalance() < productPrice) {
            return "Insufficient balance";
        }

        // Deduct stock and balance
        merchantStock.setStock(merchantStock.getStock() - 1);
        user.setBalance(user.getBalance() - productPrice);
        userService.updateUser(userId, user);

        //save to History
        purchaseHistoryService.recordPurchase(userId, productId, merchantId, 1, productPrice);

        return "Purchase successful";
    }

}
