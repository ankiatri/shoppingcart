package com.ethoca.elimininator.shoppingcart.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.ethoca.elimininator.shoppingcart.entity.CartItem;
import com.ethoca.elimininator.shoppingcart.entity.Status;
import com.ethoca.elimininator.shoppingcart.repository.CartItemRepository;
import com.ethoca.elimininator.shoppingcart.service.CartItemService;

/**
 * Cart service impl
 *
 * @author Ankita
 */
@Service
@Transactional
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CartItemServiceImpl implements CartItemService {

    private CartItemRepository cartItemRepository;

    @Autowired
    public CartItemServiceImpl(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }

    @Override
    public List<CartItem> getCartItems(Long userId) {
        return cartItemRepository.findByUserId(userId);
    }

    @Override
    public List<CartItem> getCartItemsByStatus(Long userId, Status status) {
        return cartItemRepository.findByUserIdAndStatus(userId, status);
    }

    @Override
    public List<CartItem> addCartItem(Long userId, CartItem item) {
        CartItem cartItem = cartItemRepository.findByProductIdAndStatus(item.getProduct().getId(), Status.OPENED);
        if (cartItem != null) {
            int quantity = cartItem.getQuantity() != 0 ? cartItem.getQuantity() + item.getQuantity()
                    : item.getQuantity();
            item.setQuantity(quantity);
            item.setProduct(cartItem.getProduct());
            item.setId(cartItem.getId());
            item.setStatus(cartItem.getStatus());
        }

        item.setUserId(userId);
        cartItemRepository.saveAndFlush(item);
        return getCartItems(userId);
    }

    @Override
    public List<CartItem> updateCartItem(Long userId, CartItem item) {
        CartItem cartItem = cartItemRepository.findByProductIdAndStatus(item.getProduct().getId(), Status.OPENED);
        if (cartItem != null) {
            item.setProduct(cartItem.getProduct());
            item.setId(cartItem.getId());
        }
        item.setUserId(userId);
        cartItemRepository.saveAndFlush(item);
        return getCartItemsByStatus(userId, Status.OPENED);
    }

    @Override
    public List<CartItem> removeCartItem(Long userId, CartItem item) {
        cartItemRepository.deleteProduct(item.getProduct().getId());
        return getCartItems(userId);
    }

}
