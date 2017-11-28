package com.pengchong.service;


import com.pengchong.dao.ZtreeDemoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("ztreeDemoService")
public class ZtreeDemoServiceImpl implements  ZtreeDemoService{

    @Autowired
    private ZtreeDemoMapper ztreeDemoMapper;

    @Override
    public List<Map> findZtreeDemo() {
        return ztreeDemoMapper.findZtreeDemo();
    }
}
