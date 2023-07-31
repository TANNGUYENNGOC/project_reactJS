package com.be.repository;

import com.be.dto.CartDetailDto;
import com.be.dto.ICartDto;
import com.be.dto.ICountDto;
import com.be.model.cart.Cart;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ICartRepository extends JpaRepository<Cart, Integer> {
    @Query(value = "select cart_detail.id,\n" +
            "       p.img,\n" +
            "       p.name,\n" +
            "       p.price,\n" +
            "       p.id as idProduct,\n" +
            "       cart_detail.quantity,\n" +
            "        cart_detail.total\n" +
            "from  cart_detail\n" +
            "    join product p on p.id = cart_detail.product_id\n" +
            "    join cart c on c.id = cart_detail.cart_id\n" +
            "    join user u on c.user_id = u.id\n" +
            "where u.id = :idCustomer and c.is_delete = 0 order by p.id desc",
    countQuery = "select cart_detail.id,\n" +
            "       p.img,\n" +
            "       p.name,\n" +
            "       p.price,\n" +
            "       p.id as idProduct,\n" +
            "       cart_detail.quantity,\n" +
            "        cart_detail.total\n" +
            "from  cart_detail\n" +
            "    join product p on p.id = cart_detail.product_id\n" +
            "    join cart c on c.id = cart_detail.cart_id\n" +
            "    join user u on c.user_id = u.id\n" +
            "where u.id = :idCustomer and c.is_delete = 0 order by p.id desc",
    nativeQuery = true)
    Page<CartDetailDto> getListCart (Pageable pageable, @Param("idCustomer") Integer idCustomer);

    @Query(value = "select cart.id,cart.code,cart.customer_name as customerName,cart.payment_date as paymentDate,cart.total from cart join user u on u.id = cart.user_id where user_id = :idCustomer and cart.is_delete = 0",
    countQuery = "select cart.id,cart.code,cart.customer_name as customerName,cart.payment_date as paymentDate,cart.total from cart join user u on u.id = cart.user_id where user_id = :idCustomer and cart.is_delete = 0",
    nativeQuery = true)
    ICartDto findByCartToIdCustomer(@Param("idCustomer") Integer idCustomer);

    // Phương thức này update lại ngày thanh toán và và set lại trạng thái đã thanh
    //toán rồi trong bảng cart
    @Transactional
    @Modifying
    @Query(value = "UPDATE cart SET cart.is_delete = 1, cart.payment_date = CURRENT_TIMESTAMP() where cart.id = :idCart",
            nativeQuery = true)
    void payCart(@Param("idCart") Integer idCart);

    @Query(value = "select sum  (quantity) as countProduct from cart_detail cd join cart c on c.id = cd.cart_id where c.id = :idCart and c.is_delete = 0",
    countQuery = "select sum(quantity)  as countProduct from cart_detail cd join cart c on c.id = cd.cart_id where c.id = :idCart and c.is_delete = 0",
    nativeQuery = true)
    ICountDto getCountProduct(@Param("idCart") Integer idCart);
}
