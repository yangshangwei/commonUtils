/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.artisan.commonUtils.other;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * description
 *
 * @author hujun [KF.hujunA@h3c.com]
 * @date 2019/03/09 09:41
 * @since 1.0
 */
public class NumberFormatDemo01 {
    public static void main(String args[]){
        NumberFormat nf = null ;    // 声明一个NumberFormat对象
        nf = NumberFormat.getInstance() ;  // 得到默认的数字格式化显示
        System.out.println("格式化之后的数字：" + nf.format(10000000)) ;
        System.out.println("格式化之后的数字：" + nf.format(1000.345)) ;
        DecimalFormat df = null ;

        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        System.out.println("格式化之后的数字：" + nf.format(15019)) ;
    }
}



