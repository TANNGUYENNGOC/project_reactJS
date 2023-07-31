package com.be.service.impl;

import com.be.model.product.Styles;
import com.be.repository.IStylesRepository;
import com.be.service.IStylesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StylesServiceimpl implements IStylesService {
    @Autowired
    private IStylesRepository iStylesRepository;
    @Override
    public List<Styles> findAll() {
        return iStylesRepository.findAll();
    }
}
