package com.be.service.user;


import com.be.model.user.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    Optional<User> findByUsername(String name);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    Boolean checkIfValidOldPassword(User user, String oldPassword);

    void changeUserPassword(User user,String newPassword);

    User findByEmailEmployee(String email);

    void updateOtp(User user);
    List<User> getAll();
    void save(User user);

    //Tấn thêm vào
    User findById(Integer id);
}
