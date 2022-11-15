package nus.iss.csf.miniprojectserver.models;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class OrderDetails {

    private String orderId;
    private String username;
    private String name;
    private Integer contact;
    private String postal;
    private String address1;
    private String address2;
    private String amount;
    private String status;
    private String date;
    
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getOrderId() {
        return orderId;
    }
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getContact() {
        return contact;
    }
    public void setContact(Integer contact) {
        this.contact = contact;
    }
    public String getPostal() {
        return postal;
    }
    public void setPostal(String postal) {
        this.postal = postal;
    }
    public String getAddress1() {
        return address1;
    }
    public void setAddress1(String address1) {
        this.address1 = address1;
    }
    public String getAddress2() {
        return address2;
    }
    public void setAddress2(String address2) {
        this.address2 = address2;
    }
    public String getAmount() {
        return amount;
    }
    public void setAmount(String amount) {
        this.amount = amount;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public static OrderDetails create(JsonObject obj) {

            JsonObject details = obj.getJsonObject("details");

            OrderDetails orderDetails = new OrderDetails();
            orderDetails.setName(details.getString("name"));
            orderDetails.setContact(details.getInt("contact"));
            orderDetails.setPostal(details.getString("postal"));
            orderDetails.setAddress1(details.getString("address1"));
            orderDetails.setAddress2(details.getString("address2"));
            orderDetails.setAmount(details.getString("amount"));
            orderDetails.setUsername(details.getString("username"));  
            
        return orderDetails;
    }

    public static OrderDetails createRs(SqlRowSet rs) {

        OrderDetails orderDetail = new OrderDetails();
        orderDetail.setOrderId(rs.getString("order_id"));
        orderDetail.setName(rs.getString("name"));
        orderDetail.setContact(rs.getInt("contact"));
        orderDetail.setAddress1(rs.getString("address1"));
        orderDetail.setAddress2(rs.getString("address2"));
        orderDetail.setPostal(rs.getString("postal"));
        orderDetail.setAmount(rs.getString("amount"));
        orderDetail.setStatus(rs.getString("status"));
        orderDetail.setDate(rs.getString("date"));

        return orderDetail;

    }

    public JsonObject toJson() {
        return Json.createObjectBuilder()
            .add("orderId", orderId)
            .add("name", name)
            .add("contact", contact)
            .add("address", address1 + " " + address2)
            .add("postal", postal)
            .add("amount", amount)
            .add("status", status)
            .add("date", date)
            .build();
    }
    
}
