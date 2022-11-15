package nus.iss.csf.miniprojectserver.models;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class LineItems {
    
    private String orderId;
    private String cardName;
    private String cardId;
    private String image;
    private String price;
    private String username;
    private Integer quantity;

    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getOrderId() {
        return orderId;
    }
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    public String getCardName() {
        return cardName;
    }
    public void setCardName(String cardName) {
        this.cardName = cardName;
    }
    public String getCardId() {
        return cardId;
    }
    public void setCardId(String cardId) {
        this.cardId = cardId;
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

    public static LineItems createRs(SqlRowSet rs) {

        LineItems lineItems = new LineItems();

        lineItems.setOrderId(rs.getString("order_id"));
        lineItems.setImage(rs.getString("image"));
        lineItems.setCardName(rs.getString("card_name"));
        lineItems.setCardId(rs.getString("card_id"));
        lineItems.setPrice(rs.getString("price"));
        lineItems.setQuantity(rs.getInt("quantity"));

        return lineItems;
    }
    
    public JsonObject toJson() {
        return Json.createObjectBuilder()
            .add("orderId", orderId)
            .add("image", image)
            .add("cardName", cardName)
            .add("card_id", cardId)
            .add("price", price)
            .add("quantity", quantity)
            .build();
    }
    
}
