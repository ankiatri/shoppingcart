package com.ethoca.elimininator.shoppingcart.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ethoca.elimininator.shoppingcart.entity.Product;
import com.ethoca.elimininator.shoppingcart.service.ProductService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private ObjectMapper objectMapper;

    @PostConstruct
    void loadProducts() {
        try {
            List<Product> products = objectMapper.readValue(
                    TypeReference.class.getResourceAsStream("/json/products.json"), new TypeReference<List<Product>>() {
                    });
            productService.saveProducts(products);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return new ResponseEntity(products, HttpStatus.OK);

    }

    @GetMapping("/{productId}")
    public ResponseEntity getProductById(@PathVariable("productId") Long productId) {
        Optional<Product> product = productService.getProductById(productId);
        if (product.get() != null) {
            return new ResponseEntity(product, HttpStatus.OK);
        } else {
            return new ResponseEntity(product, HttpStatus.OK);
        }
    }

}
