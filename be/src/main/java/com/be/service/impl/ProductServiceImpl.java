package com.be.service.impl;

import com.be.dto.product.IProductDto;
import com.be.model.cart.Cart;
import com.be.model.product.Product;
import com.be.repository.IProductRepository;
import com.be.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements IProductService {
    @Autowired
    private IProductRepository iProductRepository;

    @Override
    public Product findById(Integer id) {
        return iProductRepository.findById(id).get();
    }

    @Override
    public void save(Product product) {
        iProductRepository.save(product);
    }

    @Override
    public Page<IProductDto> listSearchProduct(Pageable pageable,
                                               String nameProduct,
                                               String nameProductType,
                                               String nameStyles,
                                               String nameSubject) {
        return iProductRepository.listSearchProduct(pageable,nameProduct,nameProductType,nameStyles,nameSubject);
    }

    @Override
    public IProductDto getProduct(Integer idProduct) {
        return iProductRepository.getProduct(idProduct);
    }
}
