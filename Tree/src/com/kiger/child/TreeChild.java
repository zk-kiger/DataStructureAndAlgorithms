package com.kiger.child;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName TreeChild
 * @Description 树的孩子表示法
 * @Author zk_kiger
 * @Date 2019/6/26 22:23
 * @Version 1.0
 */

public class TreeChild<E> {

    private static class ChildNode {
        // 记录当前结点位置
        private int pos;
        // 记录子结点的引用
        private ChildNode next;

        public ChildNode(int pos, ChildNode next) {
            this.pos = pos;
            this.next = next;
        }
    }

    public static class Node<T> {
        T data;
        // 记录第一个子结点
        ChildNode first;

        public Node(){}

        public Node(T data) {
            this.data = data;
            this.first = null;
        }

        public String toString() {
            if (first != null) {
                return "TreeChild$Node[data=" + data + ", first=" + first.pos
                        + "]";
            }
            return "TreeChild$Node[data=" + data + ", first=-1]";
        }
    }

    private final int DEFAULT_TREE_SIZE = 100;
    private int treeSize = 0;

    // 使用一个Node[]数组来记录该数的所有结点
    private Node<E>[] nodes;

    // 记录结点数量
    private int nodeNums;

    // 以指定根结点创建树
    public TreeChild(E data) {
        treeSize = DEFAULT_TREE_SIZE;
        nodes = new Node[treeSize];
        nodes[0] = new Node<>(data);
        nodeNums++;
    }

    // 以指定根节点、指定treeSize创建树
    public TreeChild(E data, int treeSize) {
        this.treeSize = treeSize;
        this.nodes = new Node[treeSize];
        nodes[0] = new Node<E>(data);
        nodeNums++;
    }

    // 为指定结点添加子结点
    public void addNode(E data, Node<E> node) {
        for (int i = 0; i < treeSize; i++) {
            // 存在空位就加入
            if(nodes[i] == null) {
                nodes[i] = new Node<>(data);
                // 如果该结点没有第一个子结点链
                if(node.first == null) {
                    node.first = new ChildNode(i, null);
                } else {
                    // 如果有，则依次取出该结点的子结点，直到叶结点
                    ChildNode currNode = node.first;
                    while(currNode.next != null) {
                        currNode = currNode.next;
                    }
                    // 在叶结点后添加该子结点
                    currNode.next = new ChildNode(i, null);
                }
                nodeNums++;
                return;
            }
        }
        throw new RuntimeException("树已满，无法添加新结点");
    }

    // 判断是否为空
    public boolean isEmpty() {
        return nodes[0] == null;
    }

    // 获取根结点
    public Node<E> getRoot() {
        return nodes[0];
    }

    // 获取指定结点的所有子结点
    public List<Node<E>> getChildren(Node<E> node) {
        List<Node<E>> list = new ArrayList<>();
        // 获取指定结点的第一个结点
        ChildNode currNode = node.first;
        // 沿着第一个结点不断搜索，并存入list
        while(currNode != null) {
            list.add(nodes[currNode.pos]);
            currNode = currNode.next;
        }
        return list;
    }

    // 返回指定结点的第index个子结点
    public Node<E> getChildByIndex(Node<E> node, int index) {
        // 获取该结点的第一个结点
        ChildNode currNode = node.first;
        // 沿着孩子链搜寻
        for (int i = 0; currNode != null; i++) {
            if(index == i) {
                return nodes[currNode.pos];
            }
            currNode = currNode.next;
        }
        return null;
    }

    // 递归的方式返回某个结点的深度
    private int getDeep(Node<E> node) {
        if(node.first == null) {
            return 1;
        } else {
            int max = 0;
            ChildNode currNode = node.first;
            while(currNode != null) {
                // 获取以其子结点为根的子树的深度
                int tmp = this.getDeep(nodes[currNode.pos]);
                if(tmp > max) {
                    max = tmp;
                }
                currNode = currNode.next;
            }

            return max+1;
        }
    }

    // 获取树的深度
    public int getDeep() {
        return this.getDeep(getRoot());
    }

    // 返回包含指定结点的索引
    public int pos(Node<E> node) {
        for (int i = 0; i < treeSize; i++) {
            if(nodes[i] == node) {
                return i;
            }
        }
        return -1;
    }
}
