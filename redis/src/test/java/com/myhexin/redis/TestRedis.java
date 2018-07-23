package com.myhexin.redis;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.myhexin.redis.domain.User;
import com.myhexin.redis.servcie.UserService;
@SpringBootTest(classes = application.class)
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class TestRedis {
    @Resource
    private RedisTemplate<String, String> redisTemplate;
    @Resource
    private UserService userService;
    @Test
    public void testSet() {
        this.redisTemplate.opsForValue().set("study", "java");
    	redisTemplate.opsForList().leftPush("zy", "java");
    	redisTemplate.opsForList().leftPush("zy", "java1");
    	redisTemplate.opsForList().leftPush("zy", "java2");
        System.out.println(this.redisTemplate.opsForList().leftPop("zy"));
        
       
    }
    
    
    @Test
    public void testSetUser() {
    	User user1 = new User("user1ID", "User 1");  
        User user2 = new User("user2ID", "User 2");  
          
        System.out.println("==== getting objects from redis ====");  
        System.out.println("User is not in redis yet: " + userService.get(user1));  
        System.out.println("User is not in redis yet: " + userService.get(user2));  
          
        System.out.println("==== putting objects into redis ====");  
        userService.put(user1);  
        userService.put(user2);  
          
        System.out.println("==== getting objects from redis ====");  
        System.out.println("User should be in redis yet: " + userService.get(user1));  
        System.out.println("User should be in redis yet: " + userService.get(user2));  
          
        System.out.println("==== deleting objects from redis ====");  
       
        // userService.delete(user1);  
        // userService.delete(user2);  
       
        System.out.println("==== getting objects from redis ====");  
        System.out.println("User is not in redis yet: " + userService.get(user1));  
        System.out.println("User is not in redis yet: " + userService.get(user2)); 
    }
    
    
}