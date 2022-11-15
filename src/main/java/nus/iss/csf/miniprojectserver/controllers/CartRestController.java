package nus.iss.csf.miniprojectserver.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import nus.iss.csf.miniprojectserver.models.Cart;
import nus.iss.csf.miniprojectserver.models.Response;
import nus.iss.csf.miniprojectserver.services.CartService;
import nus.iss.csf.miniprojectserver.services.CartServiceException;

@RestController
@RequestMapping(path = "/api")
public class CartRestController {
    
    @Autowired
    private CartService cartSvc;

    @PostMapping(path = "/addToCart", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> insertCartItems(@RequestBody String payload) {

        Cart cartItem = Cart.create(payload);

        cartSvc.insertCartItems(cartItem);

        JsonObject data = Json.createObjectBuilder()
                    .add("username", cartItem.getUsername())
                    .add("image", cartItem.getImage())
                    .add("cardName", cartItem.getCardName())
                    .add("id", cartItem.getCardId())
                    .add("price", cartItem.getPrice())
                    .add("quantity", cartItem.getQuantity())
                    .build();


        return ResponseEntity.status(HttpStatus.CREATED).body(data.toString());
    }

    @GetMapping(path = "{username}/getCart", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getCartItems(@PathVariable String username) {
        List<Cart> cartItems = cartSvc.getUserCartItems(username);
        
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        for (Cart cartItem: cartItems)
            arrayBuilder.add(cartItem.toJson());
            
            return ResponseEntity.ok(arrayBuilder.build().toString());
        }

    @DeleteMapping(path = "/deleteCart/{cartId}")
    public ResponseEntity<String> deleteCartItemById(@PathVariable Integer cartId) {
        try {
            cartSvc.deleteCartItemById(cartId);
        } catch (CartServiceException e) {
            System.out.println(e.getReason());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.toString());
        }

        Response resp = new Response();
        resp.setMessage("Cart item(s) successfully deleted.");
        resp.setCode(200);

        return ResponseEntity.status(HttpStatus.OK).body(resp.toJson().toString());
    }

    @DeleteMapping(path = "/deleteCart/user/{username}")
    public ResponseEntity<String> deleteCartItemByUser(@PathVariable String username) {
        try {
            cartSvc.deleteCartItemsByUser(username);
        } catch (CartServiceException e) {
            System.out.println(e.getReason());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.toString());
        }

        Response resp = new Response();
        resp.setMessage("Cart item(s) successfully deleted.");
        resp.setCode(200);

        return ResponseEntity.status(HttpStatus.OK).body(resp.toJson().toString());

    }
        
}
    

