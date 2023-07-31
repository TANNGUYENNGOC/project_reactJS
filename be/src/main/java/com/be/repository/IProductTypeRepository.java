package com.be.repository;

import com.be.model.product.Product;
import com.be.model.product.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductTypeRepository extends JpaRepository<ProductType,Integer> {
}
