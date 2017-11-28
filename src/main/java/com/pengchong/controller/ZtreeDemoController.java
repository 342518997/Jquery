package com.pengchong.controller;


import com.pengchong.service.ZtreeDemoService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@Controller
public class ZtreeDemoController {

    @Autowired
    private ZtreeDemoService ztreeDemoService;

    @RequestMapping("/findZtreeDemo")
    public void findZtreeDemo(HttpServletResponse response) throws IOException {
        /* 设置格式为text/json    */
        response.setContentType("text/json");
        /*设置字符集为'UTF-8'*/
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.print(JSONArray.fromObject(ztreeDemoService.findZtreeDemo()).toString());
    }
    @RequestMapping("/replication")
    public void replication(HttpServletResponse response,String
            sourcejson,String targetjson)throws  IOException{

         PrintWriter out = response.getWriter();
         List<Map<String,Object>> sourcejsons =(List<Map<String,Object>>) JSONArray.toCollection(JSONArray.fromObject(sourcejson),Map.class);// 首先把字符串转成 list  对象
         Map<String,Object> targetjsons = JSONObject.fromObject(targetjson);
         List<Map> inser = new ArrayList<>();
 /*        for(Map<String,Object> source : sourcejsons){
             Map<String,Object> maps = new HashMap<>();
             maps.putAll(source);
             maps.put("parent_id",targetjsons.get("id"));
             maps.put("avaialble",true);
             maps.put("parent_ids",targetjsons.get("parent_ids")+ (String) maps.get("parent_id")+"/" );
             inser.add(maps);

         }*/
         Map<String,Object> map = (Map<String, Object>) sourcejsons.get(0);

         out.print(true);

    }
}
