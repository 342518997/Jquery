package com.pengchong.service;

import java.util.List;
import java.util.Map;

public interface ZtreeDemoService {

    List<Map> findZtreeDemo ();

    int insertZtreeDemo(Map<String , Object> map);

    boolean isthereachildnode(Integer id);
}
