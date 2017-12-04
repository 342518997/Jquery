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
         /*List<Map<String,Object>> sourcejsons = null;*/
         Map<String,Object> targetjsons = JSONObject.fromObject(targetjson);
         List<Map<String,Object>> lists = directory(sourcejsons,targetjsons);
         out.print(true);

    }

    //复制菜单文件夹
    public List<Map<String,Object>> directory(List<Map<String ,Object>> sourcejsons,Map<String,Object> targetjsons){
        List<Map<String ,Object>>  Copiedmenu =  new ArrayList<>();
        List<Map<String,Object>>   Copiedmenu1=  new ArrayList<>();
        List<Map<String,Object>>   Copiedmenu2=  new ArrayList<>();

        for (int i = 0 ; i<sourcejsons.size(); i++){
              Number num = (Number)sourcejsons.get(i).get("id");
                if(this.ztreeDemoService.isthereachildnode(num.intValue())){
                    if(Copiedmenu.size() >0){
                        for (Map<String,Object> c : Copiedmenu){
                            if(c.get("qid").equals(sourcejsons.get(i).get("parent_id"))){
                                Map<String,Object> map = new HashMap<>();
                                map.put("name", sourcejsons.get(i).get("name"));
                                map.put("parent_id", c.get("id"));
                                map.put("avaialble", true);
                                map.put("parent_ids", c.get("parent_ids") + String.valueOf(map.get("parent_id")) + "/");
                                //插入记录返回主键
                                int id =  this.ztreeDemoService.insertZtreeDemo(map);
                                map.put("id",id);
                                map.put("qid",sourcejsons.get(i).get("id"));
                                Copiedmenu1.add(map);
                            }

                        }
                        Copiedmenu.addAll(Copiedmenu1);
                        Copiedmenu1.clear();
                    }else{
                        //文件夹
                        Map<String,Object> map = new HashMap<>();
                        map.put("name", sourcejsons.get(i).get("name"));
                        map.put("parent_id", targetjsons.get("id"));
                        map.put("avaialble", true);
                        map.put("parent_ids", targetjsons.get("parent_ids") + String.valueOf(map.get("parent_id")) + "/");
                        //插入记录返回主键
                        int id =  this.ztreeDemoService.insertZtreeDemo(map);
                        map.put("id",id);
                        map.put("qid",sourcejsons.get(i).get("id"));
                        Copiedmenu1.add(map);

                    }
                    Copiedmenu.addAll(Copiedmenu1);
                    Copiedmenu1.clear();

                }else{
                    //文件
                    if (sourcejsons.size()==1){
                        Map<String,Object> map = isCopy(sourcejsons.get(i),targetjsons);
                       Copiedmenu.add(map);
                        Copiedmenu1.add(map);
                    }
                    if(Copiedmenu.size() >1){
                        for (Map<String,Object> c : Copiedmenu){
                             if(c.get("qid").equals(sourcejsons.get(i).get("parent_id"))){
                                 Map<String,Object> map = new HashMap<>();
                                 map.put("name", sourcejsons.get(i).get("name"));
                                 map.put("parent_id", c.get("id"));
                                 map.put("avaialble", true);
                                 map.put("parent_ids", c.get("parent_ids") + String.valueOf(map.get("parent_id")) + "/");
                                 //插入记录返回主键
                                 int id =  this.ztreeDemoService.insertZtreeDemo(map);
                                 map.put("id",id);
                                 map.put("qid",sourcejsons.get(i).get("id"));
                                 Copiedmenu2.add(map);

                             }

                        }
                        Copiedmenu.addAll(Copiedmenu2);
                        Copiedmenu2.clear();
                    }

                }
        }
        return  Copiedmenu;
    }

    //复制菜单文件
    public Map<String,Object> isCopy(Map<String,Object> traversal,Map<String,Object> targetjsons ){
        Map<String, Object> maps = new HashMap<>();
        maps.put("name", traversal.get("name"));
        maps.put("parent_id", targetjsons.get("id"));
        maps.put("avaialble", true);
        maps.put("parent_ids", targetjsons.get("parent_ids") + String.valueOf(maps.get("parent_id")) + "/");
       //插入记录返回主键
        int id =  this.ztreeDemoService.insertZtreeDemo(maps);
        maps.put("id",id);
        maps.put("qid",traversal.get("id"));

        return  maps;
    }
}
