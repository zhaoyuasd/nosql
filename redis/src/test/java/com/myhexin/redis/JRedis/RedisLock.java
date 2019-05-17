package com.myhexin.redis.JRedis;

import redis.clients.jedis.Jedis;

public class RedisLock {
  public static void main(String[] args) {
	  for(int i=0;i<18;i++) {
		  System.out.println(Thread.currentThread().getName()+"创建 线程："+i);
		  UpdateNum uu=new UpdateNum(getJedis());
		  uu.start();
	  }
	  
}
  
  public static Jedis getJedis() {
	  Jedis jedis=new Jedis("127.0.0.1",6379);
	  jedis.auth("zy774463266");
	  return jedis; 
  }
}


class UpdateNum extends Thread{
	private Jedis jedis;
	private boolean finshed=false;
	private final String lock="lock";
	UpdateNum(Jedis jedis){
	    this.jedis=jedis;	
	}
	
	public void run(){
		withNX();
	}
	
	public void withNX() {
		 int waitCount=0;
		 System.out.println(Thread.currentThread().getName()+"开始执行");
	    while(!finshed) {
	     long ob=jedis.setnx(lock.getBytes(),lock.getBytes());
	     if(ob<=0) {
	    	 waitCount=waitCount+1;
	    	 continue ;
	     }
	     System.out.println(Thread.currentThread().getName()+" 获取到锁");
	     jedis.expire(lock, 5);
		 String num=jedis.get("num");
	     System.out.println(Thread.currentThread().getName()+"  num:"+num+" "+num.getClass());
	     Integer sum=20+Integer.valueOf(num);
	     jedis.set("num",String.valueOf(sum));
	     jedis.del(lock.getBytes());
	     finshed=true;
	     System.out.println(Thread.currentThread().getName()+" waitCount:"+waitCount);
	 }
	}
	
  public void withIncryBy() {
	  jedis.incrBy("num", 20);
  }
}