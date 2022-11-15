package nus.iss.csf.miniprojectserver.models;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class Response {
    
    private int code = 0;
    private String message = "";

    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public JsonObject toJson() {
        return Json.createObjectBuilder()
                .add("message", message)
                .add("code", code)
                .build();
    }
    
}
