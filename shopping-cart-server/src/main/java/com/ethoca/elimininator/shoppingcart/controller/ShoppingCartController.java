package com.ethoca.elimininator.shoppingcart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ethoca.elimininator.shoppingcart.entity.CartItem;
import com.ethoca.elimininator.shoppingcart.entity.Status;
import com.ethoca.elimininator.shoppingcart.service.CartItemService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/cart")
public class ShoppingCartController {

    @Autowired
    private CartItemService cartService;

    @GetMapping("/{userId}")
    public ResponseEntity getCartItemById(@PathVariable("userId") Long userId) {
        List<CartItem> cartItems = cartService.getCartItems(userId);
        if (cartItems != null && cartItems.size() > 0) {
            return new ResponseEntity(cartItems, HttpStatus.OK);
        } else {
            return new ResponseEntity(cartItems, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{userId}/{status}")
    public ResponseEntity getCartItemByUsername(@PathVariable("userId") Long userId,
            @PathVariable("status") String status) {
        Status statusValue;
        if (status.equalsIgnoreCase("Submitted")) {
            statusValue = Status.SUBMITTED;
        } else {
            statusValue = Status.OPENED;
        }
        List<CartItem> cartItems = cartService.getCartItemsByStatus(userId, statusValue);
        if (cartItems != null && !cartItems.isEmpty()) {
            return new ResponseEntity(cartItems, HttpStatus.OK);
        } else {
            return new ResponseEntity(cartItems, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/add/{userId}")
    public ResponseEntity addCartItem(@PathVariable("userId") Long userId, @RequestBody CartItem cartItem) {
        List<CartItem> cartItems = cartService.addCartItem(userId, cartItem);
        return new ResponseEntity(cartItems, HttpStatus.OK);
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity updateCartItem(@PathVariable("userId") Long userId, @RequestBody CartItem cartItem) {
        List<CartItem> cartItems = cartService.updateCartItem(userId, cartItem);
        return new ResponseEntity(cartItems, HttpStatus.OK);
    }

    @PutMapping("/remove/{userId}")
    public ResponseEntity removeCartItem(@PathVariable("userId") Long userId, @RequestBody CartItem cartItem) {
        List<CartItem> cartItems = cartService.removeCartItem(userId, cartItem);
        return new ResponseEntity(cartItems, HttpStatus.OK);

    }
}
