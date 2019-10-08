package com.kiger.Link;

import java.util.Iterator;

/**
 * @ClassName LinkList
 * @Description 单向链表实现
 * @Author zk_kiger
 * @Date 2019/6/19 9:36
 * @Version 1.0
 */

public class LinkList<T> {
    // 定义一个内部类Node，代表链表的结点
    private class Node {
        // 保存数据
        private T data;
        // 指向下一个结点的引用
        private Node next;

        // 无参构造器
        public Node() {}

        // 初始化全部属性
        public Node(T data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    // 保存头结点
    private Node first;
    // 保存尾结点
    private Node last;
    // 保存已含有的结点数
    private int size;

    // 创建空链表
    public LinkList() {
        first = null;
        last = null;
    }

    public LinkList(T element) {
        first = new Node(element, null);
        // 只有一个结点，first、last都指向该结点
        last = first;
        size++;
    }

    /**
     * 返回链表长度
     * @return 结点个数
     */
    public int length() {
        return size;
    }

    /**
     * 获取指定索引出的元素
     * @param index 索引
     * @return 索引处结点存放数据
     */
    public T get(int index) {
        return this.getNodeByIndex(index).data;
    }

    /**
     * 获取指定索引的结点
     * @param index 索引
     * @return 索引结点的引用
     */
    public Node getNodeByIndex(int index) {
        if(index < 0 || index > size-1) {
            throw new IndexOutOfBoundsException("索引超出线性表范围");
        }

        // 创建一个结点指向first结点，用于遍历链表
        Node currNode = first;

        // 遍历链表
        for (int i = 0; i < size && currNode != null ; i++ , currNode = currNode.next) {
            if(i == index) {
                return currNode;
            }
        }

        return null;
    }

    /**
     * 按值查找所在索引
     * @param element 值
     * @return 所在索引
     */
    public int locate(T element) {
        Node currNode = first;

        for (int i = 0; i < size && currNode != null; i++, currNode = currNode.next) {
            if(currNode.data.equals(element)) {
                return i;
            }
        }

        return -1;
    }

    /**
     * 指定位置插入元素
     * @param element 插入元素
     * @param index 指定下标
     */
    public void insert(T element, int index) {
        if(index < 0 || index > size-1) {
            throw new IndexOutOfBoundsException("索引超出线性表范围");
        }

        // 如果是空链表
        if(first == null) {
            add(element);
        } else {
            // 当index=0时，在链表头插入
            if(index == 0) {
                addAtHead(element);
            } else {
                // 获得插入位置的前一个结点
                Node prev = getNodeByIndex(index - 1);

                // 此处代码洁简
                // Node nextNode = prev.next;
                // Node newNode = new Node(element, null);
                // prev.next = newNode;
                // newNode.next = nextNode;
                prev.next = new Node(element, prev.next);

                size++;
            }
        }
    }

    /**
     * 在链表尾部插入元素
     * @param element 插入元素
     */
    public void add(T element) {
        // 如果是空链表
        if(first == null) {
            first = new Node(element, null);
            last = first;
        } else {
            // 创建新结点
            Node newNode = new Node(element, null);

            // 尾结点的next指向新结点
            last.next = newNode;

            // 尾结点指向新结点
            last = newNode;
        }

        size++;
    }

    /**
     * 链表头部插入元素
     * @param element 插入元素
     */
    public void addAtHead(T element) {
        // 创建一个新结点
        Node newNode = new Node(element, null);

        // 新结点的next指向first
        newNode.next = first;

        // first指向新结点
        first = newNode;

        //若插入前是空表
        if(last == null) {
            last = first;
        }

        size++;
    }

    /**
     * 删除指定索引处的元素
     * @param index 索引
     * @return 删除元素
     */
    public T delete(int index) {
        if(index < 0 || index > size-1) {
            throw new IndexOutOfBoundsException("索引超出线性表范围");
        }

        Node delNode = null;

        // 如果要删除头结点
        if(index == 0) {
            delNode = first;
            first = first.next;
        } else {
            // 返回删除结点的前一个结点
            Node prev = getNodeByIndex(index-1);

            // 获取删除结点
            delNode = prev.next;

            prev.next = delNode.next;

            // 将被删除结点的next引用置为null
            delNode.next = null;
        }

        size--;
        return delNode.data;
    }

    /**
     * 删除最后一个元素
     * @return 元素
     */
    public T remove() {
        return delete(size-1);
    }

    /**
     * 判断线性表是否为空
     * @return 判断结果
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 清空线性表
     */
    public void clear() {
        first = null;
        last = null;
        size = 0;
    }

    public String toString() {

        if (isEmpty()) {
            return "[]";
        } else {
            StringBuilder sb = new StringBuilder();
            for (Node current = first; current != null; current = current.next) {
                sb.append(current.data.toString());
            }
            if (sb.charAt(0) == '+') {
                sb.deleteCharAt(0);
            }
            return sb.toString();
        }
    }
}
