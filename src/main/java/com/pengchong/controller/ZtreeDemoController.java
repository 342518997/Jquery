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
         //如果是文件
        if(sourcejsons.size()==1) {
            for (Map<String, Object> source : sourcejsons) {
                Map<String, Object> maps = new HashMap<>();
                maps.put("name", source.get("name"));
                maps.put("parent_id", targetjsons.get("id"));
                maps.put("avaialble", true);
                maps.put("parent_ids", targetjsons.get("parent_ids") + String.valueOf(maps.get("parent_id")) + "/");
                //插入记录返回主键
                 this.ztreeDemoService.insertZtreeDemo(maps) ;
            }
        }else if (sourcejsons.size()>1){//如果是文件夹
            Map<String, Object> root = new HashMap<>();
            root.put("name", sourcejsons.get(0).get("name"));
            root.put("parent_id", targetjsons.get("id"));
            root.put("avaialble", true);
            root.put("parent_ids", targetjsons.get("parent_ids") + String.valueOf(root.get("parent_id")) + "/");
            //插入记录返回主键
            int rootid = this.ztreeDemoService.insertZtreeDemo(root) ;
            sourcejsons.remove(0);
            for (Map<String, Object> source : sourcejsons) {
                    
            }
        }
         out.print(true);

    }
}
