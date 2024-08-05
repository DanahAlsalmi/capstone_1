package com.example.capstone1.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class User {

    @NotNull(message = "ID must not be empty")
    private int id;

    @NotEmpty(message = "Username must not be empty")
    @Size(min = 5, message = "Username must be more than 5 characters long")
    private String username;

    @NotEmpty(message = "Password must not be empty")
    @Size(min = 6, message = "Password must be more than 6 characters long")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-zA-Z]).{6,}$", message = "Password must contain letter and number")
    private String password;

    @NotEmpty(message = "Email must not be empty")
    @Email(message = "Email should be contain '@' ")
    private String email;

    @NotEmpty(message = "Role must not be empty")
    @Pattern(regexp = "Admin|Customer", message = "Role must be either Admin or Customer")
    private String role;

    @NotNull(message = "Balance must not be empty")
    @Positive(message = "Balance must be positive")
    private double balance;

    @AssertFalse
    private boolean isPrime;
    private LocalDate primeStartDate;
    private LocalDate primeEndDate;



    //Wish List
    private ArrayList<Product> wishlist;

    public User() {
        this.wishlist = new ArrayList<>(); // Initialize the wishlist
    }
}
