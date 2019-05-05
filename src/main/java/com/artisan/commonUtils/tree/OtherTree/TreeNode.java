/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.artisan.commonUtils.tree.OtherTree;

import lombok.Data;

import java.util.List;

/**
 * description
 *
 * @author hujun [KF.hujunA@h3c.com]
 * @date 2019/04/01 17:06
 * @since 1.0
 */
@Data
public class TreeNode {
    private String id;
    private List<TreeNode> children;
}
