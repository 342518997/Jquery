package com.pengchong.model;

import java.io.Serializable;

public class ZtreeDemo  implements Serializable{

    private Integer id;
    private String name;
    private Integer parentId;
    private String  parentIds;
    private boolean avaialble;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getParentIds() {
        return parentIds;
    }

    public void setParentIds(String parentIds) {
        this.parentIds = parentIds;
    }

    public boolean isAvaialble() {
        return avaialble;
    }

    public void setAvaialble(boolean avaialble) {
        this.avaialble = avaialble;
    }
}
