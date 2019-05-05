/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.artisan.commonUtils.design_pattern.singleton;

/**
 * description
 *
 * @author hujun [KF.hujunA@h3c.com]
 * @date 2019/04/10 14:16
 * @since 1.0
 */
public class LazySingleton {
    private static LazySingleton lazySingleton = null;
    private LazySingleton(){}
    public synchronized static LazySingleton getInstance(){
        if (lazySingleton == null) {
            lazySingleton = new LazySingleton();
        }
        return lazySingleton;
    }

}
