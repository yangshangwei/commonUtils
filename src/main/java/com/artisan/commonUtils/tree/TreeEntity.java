/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.artisan.commonUtils.tree;

import java.util.List;

/**
 * description
 *
 * @author hujun [KF.hujunA@h3c.com]
 * @date 2019/03/28 19:52
 * @since 1.0
 */
public interface TreeEntity<E> {
    public String getId();
    public String getParentId();
    public void setChildList(List<E> childList);
}
