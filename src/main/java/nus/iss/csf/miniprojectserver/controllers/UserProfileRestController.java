package nus.iss.csf.miniprojectserver.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import nus.iss.csf.miniprojectserver.models.LineItems;
import nus.iss.csf.miniprojectserver.models.OrderDetails;
import nus.iss.csf.miniprojectserver.services.UserProfileService;

@RestController
@RequestMapping(path = "/api/orders")
public class UserProfileRestController {

    @Autowired
    private UserProfileService userProfileSvc;

    @GetMapping(path = "{username}")
    public ResponseEntity<String> getUserOrders(@PathVariable String username) {

        List<OrderDetails> orderDetails = userProfileSvc.getUserOrders(username);

        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        for (OrderDetails orderdetail : orderDetails)
            arrayBuilder.add(orderdetail.toJson());
            
            return ResponseEntity.ok(arrayBuilder.build().toString());
    }

    @GetMapping(path = "{username}/{orderId}")
    public ResponseEntity<String> getOrderLineItems(@PathVariable("orderId") String orderId, @PathVariable("username") String username) {

        List<LineItems> lineItems = userProfileSvc.getOrderLineItems(orderId);

        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        for (LineItems lineItem: lineItems)
            arrayBuilder.add(lineItem.toJson());
    
            return ResponseEntity.ok(arrayBuilder.build().toString());

    }

}
    

