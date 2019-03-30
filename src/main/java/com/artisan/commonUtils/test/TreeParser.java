/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.artisan.commonUtils.test;

import java.util.ArrayList;
import java.util.List;

/**
 * description
 *
 * @author hujun [KF.hujunA@h3c.com]
 * @date 2019/03/27 14:10
 * @since 1.0
 */
public class TreeParser {


    /**
     * 解析树形数据
     * @param topId
     * @param entityList
     * @return
     * @author fushuai
     * @date 2018年3月22日
     */
    public static <E extends TreeEntity<E>> List<E> getTreeList(String topId, List<E> entityList) {
        List<E> resultList=new ArrayList<>();

        //获取顶层元素集合
        String parentId;
        for (E entity : entityList) {
            parentId=entity.getParentId();
            if(parentId==null||topId.equals(parentId)){
                resultList.add(entity);
            }
        }

        //获取每个顶层元素的子数据集合
        for (E entity : resultList) {
            entity.setChildList(getSubList(entity.getId(),entityList));
        }

        return resultList;
    }

    /**
     * 获取子数据集合
     * @param id
     * @param entityList
     * @return
     * @author fushuai
     * @date 2018年3月22日
     */
    private  static  <E extends TreeEntity<E>>  List<E> getSubList(String id, List<E> entityList) {
        List<E> childList=new ArrayList<>();
        String parentId;

        //子集的直接子对象
        for (E entity : entityList) {
            parentId=entity.getParentId();
            if(id.equals(parentId)){
                childList.add(entity);
            }
        }

        //子集的间接子对象
        for (E entity : childList) {
            entity.setChildList(getSubList(entity.getId(), entityList));
        }

        //递归退出条件
        if(childList.size()==0){
            return null;
        }

        return childList;
    }

}
