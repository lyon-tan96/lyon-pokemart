package nus.iss.csf.miniprojectserver.services;

public class CardSearchException extends Exception{

    private String reason;

    public String getReason() {
        return reason;
    }

    public CardSearchException(String reason) {
        this.reason = reason;
    }

    
}
