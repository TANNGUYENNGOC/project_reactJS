package com.be.repository;

import com.be.dto.ICartDetailDto1;
import com.be.model.cart.CartDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ICartDetailRepository extends JpaRepository<CartDetail,Integer> {
    @Query(value = "select cd.id, cd.quantity,cd.total,cd.cart_id as cartId from cart_detail cd join product p on p.id = cd.product_id where p.id = :idProduct and cd.is_delete = 0",
    countQuery = "select cd.id, cd.quantity,cd.total,cd.cart_id as cartId from cart_detail cd join product p on p.id = cd.product_id where p.id = :idProduct and cd.is_delete = 0",
    nativeQuery = true)
    ICartDetailDto1 getCartDetailByIdProduct(@Param("idProduct") Integer idProduct);


    // Phương thức này update lại flag của cart_detail sau khi thanh toán
    @Transactional
    @Modifying
    @Query(value = "UPDATE cart_detail\n" +
            "SET cart_detail.is_delete = 1 where cart_id = :idCart",
    nativeQuery = true)
    void payCartDetail(@Param("idCart") Integer idCart);


}
