/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.artisan.commonUtils.josn;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * description
 *
 * @author hujun [KF.hujunA@h3c.com]
 * @date 2019/03/30 15:15
 * @since 1.0
 */
public class JSONObjectSample {
    public static void main(String[] args) {
        createJsonByMap();
        createJson();
        createJsonByJavaBean();
    }

    private static void createJsonByMap() {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("name", "John");
        data.put("sex", "male");
        data.put("age", 22);
        data.put("is_student", true);
        data.put("hobbies", new String[] {"hiking", "swimming"});

        JSONObject obj = new JSONObject(data);
        System.out.println(obj.toString());
    }

    private static void createJson() {
        JSONObject obj = new JSONObject();
        obj.put("name", "John");
        obj.put("sex", "male");
        obj.put("age", 22);
        obj.put("is_student", true);
        obj.put("hobbies", new String[] {"hiking", "swimming"});
        //调用toString()方法可直接将其内容打印出来
        System.out.println(obj.toString());

    }

    private static void createJsonByJavaBean() {
        PersonInfo info = new PersonInfo();
        info.setName("John");
        info.setSex("male");
        info.setAge(22);
        info.setStudent(true);
        info.setHobbies(new String[] {"hiking", "swimming"});

        JSONObject obj = new JSONObject(info);
        System.out.println(obj);
    }




}
