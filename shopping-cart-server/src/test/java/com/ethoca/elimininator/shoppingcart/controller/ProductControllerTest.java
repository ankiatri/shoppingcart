package com.ethoca.elimininator.shoppingcart.controller;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

import com.ethoca.elimininator.shoppingcart.entity.Product;
import com.ethoca.elimininator.shoppingcart.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Test {@link ProductController}.
 */
@ExtendWith(SpringExtension.class)
public class ProductControllerTest {
    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductService productService;
    @Mock
    private ObjectMapper objectMapper;

    private Product mockProduct1;
    private List<Product> mockProducts = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        Authentication auth = new UsernamePasswordAuthenticationToken("productname", "password");
        SecurityContextHolder.getContext().setAuthentication(auth);
        mockProduct1 = new Product();
        mockProduct1.setId(1L);
        mockProduct1.setName("testProductName1");
        mockProduct1.setDescription("testdescription");
        mockProduct1.setPrice(100L);

        Product mockProduct2 = new Product();
        mockProduct2.setId(2L);
        mockProduct2.setName("testProductName2");
        mockProduct2.setDescription("testdescription");
        mockProduct2.setPrice(200L);
        mockProducts.add(mockProduct1);
        mockProducts.add(mockProduct2);
    }

    @Test
    public void getAllProducts_thenReturnStatusOk() throws Exception {
        when(productService.getAllProducts()).thenReturn(mockProducts);
        ResponseEntity responseEntity = productController.getAllProducts();
        List<Product> response = (List<Product>) responseEntity.getBody();
        assertThat(response.size(), equalTo(2));
        assertThat(HttpStatus.OK.value(), equalTo(responseEntity.getStatusCodeValue()));
    }

    @Test
    public void createProduct_withProductname_thenReturnResponseCreated() throws Exception {
        productController.loadProducts();
        verify(productService, times(1)).saveProducts(any());
    }

    @Test
    public void getProductById_withValid_thenReturnStatusOk() throws Exception {
        when(productService.getProductById(any())).thenReturn(Optional.of(mockProduct1));
        ResponseEntity<Product> responseEntity = productController.getProductById(1L);
        assertThat(HttpStatus.OK.value(), equalTo(responseEntity.getStatusCodeValue()));
    }
}
