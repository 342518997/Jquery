package com.pengchong.service;

import com.pengchong.dao.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Map;

@Service
public class TestServiceImpl implements TestService{

    @Autowired
    private TestMapper testMapper;

    public List<Map> findList(Map map) {
        return this.testMapper.findList(map);
    }
}
