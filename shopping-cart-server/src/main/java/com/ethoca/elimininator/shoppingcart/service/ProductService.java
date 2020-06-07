package com.ethoca.elimininator.shoppingcart.service;

import java.util.List;
import java.util.Optional;

import com.ethoca.elimininator.shoppingcart.entity.Product;

public interface ProductService {

    List<Product> getAllProducts();

    Optional<Product> getProductById(Long id);

    List<Product> saveProducts(List<Product> products);

}
