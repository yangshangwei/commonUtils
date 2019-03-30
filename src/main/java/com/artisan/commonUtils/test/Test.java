/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.artisan.commonUtils.test;

import java.util.ArrayList;
import java.util.List;

/**
 * description
 *
 * @author hujun [KF.hujunA@h3c.com]
 * @date 2019/03/27 14:13
 * @since 1.0
 */
public class Test {
    public static void main(String[] args) {
        List<Menu> list=new ArrayList<>();
        Menu menu1=new Menu();
        menu1.setId("1");
        menu1.setParentId("0");
        menu1.setName("菜单1");
        list.add(menu1);

        Menu menu2=new Menu();
        menu2.setId("2");
        menu2.setParentId("0");
        menu2.setName("菜单2");
        list.add(menu2);

        Menu menu3=new Menu();
        menu3.setId("3");
        menu3.setParentId("1");
        menu3.setName("菜单11");
        list.add(menu3);

        Menu menu4=new Menu();
        menu4.setId("4");
        menu4.setParentId("3");
        menu4.setName("菜单111");
        list.add(menu4);

        Menu menu5=new Menu();
        menu5.setId("5");
        menu5.setParentId("3");
        menu5.setName("菜单111");
        list.add(menu5);

        Menu menu6=new Menu();
        menu6.setId("6");
        menu6.setParentId("5");
        menu6.setName("菜单111");
        list.add(menu6);

        Menu menu7=new Menu();
        menu7.setId("7");
        menu7.setParentId("6");
        menu7.setName("菜单111");
        list.add(menu7);

        System.out.println(list);
        List<Menu> lists = buildTree(list);
        System.out.println(lists);

        List<Menu> menus=TreeParser.getTreeList("0",lists);

        System.out.println("===============");
        System.out.println(menus);
    }


    public static List<Menu> buildTree(List<Menu> groupResDTOList) {
        List<Menu> result = new ArrayList<>();
        /**
         * 子节点为第一层循环，父节点为第二层循环
         * (1)当子节点未匹配到父节点，认为子节点时根节点
         * （2）当子节点匹配到父节点后，将子节点添加至父节点，跳回至子节点+1开始下一回合的遍历，直至所有的节点遍历完成
         */
        //子节点为第一层循环，父节点为第二层循环，当子节点匹配到父节点后，跳回至子节点开始下一回合的遍历
        for (Menu node1 : groupResDTOList) {
            boolean mark = false;
            for (Menu node2 : groupResDTOList) {
                if (node1.getParentId() != null && node1.getParentId().equals(node2.getId())) {
                    mark = true;
                    //必须要先初始化一下子节点，否则会报空指针异常
                    if (node2.getChildList() == null) {
                        node2.setChildList(new ArrayList<Menu>());
                    }
                        node2.getChildList().add(node1);
                    //跳出最近一层的循环
                    break;
                }
            }
            //mark用于标记根节点
            if (!mark) {
                result.add(node1);
            }
        }
        return result;
    }
}
