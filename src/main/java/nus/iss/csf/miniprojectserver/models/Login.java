package nus.iss.csf.miniprojectserver.models;

import java.io.StringReader;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class Login {
    
    private String email;
    private String password;

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public static Login create(String json) {
        JsonReader reader = Json.createReader(new StringReader(json));
        JsonObject details = reader.readObject();

        final Login login = new Login();
        login.setEmail(details.getString("email"));
        login.setPassword(details.getString("password"));

        return login;
    }

//    public JsonObject toJson() {
//        return Json.createObjectBuilder()
//                .add("email", email)
//                .build();
//    }
    
}
