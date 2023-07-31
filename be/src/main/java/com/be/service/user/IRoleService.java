package com.be.service.user;



import com.be.model.user.Role;

import java.util.Optional;

public interface IRoleService {
    Optional<Role> findByName (String name);
}
