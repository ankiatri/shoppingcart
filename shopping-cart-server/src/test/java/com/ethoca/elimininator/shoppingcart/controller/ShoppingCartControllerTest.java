package com.ethoca.elimininator.shoppingcart.controller;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.ethoca.elimininator.shoppingcart.entity.CartItem;
import com.ethoca.elimininator.shoppingcart.entity.Product;
import com.ethoca.elimininator.shoppingcart.entity.Status;
import com.ethoca.elimininator.shoppingcart.service.CartItemService;

/**
 * Test {@link ShoppingCartController}.
 */
@ExtendWith(SpringExtension.class)
public class ShoppingCartControllerTest {
    @InjectMocks
    private ShoppingCartController shoppingCartController;
    @Mock
    private CartItemService cartItemService;

    private CartItem mockCartItem;
    private CartItem mockCartItem1;
    private List<CartItem> mockCartItems = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        Authentication auth = new UsernamePasswordAuthenticationToken("productname", "password");
        SecurityContextHolder.getContext().setAuthentication(auth);
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

        Product mockProduct1 = new Product();
        mockProduct1.setId(2L);
        mockProduct1.setName("testProductName2");
        mockProduct1.setDescription("testdescription");
        mockProduct1.setPrice(400L);
        mockCartItem1 = new CartItem();
        mockCartItem1.setId(2L);
        mockCartItem1.setStatus(Status.SUBMITTED);
        mockCartItem1.setQuantity(1);
        mockCartItem.setProduct(mockProduct1);
        mockCartItem1.setUserId(1L);

        mockCartItems.add(mockCartItem1);
        mockCartItems.add(mockCartItem);
    }

    @Test
    public void getCartItemsByUserId_thenReturnStatusOk() throws Exception {
        when(cartItemService.getCartItems(any())).thenReturn(mockCartItems);
        ResponseEntity responseEntity = shoppingCartController.getCartItemById(1L);
        List<CartItem> response = (List<CartItem>) responseEntity.getBody();
        assertThat(response.size(), equalTo(2));
        assertThat(HttpStatus.OK.value(), equalTo(responseEntity.getStatusCodeValue()));
    }

    @Test
    public void getCartItemsByUsername_thenReturnStatusOk() throws Exception {
        when(cartItemService.getCartItemsByStatus(1L, Status.OPENED))
                .thenReturn(Collections.singletonList(mockCartItem));
        ResponseEntity responseEntity = shoppingCartController.getCartItemByUsername(1L,
                Status.OPENED.getStatusValue());
        List<CartItem> response = (List<CartItem>) responseEntity.getBody();
        assertThat(response.size(), equalTo(1));
        assertThat(response.get(0).getUserId(), equalTo(1L));
        assertThat(response.get(0).getStatus(), equalTo(Status.OPENED));
        assertThat(HttpStatus.OK.value(), equalTo(responseEntity.getStatusCodeValue()));
    }

    @Test
    public void addCartItem_thenReturnResponseOk() throws Exception {
        when(cartItemService.addCartItem(1L, mockCartItem)).thenReturn(Collections.singletonList(mockCartItem));
        ResponseEntity responseEntity = shoppingCartController.addCartItem(1L, mockCartItem);
        List<CartItem> response = (List<CartItem>) responseEntity.getBody();
        assertThat(response.size(), equalTo(1));
        assertThat(response.get(0).getUserId(), equalTo(1L));
        assertThat(response.get(0).getQuantity(), equalTo(2));
        assertThat(HttpStatus.OK.value(), equalTo(responseEntity.getStatusCodeValue()));
    }

    @Test
    public void removeCartItem_thenReturnResponseOk() throws Exception {
        when(cartItemService.removeCartItem(1L, mockCartItem1)).thenReturn(Collections.singletonList(mockCartItem));
        ResponseEntity responseEntity = shoppingCartController.removeCartItem(1L, mockCartItem1);
        List<CartItem> response = (List<CartItem>) responseEntity.getBody();
        assertThat(response.size(), equalTo(1));
        assertThat(HttpStatus.OK.value(), equalTo(responseEntity.getStatusCodeValue()));
    }

    @Test
    public void updateCartItem_thenReturnResponseOk() throws Exception {
        mockCartItem.setQuantity(4);
        when(cartItemService.updateCartItem(1L, mockCartItem)).thenReturn(Collections.singletonList(mockCartItem));
        ResponseEntity responseEntity = shoppingCartController.updateCartItem(1L, mockCartItem);
        List<CartItem> response = (List<CartItem>) responseEntity.getBody();
        assertThat(response.size(), equalTo(1));
        assertThat(response.get(0).getQuantity(), equalTo(4));
        assertThat(HttpStatus.OK.value(), equalTo(responseEntity.getStatusCodeValue()));
    }
}
