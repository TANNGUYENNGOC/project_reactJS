package com.be.dto;

public interface CartDetailDto {
    //Dto này dùng đổ sổ list
    Integer getId();
    Integer getIdProduct();
    String getImg();
    String getName();
    Double getPrice();
    Integer getQuantity();
    Double getTotal();
}
