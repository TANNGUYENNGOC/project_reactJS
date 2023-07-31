package com.be.service.impl;

import com.be.model.product.ProductType;
import com.be.repository.IProductRepository;
import com.be.repository.IProductTypeRepository;
import com.be.service.IProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductTypeServiceImpl implements IProductTypeService {
    @Autowired
    private IProductTypeRepository iProductTypeRepository;
    @Override
    public List<ProductType> findAll() {
        return iProductTypeRepository.findAll();
    }
}
