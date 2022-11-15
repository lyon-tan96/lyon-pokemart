package nus.iss.csf.miniprojectserver.repositories;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import nus.iss.csf.miniprojectserver.models.Cart;

@Repository
public class CartRepository {

    @Autowired
    private JdbcTemplate template;

    private static final String SQL_INSERT_CART_ITEM = "insert into cart(username, image, card_name, card_id, price, quantity) values(?,?,?,?,?,?)";
    private static final String SQL_GET_USER_CART_ITEM = "select * from cart where username = ?";
    private static final String SQL_GET_CART_ITEM_BY_ID = "select * from cart where cart_id = ?";
    private static final String SQL_GET_CART_ITEM_BY_USER = "select * from cart where username = ?";
    private static final String SQL_DELETE_CART_ITEM_ID = "delete from cart where cart_id = ?";
    private static final String SQL_DELETE_CART_ITEM_USER = "delete from cart where username = ?";

    public boolean insertCartItems(Cart cartItem) {

        int updated = template.update(SQL_INSERT_CART_ITEM, cartItem.getUsername(), cartItem.getImage(), cartItem.getCardName(), cartItem.getCardId(), cartItem.getPrice(), cartItem.getQuantity());
        return 1 == updated;
    }
    
    public List<Cart> getUserCartItems (String username) {

        List<Cart> cartItems = new LinkedList<>();

        SqlRowSet rs = template.queryForRowSet(SQL_GET_USER_CART_ITEM, username);
        while (rs.next()) {
            Cart cartItem = Cart.createRs(rs);
            cartItems.add(cartItem);
        }
        
        return cartItems;
    }

    public Optional<Cart> getCartItemById (Integer cartId) {
        SqlRowSet rs = template.queryForRowSet(SQL_GET_CART_ITEM_BY_ID, cartId);
        if (!rs.next()) {
            return Optional.empty();
        }
        
        Cart c = new Cart();
        c = Cart.createRs(rs);

        return Optional.of(c);
    }

    public boolean deleteCartItemById(Integer cartId) {
        int updated = template.update(SQL_DELETE_CART_ITEM_ID, cartId);
        return 1 == updated;
    }

    public Optional<Cart> getCartItemByUser (String username) {
        SqlRowSet rs = template.queryForRowSet(SQL_GET_CART_ITEM_BY_USER, username);
        if (!rs.next()) {
            return Optional.empty();
        }
        
        Cart c = new Cart();
        c = Cart.createRs(rs);

        return Optional.of(c);
    }

    public boolean deleteCartItemsByUser(String username) {
        int updated = template.update(SQL_DELETE_CART_ITEM_USER, username);
        return 1 == updated;
    }
}
