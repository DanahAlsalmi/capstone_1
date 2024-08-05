package com.example.capstone1.Service;

import com.example.capstone1.Model.Product;
import com.example.capstone1.Model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class WishlistService {

    private final UserService userService;
    private final ProductService productService;


    //Add item to wishlist
    public void addItemToWishlist(int userId, int productId) {
        User user = userService.getUserById(userId);
        if (user != null) {
            Product product = productService.getProductById(productId);
            if (product != null) {
                user.getWishlist().add(product);
            }
        }
    }

    //Get user wishlist
    public ArrayList<Product> getUserWishlist(int userId) {
        User user = userService.getUserById(userId);
        if (user != null) {
            return user.getWishlist();
        }
        return null;
    }

    //Remove from user wishlist
    public void removeItemFromWishlist(int userId, int productId) {
        User user = userService.getUserById(userId);
        if (user != null) {
            ArrayList<Product> wishlist = user.getWishlist();
            for (int i = 0; i < wishlist.size(); i++) {
                if (wishlist.get(i).getId() == productId) {
                    wishlist.remove(i);
                    break;
                }
            }
        }
    }
}
