package com.be.service;

import com.be.dto.CartDetailDto;
import com.be.dto.ICartDto;
import com.be.dto.ICountDto;
import com.be.model.cart.Cart;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ICartService {
    Page<CartDetailDto> getListCart (Pageable pageable, Integer id);
    Cart findById(Integer id);
    void save(Cart cart);
    ICartDto findByCartToIdCustomer( Integer idCustomer);
    void deleteById(Integer id);

    void payCart( Integer idCart);
    ICountDto getCountProduct( Integer idCart);


}
