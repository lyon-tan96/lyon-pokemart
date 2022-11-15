package nus.iss.csf.miniprojectserver.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nus.iss.csf.miniprojectserver.models.Response;
import nus.iss.csf.miniprojectserver.services.OrderService;

@RestController
@RequestMapping(path = "/api")
public class OrderRestController {

    @Autowired
    private OrderService orderSvc;

    @PostMapping(path = "/newOrder")
    public ResponseEntity<String> createNewOrder(@RequestBody String payload) {

        String orderId = orderSvc.createNewOrder(payload);

        Response resp = new Response();
        resp.setCode(200);
        resp.setMessage(orderId);
        
        return ResponseEntity.status(HttpStatus.OK).body(resp.toJson().toString());
    }
    
}
