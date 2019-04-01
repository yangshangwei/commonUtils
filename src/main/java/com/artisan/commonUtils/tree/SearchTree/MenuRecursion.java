package com.artisan.commonUtils.tree.SearchTree;

import java.util.ArrayList;
import java.util.List;

public class MenuRecursion {
    //子节点
    static List<MenuTree> childMenuTree = new ArrayList<MenuTree>();

    /**
     * 获取某个父节点下面的所有子节点
     * @param MenuTreeList
     * @param pid
     * @return
     */
    public static List<MenuTree> treeMenuTreeList(List<MenuTree> MenuTreeList, int pid) {
        /**
         * 递归的思路：以查id = 5下的子节点为例子
         * 1、pid=5进来，找到子节点为6的，进入递归，此时pid = 6
         * 2、for循环在从头开始，寻找6的子节点，找到则在进入递归，
         * 没有则进入childMenuTree.add(mu)，此时pid回到5.------这一步是找6的所有子节点
         * 3、这时候pid = 5，接着上次的地方（id= 6）接着找，从id = 7开始，找到了8，奖pid = 8
         * 接着找，找不到后将id= 8加入list中，此时回到pid = 7，也找不到，奖id = 7添加list，
         * 最好for循环到id = 8（pid = 5）结束。
         */

        for (MenuTree mu : MenuTreeList) {
            //遍历出父id等于参数的id，add进子节点集合
            if (Integer.valueOf(mu.getPid()) == pid) {
                //递归遍历下一级
                treeMenuTreeList(MenuTreeList, Integer.valueOf(mu.getId()));
                childMenuTree.add(mu);
            }
        }
        return childMenuTree;
    }



    public static void main(String args[]) {
        List<MenuTree> MenuTreeList = new ArrayList<MenuTree>();
        MenuTree mu = new MenuTree();
        mu.setId("1");
        mu.setName("目录");
        mu.setPid("0");
        MenuTree mu1 = new MenuTree();
        mu1.setId("2");
        mu1.setName("目录1");
        mu1.setPid("1");
        MenuTree mu2 = new MenuTree();
        mu2.setId("3");
        mu2.setName("目录1.1");
        mu2.setPid("2");
        MenuTree mu3 = new MenuTree();
        mu3.setId("4");
        mu3.setName("目录1.2");
        mu3.setPid("2");
        MenuTree mu4 = new MenuTree();
        mu4.setId("5");
        mu4.setName("目录2");
        mu4.setPid("1");
        MenuTree mu5 = new MenuTree();
        mu5.setId("6");
        mu5.setName("目录2.1");
        mu5.setPid("5");
        MenuTree mu6 = new MenuTree();
        mu6.setId("7");
        mu6.setName("目录2.2");
        mu6.setPid("5");
        MenuTree mu7 = new MenuTree();
        mu7.setId("8");
        mu7.setName("目录2.2.1");
        mu7.setPid("7");
        MenuTreeList.add(mu);
        MenuTreeList.add(mu1);
        MenuTreeList.add(mu2);
        MenuTreeList.add(mu3);
        MenuTreeList.add(mu4);
        MenuTreeList.add(mu5);
        MenuTreeList.add(mu6);
        MenuTreeList.add(mu7);

        //找到id = 1 下面的所有子节点
        List<MenuTree> childList = treeMenuTreeList(MenuTreeList, 5);
        for (MenuTree m : childList) {
            System.out.println(m.getId() + "   " + m.getName());
        }
    }

}
