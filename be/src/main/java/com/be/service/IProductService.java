package com.be.service;

import com.be.dto.product.IProductDto;
import com.be.model.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

public interface IProductService {
    Product findById(Integer id);

    void save(Product product);

    Page<IProductDto> listSearchProduct(Pageable pageable,
                                        String nameProduct,
                                        String nameProductType,
                                        String nameStyles,
                                        String nameSubject);
    IProductDto getProduct (Integer idProduct);

}
