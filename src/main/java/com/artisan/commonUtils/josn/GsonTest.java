/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.artisan.commonUtils.josn;

import com.google.gson.Gson;

/**
 * description
 *
 * @author hujun [KF.hujunA@h3c.com]
 * @date 2019/03/25 14:27
 * @since 1.0
 */
public class GsonTest {
    class Student{
        private Long id;
        private String name;
        private double score;
        public Long getId() {
            return id;
        }
        public void setId(Long id) {
            this.id = id;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public double getScore() {
            return score;
        }
        public void setScore(double score) {
            this.score = score;
        }
        @Override
        public String toString() {
            return "Student [id=" + id + ", name=" + name + ", score=" + score
                    + "]";
        }
    }


    public static void main(String[] args) {
        GsonTest.Student s=new GsonTest().new Student();
        s.setId(1L);
        s.setName("小明");
        s.setScore(99.9);
        Gson gs=new Gson();


        String json=gs.toJson(s);   //先将javaBean转换成json
        System.out.println(json);

        Student xiaoming=gs.fromJson(json, Student.class);  //再将json转换成javaBean
        System.out.println(xiaoming);


    }
}
