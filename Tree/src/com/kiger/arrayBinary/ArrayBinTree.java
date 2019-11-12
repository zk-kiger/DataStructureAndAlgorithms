package com.kiger.arrayBinary;

import java.util.Arrays;

/**
 * @ClassName ArrayBinTree
 * @Description 二叉树顺序结构实现
 * @Author zk_kiger
 * @Date 2019/6/27 22:07
 * @Version 1.0
 */

public class ArrayBinTree<T> {

    // 使用数组来记录所有结点
    private Object[] datas;
    private final int DEFAULT_DEEP = 8;
    // 保存该数的深度
    private int deep;
    // 数组长度
    private int arraySize;

    // 以默认深度创建二叉树
    public ArrayBinTree() {
        this.deep = DEFAULT_DEEP;
        this.arraySize = (int)Math.pow(2, deep);
        datas = new Object[arraySize];
    }

    // 指定深度创建
    public ArrayBinTree(int deep) {
        this.deep = deep;
        this.arraySize = (int)Math.pow(2, deep);
        datas = new Object[arraySize];
    }

    // 以指定深度、指定结点创建
    public ArrayBinTree(int deep, T data) {
        this.deep = deep;
        this.arraySize = (int)Math.pow(2, deep);
        datas = new Object[arraySize];
        datas[0] = data;
    }

    /**
     * 为指定结点添加子结点
     * @param index 指定结点索引
     * @param data 新结点数据
     * @param left 是否为左结点
     */
    public void add(int index, T data, boolean left) {
        if(datas[index] == null) {
            throw new RuntimeException(index + "处节点为空，无法添加子节点！");
        }

        if(2*index+1 > arraySize || 2*index+2 > arraySize) {
            throw new RuntimeException("树底层数组已满");
        }

        if(left) {
            if(datas[2*index+1] == null) {
                datas[2*index+1] = data;
            } else {
                throw new RuntimeException("该节点已存在！");
            }
        } else {
            if(datas[2*index+2] == null) {
                datas[2*index+2] = data;
            } else {
                throw new RuntimeException("该节点已存在！");
            }
        }

    }

    // 判断二叉树是否为空
    public boolean isEmpty() {
        return datas[0] == null;
    }

    // 获取根结点
    public T getRoot() {
        return (T)datas[0];
    }

    // 返回指定结点的父结点
    public T getParent(int index) {
        if(index == 0) {
            throw new RuntimeException("根节点不存在父节点！");
        }

        return (T) datas[(index-1)/2];
    }

    // 获取右子结点
    public T getRight(int index) {
        if(2*index+1 > arraySize || 2*index+2 > arraySize) {
            throw new RuntimeException("树底层数组已满");
        }

        return (T) datas[2*index+2];
    }

    // 获取左子结点
    public T getLeft(int index) {
        if(2*index+1 > arraySize || 2*index+2 > arraySize) {
            throw new RuntimeException("树底层数组已满");
        }

        return (T) datas[2*index+1];
    }

    // 返回二叉树的深度
    public int getDeep(){
        return deep;
    }

    // 返回指定数据的位置
    public int getPos(T data) {
        for (int i = 0; i < arraySize; i++) {
            if(datas[i].equals(data)) {
                return i;
            }
        }
        return -1;
    }

    public String toString(){
        return Arrays.toString(datas);
    }

}
