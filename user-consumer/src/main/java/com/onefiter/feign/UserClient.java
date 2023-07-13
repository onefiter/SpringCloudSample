package com.onefiter.feign;


import com.onefiter.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

//@FeignClient(value = "要调用微服务的名字")
@FeignClient(value = "user-provider")
public interface UserClient {
    /***
     * 根据ID查询用户信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/user/find/{id}")
    User findById(@PathVariable(value = "id") Integer id);
}
