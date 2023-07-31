package com.be.service.impl;

import com.be.dto.ICartDetailDto1;
import com.be.model.cart.CartDetail;
import com.be.repository.ICartDetailRepository;
import com.be.service.ICartDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartDetailServiceImpl implements ICartDetailService {
    @Autowired
    private ICartDetailRepository iCartDetailRepository;
    @Override
    public void save(CartDetail cartDetail) {
        iCartDetailRepository.save(cartDetail);
    }

    @Override
    public CartDetail findById(Integer id) {
        return iCartDetailRepository.findById(id).get();
    }

    @Override
    public ICartDetailDto1 getCartDetailByIdProduct(Integer idProduct) {
        return iCartDetailRepository.getCartDetailByIdProduct(idProduct);
    }

    @Override
    public void deleteById(Integer id) {
        iCartDetailRepository.deleteById(id);
    }

    @Override
    public void payCartDetail(Integer idCart) {
        iCartDetailRepository.payCartDetail(idCart);
    }
}
