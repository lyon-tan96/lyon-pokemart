package nus.iss.csf.miniprojectserver.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nus.iss.csf.miniprojectserver.models.Cart;
import nus.iss.csf.miniprojectserver.repositories.CartRepository;

@Service
public class CartService {
    
    @Autowired
    private CartRepository cartRepo;

    public void insertCartItems (Cart cart) {
        cartRepo.insertCartItems(cart);
    }

    public List<Cart> getUserCartItems(String username) {
        return cartRepo.getUserCartItems(username);
    }

    public void deleteCartItemById(Integer cartId) throws CartServiceException {
        Optional<Cart> opt = cartRepo.getCartItemById(cartId);
        if (opt.isEmpty()) {
//            throw new CartServiceException("Cart Item with Id: %s does not exist".formatted(cartId));
            throw new CartServiceException("Cart Item with Id: %s does not exist");
        } else {
            cartRepo.deleteCartItemById(cartId);
        }
        
    }

    public void deleteCartItemsByUser(String username) throws CartServiceException {
        Optional<Cart> opt = cartRepo.getCartItemByUser(username);
        if (opt.isEmpty()) {
//            throw new CartServiceException("There are no items in %s's cart".formatted(username));
            throw new CartServiceException("There are no items in %s's cart");
        } else {
            cartRepo.deleteCartItemsByUser(username);
        }
    }

}
