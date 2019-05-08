package com.example.dao;

import com.example.entity.User;

import java.util.List;

public interface UserDao {
    User selectByPrimaryKey(String id);
    User findIdAndPassword(User user);
    String findId();
    int insertSelective(User user);
    List<User> findByEmail(String email);
    List<User> findAllUser();
    User find();
}
