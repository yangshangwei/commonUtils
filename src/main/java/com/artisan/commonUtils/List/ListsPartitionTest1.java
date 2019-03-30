/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.artisan.commonUtils.List;

import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.List;

/**
 * description
 *
 * @author hujun [KF.hujunA@h3c.com]
 * @date 2019/03/20 17:59
 * @since 1.0
 */
public class ListsPartitionTest1 {
    public static void main(String[] args) {
        List<String> ls = Arrays.asList("1,2,3,4,5,6,7,8,9,1,2,4,5,6,7,7,6,6,6,6,6,66".split(","));
        System.out.println(Lists.partition(ls, 20));

    }
}
