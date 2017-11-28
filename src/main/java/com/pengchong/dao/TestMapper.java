package com.pengchong.dao;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository("testMapper")
public interface TestMapper {

    List<Map>  findList(Map map);

}
