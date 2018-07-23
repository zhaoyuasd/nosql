package com.laozhao.mongoConver;

import java.util.Date;
import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.laozhao.mongoConver.domain.User;

public class Test {
 public static void main(String[] args) {
	 AnnotationConfigApplicationContext  ac=new AnnotationConfigApplicationContext(App.class);
	 MongoTemplate  mongoTemplate=(MongoTemplate) ac.getBean("mongoTemplate");
	 //存是没问题
	 User u=new User();
   //  mongoTemplate.insert(u);
     
     
     //按照时间类型查找也没问题
     Query query = new Query(); 
     Criteria criteria = Criteria.where("time").lte(new Date()); 
     String  value="3zy";
     Criteria  name=Criteria.where("name").regex("^.*"+value+"*.$");
     query.addCriteria(name);
     List<User> v=mongoTemplate.find(query, User.class);
     for(User itm:v)
     	System.out.println(itm);
}
}
