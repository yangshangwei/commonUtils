/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.artisan.commonUtils.List;

import com.google.common.collect.Lists;
import org.apache.commons.collections4.ListUtils;

import java.util.List;

/**
 * description
 *
 * @author hujun [KF.hujunA@h3c.com]
 * @date 2019/03/20 18:56
 * @since 1.0
 */
public class ListsPartitionTest2 {
    public static void main(String[] args) {
        List<Integer> intList = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8,0,1);
        System.out.println(ListUtils.partition(intList, 3));
    }
}
