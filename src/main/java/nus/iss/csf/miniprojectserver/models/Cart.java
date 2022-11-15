package nus.iss.csf.miniprojectserver.models;

import java.io.StringReader;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class Cart { 
    
    private Integer cartId;
    private String username;
    private String image;
    private String cardName;
    private String cardId;
    private String price;
    private Integer quantity;

    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    public Integer getCartId() {
        return cartId;
    }
    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
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
    public String getPrice() {
        return price;
    }
    public void setPrice(String price) {
        this.price = price;
    }

    public static Cart create(String json) {
        JsonReader reader = Json.createReader(new StringReader(json));
        JsonObject obj = reader.readObject();

        Cart cartItem = new Cart();
        cartItem.setUsername(obj.getString("username"));
        cartItem.setImage(obj.getString("image"));
        cartItem.setCardName(obj.getString("cardName"));
        cartItem.setCardId(obj.getString("cardId"));
        cartItem.setPrice(obj.getString("price"));
        cartItem.setQuantity(obj.getInt("quantity"));

        return cartItem;

    }

    public static Cart createRs(SqlRowSet rs) {
        Cart c = new Cart();
        c.setCartId(rs.getInt("cart_id"));
        c.setUsername(rs.getString("username"));
        c.setImage(rs.getString("image"));
        c.setCardName(rs.getString("card_name"));
        c.setCardId(rs.getString("card_id"));
        c.setPrice(rs.getString("price"));
        c.setQuantity(rs.getInt("quantity"));

        return c;
    }

    public JsonObject toJson() {
        return Json.createObjectBuilder()
            .add("cartId", cartId)
            .add("username", username)
            .add("image", image)
            .add("cardName", cardName)
            .add("cardId", cardId)
            .add("price", price)
            .add("quantity", quantity)
            .build();
    }

}
