/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.artisan.commonUtils.tree.OtherTree;



import com.artisan.commonUtils.utils.CheckUtil;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * description
 *
 * @author hujun [KF.hujunA@h3c.com]
 * @date 2019/04/01 17:07
 * @since 1.0
 */
public class TreeNodeTest {
    public static void main(String args[]) {
        List<TreeNode> menuList=new ArrayList<TreeNode>();
        TreeNode mu=new TreeNode();
        mu.setId("1");

        TreeNode mu1=new TreeNode();
        mu1.setId("2");

        TreeNode mu2=new TreeNode();
        mu2.setId("3");

        TreeNode mu3=new TreeNode();
        mu3.setId("4");

        TreeNode mu4=new TreeNode();
        mu4.setId("5");

        TreeNode mu5=new TreeNode();
        mu5.setId("6");

        TreeNode mu6=new TreeNode();
        mu6.setId("7");

        TreeNode mu7=new TreeNode();
        mu7.setId("8");

        menuList.add(mu);
        menuList.add(mu1);
        menuList.add(mu2);
        menuList.add(mu3);
        menuList.add(mu4);
        menuList.add(mu5);
        menuList.add(mu6);
        menuList.add(mu7);
    }
/*
    private List<TreeNode> buildTree(List<TreeNode> resTreeNodes) {
        if (CheckUtil.isNullOrEmpty(resTreeNodes)) {
            return new ArrayList<>(0);
        }
        Map<String, TreeNode> nodeMap = new HashMap<>(16);
        List<TreeNode> tollgateChannelNodeList = Lists.newArrayList();
//        resTreeNodes.forEach(node -> nodeMap.put(node.getId(), node));
        for (TreeNode node : resTreeNodes) {
            nodeMap.put(node.getId(), node);
        }
        TreeNode root = null;
        root = buildTreeNode(resTreeNodes, nodeMap, tollgateChannelNodeList, root);
//        tollgateChannelNodeList.forEach(tollgateChannelNode -> {
        for (TreeNode tollgateChannelNode : tollgateChannelNodeList) {
            TreeNode pNode = nodeMap.get(tollgateChannelNode.getDeviceCode());
            if (null != pNode) {
                if (pNode.getChildren() == null) {
                    pNode.setChildren(new ArrayList<TreeNode>());
                }
                pNode.getChildren().add(tollgateChannelNode);
            }
        });
        List<TreeNode> rootList = new ArrayList<>();
        rootList.add(root);
        if (root == null) {
            return resTreeNodes;
        }
        return rootList;

    }
    */

    /**
     * 构建树形结构
     * @param resTreeNodes
     * @param nodeMap
     * @param tollgateChannelNodeList
     * @param root
     * @return

    private TreeNode buildTreeNode(List<TreeNode> resTreeNodes, Map<String, TreeNode> nodeMap,
                                           List<TreeNode> tollgateChannelNodeList, TreeNode root) {
        for (TreeNode resTreeNode : resTreeNodes) {
            if (CheckUtil.isNullOrEmpty(resTreeNode.getOrgCode())) {
                root = resTreeNode;
                continue;
            }
            if (resTreeNode.getNodeType() == CHANNEL_TREE_NODE_TYPE) {
                tollgateChannelNodeList.add(resTreeNode);
            } else {
                TreeNode pNode = nodeMap.get(resTreeNode.getOrgCode());
                if (null != pNode) {
                    if (pNode.getChildren() == null) {
                        pNode.setChildren(new ArrayList<>());
                    }
                    pNode.getChildren().add(resTreeNode);
                }
            }
        }
        return root;
    }
     */
}
