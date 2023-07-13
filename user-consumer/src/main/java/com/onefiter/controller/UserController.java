package com.onefiter.controller;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import com.onefiter.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/consumer")
public class UserController {
    @Autowired
    private RestTemplate restTemplate;


    @Autowired
    private DiscoveryClient discoveryClient;
    /****
     * 在user-consumer服务中通过RestTemplate调用user-provider服务
     * @param id
     * @return
     */
    @HystrixCommand(fallbackMethod = "failBack") // 方法如果发生问题，则调用降级处理方法
    @GetMapping(value = "/{id}")
    public User queryById(@PathVariable(value = "id")Integer id){
//        String url = "http://localhost:18081/user/find/"+id;
//        return restTemplate.getForObject(url,User.class);

//        List<ServiceInstance> instances = discoveryClient.getInstances("user-provider");
//        ServiceInstance serviceInstance = instances.get(0);
//        String instanceUrl = "http://"+serviceInstance.getHost()+":"+serviceInstance.getPort()+"/user/find/"+id;
//        return restTemplate.getForObject(instanceUrl,User.class);
        String url = "http://user-provider/user/find/"+id;
        return restTemplate.getForObject(url,User.class);
    }

    /****
     * 服务降级处理方法
     * 当某个方法发生异常或者执行超时的时候，则直接让该方法处理用户的请求
     * @return
     */
    public User failBack(Integer id){
        User user = new User();
        user.setUsername("服务降级,默认处理！");
        return  user;
    }
}
