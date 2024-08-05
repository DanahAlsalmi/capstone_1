package com.example.capstone1.Service;

import com.example.capstone1.Model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class UserService {

    //Array for Users
    ArrayList<User> users = new ArrayList<>();

    //Get users
    public ArrayList<User> getUsers() {
        return users;
    }

    //Add User
    public void addUser(User user) {
        users.add(user);
    }

    //Update user
    public boolean updateUser(int id ,User user) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId() == id) {
                users.set(i, user);
                return true;
            }
        }
        return false;
    }

    //Delete user
    public boolean deleteUser(int id) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId() == id) {
                users.remove(i);
                return true;
            }
        }
        return false;
    }

    //Get user by ID
    public User getUserById(int userId) {
        for (User user : users) {
            if (user.getId() == userId) {
                return user;
            }
        }
        return null;
    }

    //Prime subscribe
    public User subscribeToPrime(int userId) {
        User user = getUserById(userId);
        if (user != null && user.getBalance() >= 100) {
            user.setBalance(user.getBalance() - 100);
            user.setPrime(true);
            user.setPrimeStartDate(LocalDate.now());
            user.setPrimeEndDate(LocalDate.now().plusYears(1));
            return user;
        }
        return null;
    }

    //Cancel Prime
    public boolean cancelPrimeSubscription(int userId) {
        User user = getUserById(userId);
        if (user != null && user.isPrime()) {
            user.setPrime(false);
            user.setPrimeStartDate(null);
            user.setPrimeEndDate(null);
            return true;
        }
        return false;
    }

}
