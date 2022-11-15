package nus.iss.csf.miniprojectserver.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import nus.iss.csf.miniprojectserver.models.Registration;
import nus.iss.csf.miniprojectserver.services.RegistrationException;
import nus.iss.csf.miniprojectserver.services.RegistrationService;

@RestController
@RequestMapping(path = "/register")
public class RegistrationRestController {

    @Autowired
    private RegistrationService registrationSvc;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> newRegistration(@RequestPart MultipartFile myFile, @RequestPart String username, @RequestPart String email, @RequestPart String password) throws IOException {
        
        Registration r = new Registration();

        r.setUsername(username);
        r.setEmail(email);
        r.setPassword(password);
        r.setPic(myFile.getInputStream());

        try {
            registrationSvc.registerUser(r);
        } catch (RegistrationException ex) {
            System.out.println(ex.getReason());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.toString());
        }

        JsonObject data = Json.createObjectBuilder()
                .add("name", myFile.getName())
                .add("content-type", myFile.getContentType())
                .add("size", myFile.getSize())
                .build();
        
        return ResponseEntity.ok(data.toString());
    }

    
}
