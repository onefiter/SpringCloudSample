package com.onefiter.controller;

import com.onefiter.pojo.User;
import com.onefiter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    /***
     * 根据ID查询用户信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/find/{id}")
    public User findById(@PathVariable(value = "id") Integer id){

        if (id == 3) {
            throw new RuntimeException("抛出异常");
        }

        User user =  userService.findByUserId(id);
        user.setUsername(user+"     user-provider");
        return user;
    }
}