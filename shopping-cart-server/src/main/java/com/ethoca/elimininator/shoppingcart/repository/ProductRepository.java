package com.ethoca.elimininator.shoppingcart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ethoca.elimininator.shoppingcart.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
