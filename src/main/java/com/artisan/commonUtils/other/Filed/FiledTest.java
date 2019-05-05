/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.artisan.commonUtils.other.Filed;

import java.lang.reflect.Field;

/**
 * description
 *
 * @author hujun [KF.hujunA@h3c.com]
 * @date 2019/04/02 17:44
 * @since 1.0
 */
public class FiledTest {
    public static void main(String[]args) throws NoSuchFieldException, SecurityException {
        Person person =new Person();
        //通过Class.getDeclaredField(String name)获取类或接口的指定已声明字段。
        Field f1=person.getClass().getDeclaredField("name");
        System.out.println("-----Class.getDeclaredField(String name)用法-------");
        System.out.println(f1);
        System.out.println("-----Class.getDeclaredFields()用法-------");
        //通过Class.getDeclaredFields()获取类或接口的指定已声明字段。
        Field []f2=person.getClass().getDeclaredFields();
        for(Field field:f2)
        {
            System.out.println(field);
        }
        System.out.println("-----Class.getField(String name)用法-------");
        //通过Class.getField(String name)返回一个类或接口的指定公共成员字段，私有成员报错。
        Field f3=person.getClass().getField("name");
        System.out.println(f3);
        //如果获取age属性(私有成员) 则会报错
        //Field f3=person.getClass().getField("name");
        System.out.println("-----Class.getFields()用法-------");
        //通过Class.getField()，返回 Class 对象所表示的类或接口的所有可访问公共字段。
        Field []f4=person.getClass().getFields();
        for(Field fields:f4)
        {
            //因为只有name属性为共有，因此只能遍历出name属性
            System.out.println(fields);
        }
    }
}
