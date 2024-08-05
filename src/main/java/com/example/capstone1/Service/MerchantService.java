package com.example.capstone1.Service;

import com.example.capstone1.Model.Merchant;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MerchantService {

    //Array for Merchant
    ArrayList<Merchant> merchants = new ArrayList<>();

    //Get Merchant
    public ArrayList<Merchant> getMerchants() {
        return merchants;
    }

    //Add Merchant
    public void addMerchant(Merchant merchant) {
        merchants.add(merchant);
    }

    //Update Merchant
    public boolean updateMerchant(int id ,Merchant merchant) {
        for (int i = 0; i < merchants.size(); i++) {
            if (merchants.get(i).getId() == id) {
                merchants.set(i,merchant);
                return true;
            }
        }
        return false;
    }

    //Delete Merchant
    public boolean deleteMerchant(int id) {
        for (int i = 0; i < merchants.size(); i++) {
            if (merchants.get(i).getId() == id) {
                merchants.remove(i);
                return true;
            }
        }
        return false;
    }
}
