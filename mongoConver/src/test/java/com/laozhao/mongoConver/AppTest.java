package com.laozhao.mongoConver;

import java.util.List;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import com.laozhao.mongoConver.domain.User;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;


@SpringBootTest(classes = App.class)
public class AppTest 
{
    @Autowired
    private MongoTemplate  mongoTemplate;
	
    @org.junit.Test
    public void Testasd( String testName )
    {
        User u=new User();
        mongoTemplate.insert(u);
        
        List<User> v=mongoTemplate.find(new Query(), User.class);
        for(User itm:v)
        	System.out.println(itm);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        //assertTrue( true );
    }
}
