package com.ethoca.elimininator.shoppingcart.service;

import java.util.List;

import com.ethoca.elimininator.shoppingcart.entity.CartItem;
import com.ethoca.elimininator.shoppingcart.entity.Status;

public interface CartItemService {
    List<CartItem> removeCartItem(Long userId, CartItem item);

    List<CartItem> addCartItem(Long userId, CartItem item);

    List<CartItem> getCartItems(Long userId);

    List<CartItem> getCartItemsByStatus(Long userId, Status status);

    List<CartItem> updateCartItem(Long userId, CartItem item);

}
