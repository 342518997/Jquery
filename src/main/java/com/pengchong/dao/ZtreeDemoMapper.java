package com.pengchong.dao;

import com.pengchong.model.ZtreeDemo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository("ztreeDemoMapper")
public interface ZtreeDemoMapper {

      List<Map> findZtreeDemo ();

      int insertZtreeDemo(Map<String , Object> map);

      int isthereachildnode(Integer id);

      boolean rename(String id ,String name);
}
