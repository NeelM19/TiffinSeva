package com.example.fooddeliveryapplication.chefFoodPanel;

public class FoodDetails {

    public String Quantity,Price,Description,ImageURL,RandomUID,Chefid;

    public FoodDetails(String quantity, String price, String description, String imageURL, String randomUID, String chefid, String chefId) {
        Quantity = quantity;
        Price = price;
        Description = description;
        ImageURL = imageURL;
        RandomUID = randomUID;
        Chefid = chefid;
    }
}
