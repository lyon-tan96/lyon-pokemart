package nus.iss.csf.miniprojectserver.services;

import java.io.StringReader;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import nus.iss.csf.miniprojectserver.models.Email;
import nus.iss.csf.miniprojectserver.models.LineItems;
import nus.iss.csf.miniprojectserver.models.OrderDetails;
import nus.iss.csf.miniprojectserver.repositories.CheckoutRepository;

@Service
public class OrderService {
    
    @Autowired
    private CheckoutRepository checkoutRepo;

    @Autowired
    private EmailService emailSvc;

    @Transactional
    public String createNewOrder(String json) {

        JsonReader reader = Json.createReader(new StringReader(json));
        JsonObject data = reader.readObject();

        String orderId = UUID.randomUUID().toString().substring(0,8);

        OrderDetails order = OrderDetails.create(data);
        order.setStatus("Pending Payment");
        order.setOrderId(orderId);

        checkoutRepo.createNewOrder(order);

        
        JsonArray lineItems = data.getJsonArray("lineItems");
        for (int i = 0; i < lineItems.size(); i++) {
            JsonObject lineItem = lineItems.getJsonObject(i);
            LineItems item = new LineItems();
            item.setCardId(lineItem.getString("cardId"));
            item.setCardName(lineItem.getString("cardName"));
            item.setImage(lineItem.getString("image"));
            item.setPrice(lineItem.getString("price"));
            item.setUsername(lineItem.getString("username"));
            item.setOrderId(orderId);
            item.setQuantity(lineItem.getInt("quantity"));

            Optional<Integer> opt = checkoutRepo.findDuplicateCardOrder(orderId, item.getCardId());
            if (opt.isEmpty()){
                checkoutRepo.insertLineItems(item);
            } else {
                Integer quantity = opt.get() + 1;
                checkoutRepo.updateCardQuantity(quantity, orderId, item.getCardId());
            }
        }

        checkoutRepo.deleteCartItems(order.getUsername());

        Email content = new Email();
        content.setOrderId(orderId);
        content.setName(order.getName());
        content.setUsername(order.getUsername());
        content.setAmount(order.getAmount());
        
        emailSvc.sendEmail(content);

        return orderId;
    }
    
}
