package com.be.service.user.impl;

import com.be.model.user.Role;
import com.be.repository.user.IRoleRepository;
import com.be.service.user.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService implements IRoleService {
    @Autowired
    private IRoleRepository roleRepository;
    @Override
    public Optional<Role> findByName(String name) {
        return roleRepository.findWithName(name);
    }
}
