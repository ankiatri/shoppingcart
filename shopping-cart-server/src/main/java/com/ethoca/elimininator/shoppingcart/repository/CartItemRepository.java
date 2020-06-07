package com.ethoca.elimininator.shoppingcart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ethoca.elimininator.shoppingcart.entity.CartItem;
import com.ethoca.elimininator.shoppingcart.entity.Status;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    List<CartItem> findByUserId(Long userId);

    List<CartItem> findByUserIdAndStatus(Long userId, Status status);

    @Query("SELECT c FROM CartItem c where c.product.id = :productId")
    CartItem findByProductId(Long productId);

    @Query("SELECT c FROM CartItem c where c.product.id = :productId and c.status = :status")
    CartItem findByProductIdAndStatus(Long productId, Status status);

    @Modifying
    @Query("Delete FROM CartItem c where c.product.id = :productId")
    void deleteProduct(Long productId);
}
