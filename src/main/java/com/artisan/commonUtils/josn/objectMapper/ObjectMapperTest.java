/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.artisan.commonUtils.josn.objectMapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.text.SimpleDateFormat;

/**
 * description
 *
 * @author hujun [KF.hujunA@h3c.com]
 * @date 2019/03/30 16:11
 * @since 1.0
 */
public class ObjectMapperTest {
    public static void main(String[] args) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();

        //序列化的时候序列对象的所有属性
//        objectMapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);
//
//        //反序列化的时候如果多了其他属性,不抛出异常
//        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//
//        //如果是空对象的时候,不抛异常
//        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
//
//        //取消时间的转化格式,默认是时间戳,可以取消,同时需要设置要表现的时间格式
//        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
//        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
//        objectMapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);
//        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, true);

        Person person = new Person(1, "tom", "123");
        String jsonString = objectMapper.writeValueAsString(person);
        System.out.println("JsonString: " + jsonString);

        Person person1 = objectMapper.readValue(jsonString, Person.class);
        System.out.println(person1.toString());


    }
}
