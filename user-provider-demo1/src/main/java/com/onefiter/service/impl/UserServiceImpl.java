package com.onefiter.service.impl;

import com.onefiter.dao.UserDao;
import com.onefiter.pojo.User;
import com.onefiter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Override
    public User findByUserId(Integer id) {
        return userDao.findById(id).get();
    }
}
