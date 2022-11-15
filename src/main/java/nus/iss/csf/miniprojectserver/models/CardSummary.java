package nus.iss.csf.miniprojectserver.models;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class CardSummary {
    
    public String image;
    public String cardName;
    public String price;
    public String id;

    public String getCardName() {
        return cardName;
    }
    public void setCardName(String cardName) {
        this.cardName = cardName;
    }
        
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }

    public String getPrice() {
        return price;
    }
    public void setPrice(String price) {
        this.price = price;
    }

    public JsonObject toJson() {
        return Json.createObjectBuilder()
            .add("image", image)
            .add("cardName", cardName)
            .add("price", price)
            .add("id", id)
            .build();
    }
    
    
}
