package nus.iss.csf.miniprojectserver.services;

public class CartServiceException extends Exception {

    private String reason;

    public String getReason() {
        return reason;
    }

    public CartServiceException(String reason) {
        this.reason = reason;
    }
    
}
