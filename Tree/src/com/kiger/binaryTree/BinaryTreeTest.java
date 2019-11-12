package com.kiger.binaryTree;

import java.util.LinkedList;

public class BinaryTreeTest {

    public static void main(String[] args) {
        BinaryTree<String> tree = new BinaryTree<>();
        LinkedList<String> linkedList = new LinkedList<>();
        String[] strings = {"A", "B", "D", "#", "#", "E", "#", "#", "C", "F", "#", "#", "G", "#", "#"};
        for (String str :
                strings) {
            linkedList.add(str);
        }
        tree.buildTree(linkedList);
        System.out.println("树形形状：");
        tree.PrintTree();
        System.out.println();

        System.out.print("先序遍历  (递归):");
        tree.preOrder();
        System.out.println();
        System.out.print("先序遍历(非递归):");
        tree.preOrder_();
        System.out.println();

        System.out.print("中序遍历  (递归):");
        tree.inOrder();
        System.out.println();
        System.out.print("中序遍历(非递归):");
        tree.inOrder_();
        System.out.println();

        System.out.print("后序遍历  (递归)");
        tree.postOrder();
        System.out.println();
        System.out.print("后序遍历(非递归):");
        tree.postOrder_();
        System.out.println();

        int num = tree.getLeafNodes();
        System.out.println("叶子结点个数：" + num);

        int depth = tree.getDepth();
        System.out.println("树的深度：" + depth);

        int num_ = tree.getNodeNumLevel(2);
        System.out.println("第二层结点个数：" + num_);
    }
}
