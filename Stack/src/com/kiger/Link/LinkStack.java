package com.kiger.Link;

/**
 * @ClassName LinkStack
 * @Description TODO
 * @Author zk_kiger
 * @Date 2019/6/20 22:07
 * @Version 1.0
 */

public class LinkStack<T> {
    private class Node {
        private T data;
        private Node next;

        public Node() {}

        public Node(T data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    private Node top;
    private int size = 0;

    public LinkStack() {}

    public LinkStack(T element) {
        top = new Node(element, null);
        size++;
    }

    // 返回链栈长度
    public int length() {
        return size;
    }

    // 入栈
    public void push(T element) {
        top = new Node(element, top);
        size++;
    }

    // 出栈
    public T pop() {
        if (size == 0) {
            return null;
        }
        // 记录栈顶结点
        Node oldNode = top;
        // 栈顶指针指向新的栈顶结点
        top = top.next;
        // 释放原栈顶引用
        oldNode.next = null;
        size--;

        return oldNode.data;
    }

    // 获取栈顶元素
    public T getTop() {
        if(size == 0) {
            return null;
        }

        return top.data;
    }

    // 判断是否为空
    public boolean isEmpty() {
        return size == 0;
    }

    // 清空栈
    public void clear() {
        size = 0;
        top = null;
    }

    public String toString() {
        if (size == 0) {
            return "[]";
        }

        else {
            StringBuilder sb = new StringBuilder("[");

            for (Node current = top; current != null; current = current.next) {
                sb.append(current.data.toString() + ", ");
            }

            sb.append("]");

            int length = sb.length();

            return sb.delete(length - 3, length - 1).toString();
        }
    }
}
