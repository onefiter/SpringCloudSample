package com.onefiter.feign.fallback;

import com.onefiter.feign.UserClient;
import com.onefiter.pojo.User;

public class UserClientFallback implements UserClient {
    @Override
    public User findById(Integer id) {
        User user = new User();
        user.setUsername("服务熔断");
        return user;
    }
}
