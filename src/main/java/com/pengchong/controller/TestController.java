package com.pengchong.controller;

import com.pengchong.service.TestService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Controller
public class TestController {

    @Autowired
    private TestService testService;

    @RequestMapping("/test")
    public void testList(HttpServletResponse response,String name) throws IOException {
        /* 设置格式为text/json    */
        response.setContentType("text/json");
        /*设置字符集为'UTF-8'*/
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        Map<String , Object> map = new HashMap<>();
        map.put("name",name);
        JSONArray json = JSONArray.fromObject(testService.findList(map));
        out.print(json.toString());
        out.close();
    }


}
