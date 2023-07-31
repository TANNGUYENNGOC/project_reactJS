package com.be.repository;

import com.be.model.product.Styles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IStylesRepository extends JpaRepository<Styles,Integer> {
}
