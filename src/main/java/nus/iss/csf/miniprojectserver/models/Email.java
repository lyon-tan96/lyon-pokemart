package nus.iss.csf.miniprojectserver.models;

public class Email {

    private String username;
    private String orderId;
    private String name;
    private String amount;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getAmount() {
        return amount;
    }
    public void setAmount(String amount) {
        this.amount = amount;
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
    
    
}
