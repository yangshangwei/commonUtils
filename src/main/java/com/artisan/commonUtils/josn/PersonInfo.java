/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.artisan.commonUtils.josn;

import lombok.Data;

/**
 * description
 *
 * @author hujun [KF.hujunA@h3c.com]
 * @date 2019/03/30 15:23
 * @since 1.0
 */
@Data
public class PersonInfo {
    private String name;
    private String sex;
    private int age;
    private boolean isStudent;
    private String[] hobbies;
}
