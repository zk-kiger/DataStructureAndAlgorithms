package com.kiger.DeLink;

/**
 * @ClassName DeLinkList
 * @Description 双向链表实现
 * @Author zk_kiger
 * @Date 2019/6/19 21:34
 * @Version 1.0
 */

public class DeLinkList<T> {
    // 定义内部类，链表的结点
    private class Node {
        private T data;

        private Node prev;

        private Node next;

        public Node() {}

        public Node(T data, Node prev, Node next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }
    }

    // 头结点
    private Node first;
    // 尾结点
    private Node last;
    // 链表中的结点个数
    private int size;

    // 创建空链表
    public DeLinkList() {
        first = null;
        last = null;
    }

    // 已指定数据元素创建链表
    public DeLinkList(T element) {
        first = new Node(element, null, null);

        // 只有一个结点，first、last都指向
        last = first;
        size++;
    }

    /**
     * 返回链表长度
     * @return size
     */
    public int length() {
        return size;
    }

    /**
     * 获取指定位置的数据元素
     * @param index 指定位置
     * @return data
     */
    public T get(int index) {
        return this.getNodeByIndex(index).data;
    }

    /**
     * 获得指定位置的结点
     * @param index 指定位置
     * @return Node
     */
    private Node getNodeByIndex(int index) {
        if(index < 0 || index > size-1) {
            throw new IndexOutOfBoundsException("索引超出线性表范围");
        }

        // 在链表前半段
        if(index < size/2) {
            Node currNode = first;

            for (int i = 0; i < size / 2 && currNode != null; i++, currNode = currNode.next) {
                if(i == index) {
                    return currNode;
                }
            }
        } else {
            Node currNode = last;

            for (int i = size -1 ; i >= size / 2 && currNode != null; i--, currNode = currNode.prev) {
                if(i == index) {
                    return currNode;
                }
            }
        }

        return null;
    }

    /**
     * 按值查询所在的位置
     * @param element 值
     * @return 位置
     */
    public int locate(T element) {
        Node currNode = first;

        for (int i = 0; i < size - 1 && currNode != null; i++, currNode = currNode.next) {
            if (element.equals(currNode.data)) {
                return i;
            }
        }

        return -1;
    }

    /**
     * 向指定位置插入元素
     * @param element 元素
     * @param index 指定位置
     */
    public void insert(T element, int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("索引超出线性表范围");
        }

        if (first == null) {
            this.add(element);
        } else {
            if (0 == index) {
                this.addAtHead(element);
            } else {
                Node prev = this.getNodeByIndex(index - 1);// 获取插入节点的前一个节点
                Node next = prev.next;// 待插索引处的节点

                Node newNode = new Node(element, prev, next);// 新增节点，让它的prev指向之前的节点。next指向之后的节点

                prev.next = newNode;// 之前的节点的next指向当前节点
                next.prev = newNode;// 之后节点的prev指向当前节点

                size++;
            }
        }
    }

    /**
     * 尾插法
     * @param element
     */
    public void add(T element) {
        // 空表
        if(first == null){
            first = new Node(element, null, null);
            last = first;
        } else {
            Node newNode = new Node(element, last, null);
            last.next = newNode;
            last = newNode;
        }

        size++;
    }

    /**
     * 头插法
     * @param element 值
     */
    public void addAtHead(T element) {
        Node newNode = new Node(element, null, first);
        first.prev = newNode;
        first = newNode;

        // 如果插入之前为空表
        if(last == null) {
            last = first;
        }

        size++;
    }

    /**
     * 删除指定位置元素
     * @param index 指定位置
     * @return delNode.data
     */
    public T delete(int index) {
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException("索引超出线性表范围");
        }

        Node delNode = null;

        if(index == 0) {
            delNode = first;
            first = first.next;
            first.prev = null;
        } else {
            Node prev = getNodeByIndex(index - 1);
            delNode = prev.next;

            prev.next = delNode.next;

            // 可能删除的是最后一个元素
            if(delNode.next != null) {
                delNode.next.prev = prev;
            } else {
                last = prev;
            }

            delNode.prev = null;
            delNode.next = null;

        }
        size--;

        return delNode.data;
    }

    // 删除最后一个元素
    public T remove() {
        return this.delete(size - 1);
    }

    // 判断是否为空
    public boolean isEmpty() {
        return size == 0;
    }

    // 清空线性表
    public void clear() {
        first = null;
        last = null;
        size = 0;
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        } else {
            StringBuilder sb = new StringBuilder("[");
            for (Node current = first; current != null; current = current.next) {
                sb.append(current.data + ", ");
            }
            sb.append("]");

            int len = sb.length();

            // 删除多余的“,”和空格
            return sb.delete(len - 3, len - 2).toString();
        }
    }
}
