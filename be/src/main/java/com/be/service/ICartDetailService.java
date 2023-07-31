package com.be.service;

import com.be.dto.ICartDetailDto1;
import com.be.model.cart.CartDetail;
import org.springframework.data.repository.query.Param;

public interface ICartDetailService {
    void save(CartDetail cartDetail);
    CartDetail findById(Integer id);
    ICartDetailDto1 getCartDetailByIdProduct( Integer idProduct);
    void deleteById(Integer id);
    void payCartDetail( Integer idCart);

}
