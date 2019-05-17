package com.myhexin.redis.JRedis;

import redis.clients.jedis.Jedis;

public class PFTest {
	public static Jedis getJedis() {
		  Jedis jedis=new Jedis("127.0.0.1",6379);
		  jedis.auth("zy774463266");
		  return jedis; 
	  }
	
	public static void main(String[] args) {
		Jedis jedis=getJedis();
		for(int i=0;i<100000;i++) {
			jedis.pfadd("ck", "user"+i);
		}
		System.out.println("pfcount:"+jedis.pfcount("ck"));	
		jedis.close();
	}
}
