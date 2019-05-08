package com.example.service;

import com.example.entity.User;

import java.util.List;

public interface UserService {
    public User getUserById(String userId);

    public User findIdAndPassword(User user);

    public String findId();

    public int insertSelective(User user);

    public List<User> findByEmail(String email);

    public List<User> findAllUser();
}
