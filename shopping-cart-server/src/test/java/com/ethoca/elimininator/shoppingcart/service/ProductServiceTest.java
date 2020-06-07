package com.ethoca.elimininator.shoppingcart.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.ethoca.elimininator.shoppingcart.entity.Product;
import com.ethoca.elimininator.shoppingcart.repository.ProductRepository;
import com.ethoca.elimininator.shoppingcart.service.impl.ProductServiceImpl;

/**
 * Test {@link ProductService}.
 */
@ExtendWith(SpringExtension.class)
public class ProductServiceTest {
    @InjectMocks
    private ProductServiceImpl productService;
    @Mock
    private ProductRepository productRepository;

    private Product mockProduct1;
    private List<Product> mockProducts = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        mockProduct1 = new Product();
        mockProduct1.setId(1L);
        mockProduct1.setName("testProductName1");
        mockProduct1.setDescription("testdescription");
        mockProduct1.setPrice(100L);
        mockProducts.add(mockProduct1);
    }

    @Test
    public void verify_getAllProducts() {
        productService.getAllProducts();
        verify(productRepository, times(1)).findAll();
    }

    @Test
    public void verify_saveProduct() {
        productService.saveProducts(mockProducts);
        verify(productRepository, times(1)).saveAll(mockProducts);
    }

    @Test
    public void verify_getProductById() {
        productService.getProductById(1L);
        verify(productRepository, times(1)).findById(1L);
    }
}
