package com.be.dto;

public interface ICartDetailDto1 {
    //Dto này dùng để lấy ra 1 cart_detail dựa vào id của giỏ hàng
    Integer getId();
    Integer getQuantity();
    Double getTotal();
    Integer getCartId();
}
