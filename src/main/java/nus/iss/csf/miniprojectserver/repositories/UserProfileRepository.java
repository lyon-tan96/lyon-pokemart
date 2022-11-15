package nus.iss.csf.miniprojectserver.repositories;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import nus.iss.csf.miniprojectserver.models.LineItems;
import nus.iss.csf.miniprojectserver.models.OrderDetails;

@Repository
public class UserProfileRepository {

    @Autowired
    private JdbcTemplate template;

    private static final String SQL_FIND_USER_ORDERS = "select * from orders where username = ? order by date";
    private static final String SQL_GET_ORDER_LINEITEMS = "select * from lineitems where order_id = ?";

    public List<OrderDetails> getUserOrders(String username) {

        List<OrderDetails> orderDetails = new LinkedList<>();

        SqlRowSet rs = template.queryForRowSet(SQL_FIND_USER_ORDERS, username);

        while (rs.next()) {
            OrderDetails orderDetail = OrderDetails.createRs(rs);
            orderDetails.add(orderDetail);
        }

        return orderDetails;
    }


    public List<LineItems> getOrderLineitems(String orderId) {

        List<LineItems> lineItems = new LinkedList<>();

        SqlRowSet rs = template.queryForRowSet(SQL_GET_ORDER_LINEITEMS, orderId);

        while(rs.next()) {
            LineItems lineItem = LineItems.createRs(rs);
            lineItems.add(lineItem);
        }

        return lineItems;
    }


    }
    
