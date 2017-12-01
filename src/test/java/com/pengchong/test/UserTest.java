package com.pengchong.test;

import com.pengchong.dao.TestMapper;
import com.pengchong.dao.ZtreeDemoMapper;
import com.pengchong.model.User;
import com.pengchong.service.ZtreeDemoService;
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
 /*       List<Map> maps = testMapper.findZtreeDemo();
        System.out.println(maps.size());*/
       int  a=  testMapper.isthereachildnode(3);
        System.out.println(a);
  }
    @Test
    public void insert(){
        ApplicationContext context = new
                ClassPathXmlApplicationContext("classpath:spring-config.xml");
        ZtreeDemoService testMapper =  context.getBean("ztreeDemoService",ZtreeDemoService.class);
        Map<String,Object> map = new HashMap<>();
        map.put("id",0);
        map.put("name","测试");
        map.put("parent_id",0);
        map.put("avaialble",true);
        map.put("parent_ids","0/");
        int a = testMapper.insertZtreeDemo(map);
        System.out.println(a);
    }
}
