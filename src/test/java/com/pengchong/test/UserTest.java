package com.pengchong.test;

import com.pengchong.dao.TestMapper;
import com.pengchong.dao.ZtreeDemoMapper;
import com.pengchong.model.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserTest {

    @Test
    public void testfindList(){
        ApplicationContext context = new
                ClassPathXmlApplicationContext("classpath:spring-config.xml");
        ZtreeDemoMapper testMapper =  context.getBean("ztreeDemoMapper",ZtreeDemoMapper.class);
        List<Map> maps = testMapper.findZtreeDemo();
        System.out.println(maps.size());
  }
}
