package com.ethoca.elimininator.shoppingcart.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.ethoca.elimininator.shoppingcart.entity.CartItem;
import com.ethoca.elimininator.shoppingcart.entity.Product;
import com.ethoca.elimininator.shoppingcart.entity.Status;
import com.ethoca.elimininator.shoppingcart.repository.CartItemRepository;
import com.ethoca.elimininator.shoppingcart.service.impl.CartItemServiceImpl;

/**
 * Test {@link CartService}.
 */
@ExtendWith(SpringExtension.class)
public class CartItemServiceTest {
    @InjectMocks
    private CartItemServiceImpl cartItemService;
    @Mock
    private CartItemRepository cartItemRepository;
    private CartItem mockCartItem;
    private List<CartItem> mockCartItems = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        Product mockProduct = new Product();
        mockProduct.setId(1L);
        mockProduct.setName("testProductName1");
        mockProduct.setDescription("testdescription");
        mockProduct.setPrice(200L);

        mockCartItem = new CartItem();
        mockCartItem.setId(1L);
        mockCartItem.setStatus(Status.OPENED);
        mockCartItem.setQuantity(2);
        mockCartItem.setUserId(1L);
        mockCartItem.setProduct(mockProduct);
        mockCartItems.add(mockCartItem);
    }

    @Test
    public void verify_getCartItems() throws Exception {
        cartItemService.getCartItems(1L);
        verify(cartItemRepository, times(1)).findByUserId(1L);
    }

    @Test
    public void verify_getCartItemByStatus() throws Exception {
        cartItemService.getCartItemsByStatus(1L, Status.OPENED);
        verify(cartItemRepository, times(1)).findByUserIdAndStatus(1L, Status.OPENED);
    }

    @Test
    public void verify_addCartItem_withSameProductId() throws Exception {

        Product product = new Product();
        product.setId(1L);
        CartItem cartItem = new CartItem();
        cartItem.setId(1L);
        cartItem.setQuantity(4);
        cartItem.setProduct(product);
        when(cartItemRepository.findByProductId(any())).thenReturn(mockCartItem);
        when(cartItemRepository.findByUserId(any())).thenReturn(Collections.singletonList(cartItem));
        List<CartItem> cartItems = cartItemService.addCartItem(1L, cartItem);
        assertThat(cartItems.size(), equalTo(1));
        assertThat(cartItems.get(0).getQuantity(), equalTo(6));
        verify(cartItemRepository, times(1)).saveAndFlush(any());
    }

    @Test
    public void verify_addCartItem_withNewProductId() throws Exception {
        Product product = new Product();
        product.setId(2L);
        CartItem cartItem = new CartItem();
        cartItem.setId(1L);
        cartItem.setQuantity(4);
        cartItem.setProduct(product);
        when(cartItemRepository.findByProductId(any())).thenReturn(mockCartItem);
        mockCartItems.add(cartItem);
        when(cartItemRepository.findByUserId(any())).thenReturn(mockCartItems);

        List<CartItem> cartItems = cartItemService.addCartItem(1L, cartItem);
        assertThat(cartItems.size(), equalTo(2));
        verify(cartItemRepository, times(1)).saveAndFlush(any());
    }

    @Test
    public void verify_updateCartItem_newQuantityUpdated() throws Exception {
        Product product = new Product();
        product.setId(1L);
        CartItem cartItem = new CartItem();
        cartItem.setId(1L);
        cartItem.setQuantity(4);
        cartItem.setProduct(product);

        when(cartItemRepository.findByProductId(any())).thenReturn(mockCartItem);
        when(cartItemRepository.findByUserId(any())).thenReturn(Collections.singletonList(cartItem));

        List<CartItem> cartItems = cartItemService.updateCartItem(1L, cartItem);
        assertThat(cartItems.size(), equalTo(1));
        assertThat(cartItems.get(0).getQuantity(), equalTo(4));
        verify(cartItemRepository, times(1)).saveAndFlush(any());
    }

    @Test
    public void verify_removeCartItem() throws Exception {
        cartItemService.removeCartItem(1L, mockCartItem);
        verify(cartItemRepository, times(1)).deleteProduct(1L);
    }
}
