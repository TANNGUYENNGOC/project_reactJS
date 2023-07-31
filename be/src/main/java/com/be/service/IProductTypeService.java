package com.be.service;

import com.be.model.product.ProductType;

import java.util.List;

public interface IProductTypeService {
    List<ProductType> findAll();
}
