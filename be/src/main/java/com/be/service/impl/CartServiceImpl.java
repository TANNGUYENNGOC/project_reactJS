package com.be.service.impl;

import com.be.dto.CartDetailDto;
import com.be.dto.ICartDto;
import com.be.dto.ICountDto;
import com.be.model.cart.Cart;
import com.be.repository.ICartRepository;
import com.be.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CartServiceImpl implements ICartService {
    @Autowired
    private ICartRepository iCartRepository;
    @Override
    public Page<CartDetailDto> getListCart(Pageable pageable, Integer id) {
        return iCartRepository.getListCart(pageable,id);
    }

    @Override
    public Cart findById(Integer id) {
        return iCartRepository.findById(id).get();
    }

    @Override
    public void save(Cart cart) {
        iCartRepository.save(cart);
    }

    @Override
    public ICartDto findByCartToIdCustomer(Integer idCustomer) {
        return iCartRepository.findByCartToIdCustomer(idCustomer);
    }

    @Override
    public void deleteById(Integer id) {
        iCartRepository.deleteById(id);
    }

    @Override
    public void payCart(Integer idCart) {
        iCartRepository.payCart(idCart);
    }

    @Override
    public ICountDto getCountProduct(Integer idCart) {
        return iCartRepository.getCountProduct(idCart);
    }
}
