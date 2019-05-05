/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.artisan.commonUtils.base;

import com.github.pagehelper.Page;
import org.apache.poi.ss.formula.functions.T;



/**
 * description
 *
 * @author hujun [KF.hujunA@h3c.com]
 * @date 2019/04/01 17:24
 * @since 1.0
 */
public class PageResult {
    private Pagination paging;
    private T data;

    public PageResult() {
    }

    public Pagination getPaging() {
        return this.paging;
    }

    public void setPaging(Pagination paging) {
        this.paging = paging;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

//    public static <T> PageResult<T> of(T list, Page page) {
//        PageResult result = new PageResult();
//        Pagination paging = new Pagination();
//        paging.setPageNum(page.getPageNum());
//        paging.setPageSize(page.getPageSize());
//        paging.setTotal((int)page.getTotal());
//        result.setPaging(paging);
//        result.setData(list);
//        return result;
//    }
}
