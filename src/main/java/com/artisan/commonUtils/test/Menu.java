/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.artisan.commonUtils.test;

import java.util.List;

/**
 * description
 *
 * @author hujun [KF.hujunA@h3c.com]
 * @date 2019/03/27 14:12
 * @since 1.0
 */
public class Menu implements TreeEntity<Menu> {
    private String id;
    private String name;
    private String parentId;
    private List<Menu> childList;

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public List<Menu> getChildList() {
        return childList;
    }

    @Override
    public void setChildList(List<Menu> childList) {
        this.childList = childList;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", parentId='" + parentId + '\'' +
                ", childList=" + childList +
                '}';
    }
}
