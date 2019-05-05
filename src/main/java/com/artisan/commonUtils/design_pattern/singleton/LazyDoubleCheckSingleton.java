/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.artisan.commonUtils.design_pattern.singleton;

/**
 * description
 *
 * @author hujun [KF.hujunA@h3c.com]
 * @date 2019/04/10 14:22
 * @since 1.0
 */
public class LazyDoubleCheckSingleton {
    private volatile static LazyDoubleCheckSingleton lazyDoubleCheckSingleton = null;
    //是有构造方法，防止外面生成
    private LazyDoubleCheckSingleton(){}
    public static LazyDoubleCheckSingleton getLazyDoubleCheckSingleton(){
        if (lazyDoubleCheckSingleton == null) {
            /**
             * synchronized四种用法
             * 1、方法声明时使用,放在范围操作符(public等)之后,返回类型声明(void等)之前.即一次只能有一个线程进入该方法,
             * 其他线程要想在此时调用该方法,只能排队等候,
             * 2、对某一代码块使用,synchronized后跟括号,括号里是变量,这样,一次只有一个线程进入该代码块
             * 3、synchronized后面括号里是一对象,此时,线程获得的是对象锁
             * 4、synchronized后面括号里是类
             */
            synchronized (LazyDoubleCheckSingleton.class) {
                if (lazyDoubleCheckSingleton == null) {
                    lazyDoubleCheckSingleton = new LazyDoubleCheckSingleton();
                }
            }
        }
        return lazyDoubleCheckSingleton;
    }
}
