package nus.iss.csf.miniprojectserver.repositories;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import nus.iss.csf.miniprojectserver.models.LineItems;
import nus.iss.csf.miniprojectserver.models.OrderDetails;

@Repository
public class CheckoutRepository {

    @Autowired
    private JdbcTemplate template;
    
    private final static String SQL_INSERT_ORDER = "insert into orders(order_id, username, name, contact, postal, address1, address2, amount, status) values(?,?,?,?,?,?,?,?,?)";
    private final static String SQL_INSERT_LINEITEMS = "insert into lineitems(order_id, image, card_name, card_id, price, username, quantity) values(?,?,?,?,?,?,?)";
    private final static String SQL_DELETE_CART_ITEMS = "delete from cart where username = ?";
    private final static String SQL_FIND_DUPLICATE_CARD_ORDER = "select * from lineitems where order_id = ? and card_id = ?";
    private final static String SQL_UPDATE_CARD_QUANTITY = "update lineitems set quantity = ? where order_id = ? and card_id = ?";

    public boolean createNewOrder(OrderDetails order) {

        int updated = template.update(SQL_INSERT_ORDER, order.getOrderId(), order.getUsername(), order.getName(), order.getContact(), order.getPostal(), order.getAddress1(), order.getAddress2(), order.getAmount(), order.getStatus());
        return 1 == updated;
    }

    public boolean insertLineItems(LineItems lineItems) {

        int updated = template.update(SQL_INSERT_LINEITEMS, lineItems.getOrderId(), lineItems.getImage(), lineItems.getCardName(), lineItems.getCardId(), lineItems.getPrice(), lineItems.getUsername(), lineItems.getQuantity());
        return 1 == updated;
    }

    public boolean deleteCartItems(String username) {
        int updated = template.update(SQL_DELETE_CART_ITEMS, username);
        return updated == 1;
    }

    public Optional<Integer> findDuplicateCardOrder(String orderId, String cardId) {
        SqlRowSet rs = template.queryForRowSet(SQL_FIND_DUPLICATE_CARD_ORDER, orderId, cardId);

        if(!rs.next()) {
            return Optional.empty();
        } else {
            return Optional.of(rs.getInt("quantity"));
        }
    }

    public boolean updateCardQuantity(Integer quantity, String orderId, String cardId) {

        int updated = template.update(SQL_UPDATE_CARD_QUANTITY, quantity, orderId, cardId);
        return 1 == updated;
    }
    




}
