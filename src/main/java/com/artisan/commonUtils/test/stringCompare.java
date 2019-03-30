/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.artisan.commonUtils.test;

/**
 * description
 *
 * @author hujun [KF.hujunA@h3c.com]
 * @date 2019/03/28 14:06
 * @since 1.0
 */
public class stringCompare {
    public static void main(String[] args) {
        String str1 = "我渝卡北测区";
        String str2 = "测试卡口是我";
        int count = compare(str1,str2,0);
        System.out.println(count);

        int t = namematching(str1, str2);
        System.out.println(t);

        if(str1.substring(0,4).equals(str2.substring(0,4))) {
            System.out.println(1);
        }
    }

    public static int compare(String str1,String str2,int start){
        if(start<0)return 0;
        if(str1==null || str2==null)return 0;
        int len=str1.length()>str2.length()?str2.length():str1.length();
        if(start>=len)return 0;
        char c1,c2;
        int okLen=0;
        for(int i=start;i<len;i++){
            c1=str1.charAt(i);
            c2=str2.charAt(i);
            if(c1==c2){
                okLen++;
            }else{
                okLen=0;
            }
        }
        return okLen;
    }

    private static int namematching(String o1, String o2){
        Boolean flag = false;
        int count = 0;
        for (int i = 0;i < o1.length();i++) {
            for (int j = 0;j < o2.length();j++) {
                if (o1.charAt(i) == o2.charAt(j)) {
                    count = count+1;
                }
            }
        }
        if (count >= 6) {
         flag = true;
        }

        return count;
    }

}
