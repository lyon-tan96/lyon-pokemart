package nus.iss.csf.miniprojectserver.services;

public class RegistrationException extends Exception {

    private String reason;

    public RegistrationException(String reason) {
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }
    
}
