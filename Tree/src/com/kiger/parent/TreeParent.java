package com.kiger.parent;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName TreeParent
 * @Description 树的父亲表示法
 * @Author zk_kiger
 * @Date 2019/6/25 22:53
 * @Version 1.0
 */

public class TreeParent<E> {

    public static class Node<T> {
        T data;
        // 记录该结点父亲结点的索引
        int parent;

        public Node() {}

        public Node(T data) {
            this.data = data;
        }

        public Node(T data, int parent) {
            this.data = data;
            this.parent = parent;
        }

        public String toString() {
            return "TreeParent$Node[data=" + data + ", parent=" + parent + "]";
        }
    }

    private final int DEFAULT_TREE_SIZE = 100;
    private int treeSize = 0;

    // 使用一个Node[]数组来记录树的所有结点
    private Node<E>[] nodes;

    // 记录结点数
    private int nodeNums;

    // 以指定结点创建树
    public TreeParent(E data) {
        this.treeSize = DEFAULT_TREE_SIZE;
        this.nodes = new Node[treeSize];
        nodes[0] = new Node<E>(data, -1);
        nodeNums++;
    }

    // 以指定根结点、指定treeSize创建树
    public TreeParent(E data, int treeSize) {
        this.treeSize = treeSize;
        this.nodes = new Node[treeSize];
        nodes[0] = new Node<E>(data, -1);
        nodeNums++;
    }

    // 判断是否为空树
    public boolean isEmpty() {
        // 根结点是否为null
        return nodes[0] == null;
    }

    // 返回包含指定结点的索引位置
    public int pos(Node<E> node) {
        for (int i = 0; i < treeSize; i++) {
            if(nodes[i] == node) {
                return i;
            }
        }
        return -1;
    }

    // 为指定结点添加子结点
    public void addNode(E data, Node<E> parent) {
        for (int i = 0; i < treeSize; i++) {
            // 找到存放结点数组第一个空位置，用来存放新的结点
            if(nodes[i] == null) {
                nodes[i] = new Node<>(data, this.pos(parent));
                nodeNums++;
                return;
            }
        }
        throw new RuntimeException("树满了,无法添加新结点");
    }

    // 获取根结点
    public Node<E> getRoot() {
        return nodes[0];
    }

    // 获取指定结点的父结点
    public Node<E> getParent(Node<E> node) {
        return nodes[node.parent];
    }

    // 获取指定结点的子结点
    public List<Node<E>> getChildren(Node<E> node) {
        List<Node<E>> list = new ArrayList<>();
        for (int i = 0; i < treeSize; i++) {
            if (nodes[i] != null && nodes[i].parent == this.pos(node)) {
                list.add(nodes[i]);
            }
        }
        return list;
    }

    // 获取树的深度
    public int getDeep() {
        int max = 0;
        for (int i = 0; i < treeSize && nodes[i] != null; i++) {
            // 初始化当前结点的深度
            int def = 1;
            // m记录当前结点父结点的位置
            int m = nodes[i].parent;
            while(m != -1 && nodes[i] != null) {
                // 继续向上搜索父结点
                m = nodes[m].parent;
                def++;
            }
            if (def > max){
                max = def;
            }
        }
        return max;
    }

    // 删除指定结点的子树
    public void removeChildren(Node<E> node) {
        for (int i = 0; i < treeSize; i++) {
            if(nodes[i] != null && nodes[i].parent == this.pos(node)) {
                nodes[i] = null;
                nodeNums--;
            }
        }
    }
}
