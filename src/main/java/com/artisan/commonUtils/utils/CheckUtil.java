/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.artisan.commonUtils.utils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * description
 *
 * @author hujun [KF.hujunA@h3c.com]
 * @date 2019/04/01 17:29
 * @since 1.0
 */
public class CheckUtil {
    public CheckUtil() {
    }

    public static boolean isNullOrEmpty(Object pObj) {
        if (pObj == null) {
            return true;
        } else if (pObj instanceof String) {
            return ((String)pObj).length() == 0;
        } else if (pObj instanceof Collection) {
            return ((Collection)pObj).isEmpty();
        } else if (pObj instanceof Map) {
            return ((Map)pObj).size() == 0;
        } else {
            return false;
        }
    }

    public static boolean isNotNullAndNotEmpty(Object pObj) {
        if (pObj == null) {
            return false;
        } else if (pObj instanceof String) {
            return ((String)pObj).length() != 0;
        } else if (pObj instanceof Collection) {
            return !((Collection)pObj).isEmpty();
        } else if (pObj instanceof Map) {
            return ((Map)pObj).size() != 0;
        } else {
            return true;
        }
    }

    public static void checkTest() {
        String name = new String();
//        Assert.isTrue(isNullOrEmpty(name), "name不为空");
        List<String> names = Lists.newArrayList();
//        Assert.isTrue(isNullOrEmpty(names), "names列表不为空");
        Map<String, String> userMap = Maps.newHashMap();
//        Assert.isTrue(isNullOrEmpty(userMap), "useMap不为空");
    }
}
