package com.be.service.impl;

import com.be.model.product.Subject;
import com.be.repository.ISubjectRepository;
import com.be.service.ISubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ISubjectServiceimpl implements ISubjectService {
    @Autowired
    private ISubjectRepository iSubjectRepository;
    @Override
    public List<Subject> findAll() {
        return iSubjectRepository.findAll();
    }
}
