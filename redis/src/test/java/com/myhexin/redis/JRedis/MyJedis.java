package com.myhexin.redis.JRedis;

import java.net.URI;
import java.net.URISyntaxException;

import com.alibaba.fastjson.JSONObject;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class MyJedis {
  public static void main(String[] args) throws URISyntaxException {
	//URI uri=new   URI("http://127.0.0.1:6379");
	//JedisPool jedisPool=new JedisPool("127.0.0.1",6379);
    //JedisPool jedisPool=new JedisPool(uri);
	//Jedis     jedis=   jedisPool.getResource();
	  Jedis jedis=new Jedis("127.0.0.1",6379);
	  jedis.auth("zy774463266");
	  System.out.println("redis init");
	  System.out.println(jedis.ping());
	  //User user=new User("zy","7744");
	  //jedis.set("user", JSONObject.toJSONString(user));
	  
	  //String uu=jedis.get("user");
	  
	  //User us=JSONObject.parseObject(uu, User.class);
	  //System.out.println(us);
	  
	  //jedis.hset(key, field, value)
	  System.out.println(jedis.setnx("asd", "asd"));
	  System.out.println(jedis.setnx("asd", "asd"));
}
}
