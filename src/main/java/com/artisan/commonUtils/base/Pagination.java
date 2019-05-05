/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.artisan.commonUtils.base;

/**
 * description
 *
 * @author hujun [KF.hujunA@h3c.com]
 * @date 2019/04/01 17:24
 * @since 1.0
 */
public class Pagination {
    private int total;
    private int pageNum;
    private int pageSize;

    public Pagination() {
    }

    public int getTotal() {
        return this.total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPageNum() {
        return this.pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
