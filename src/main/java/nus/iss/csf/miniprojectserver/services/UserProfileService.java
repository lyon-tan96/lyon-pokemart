package nus.iss.csf.miniprojectserver.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nus.iss.csf.miniprojectserver.models.LineItems;
import nus.iss.csf.miniprojectserver.models.OrderDetails;
import nus.iss.csf.miniprojectserver.repositories.UserProfileRepository;

@Service
public class UserProfileService {

    @Autowired
    private UserProfileRepository userProfileRepo;

    public List<OrderDetails> getUserOrders(String username) {

        List<OrderDetails> orderDetails = userProfileRepo.getUserOrders(username);

        return orderDetails;
    }

    public List<LineItems> getOrderLineItems(String orderId) {

        List<LineItems> lineItems = userProfileRepo.getOrderLineitems(orderId);

        return lineItems;
    }
    
}
