/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.artisan.commonUtils.base;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * description
 *
 * @author hujun [KF.hujunA@h3c.com]
 * @date 2019/04/01 17:27
 * @since 1.0
 */
public class PageQuery implements Serializable {
    private static final long serialVersionUID = 6320982368059524189L;
    @JsonProperty("page_num")
    private int pageNum = 1;
    @JsonProperty("page_size")
    private int pageSize = 20;
    @JsonProperty("order_field")
    private String orderField;
    @JsonProperty("order_rule")
    private String orderRule;

    public PageQuery() {
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

    public String getOrderField() {
        return this.orderField;
    }

    public void setOrderField(String orderField) {
        this.orderField = orderField;
    }

    public String getOrderRule() {
        return this.orderRule;
    }

    public void setOrderRule(String orderRule) {
        this.orderRule = orderRule;
    }
}

