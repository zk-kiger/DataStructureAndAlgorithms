package com.kiger.Link;

/**
 * @ClassName LinkQueue
 * @Description 链式队列实现
 * @Author zk_kiger
 * @Date 2019/6/21 22:13
 * @Version 1.0
 */

public class LinkQueue<T> {
    private class Node {
        private T data;
        private Node next;

        public Node() {}

        public Node(T data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    private Node front;
    private Node rear;
    private int size = 0;

    public LinkQueue() {
        front = null;
        rear = null;
    }

    public LinkQueue(T element) {
        front = new Node(element, null);
        rear = front;
        size++;
    }

    // 获取元素个数
    public int length() {
        return size;
    }

    // 入队
    public void put(T element) {
        Node newNode = new Node(element, null);
        if(size == 0) {
            front = newNode;
            rear = front;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
        size++;
    }

    // 出队
    public T remove() {
        Node delNode = null;
        if(size == 0) {
            throw new RuntimeException("队列为空");
        } else {
            delNode = front;
            front = delNode.next;
            delNode.next = null;
        }

        return delNode.data;
    }

    // 返回队首元素
    public T getFrontElemennt() {
        return front.data;
    }

    // 判断队列是否为空
    public boolean isEmpty() {
        return size == 0;
    }

    // 清空队列
    public void clear() {
        front = rear = null;
        size = 0;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        } else {
            StringBuilder sb = new StringBuilder("[");
            for (Node current = front; current != null; current = current.next) {
                sb.append(current.data.toString() + ", ");
            }

            int len = sb.length();

            return sb.delete(len - 2, len).append("]").toString();
        }
    }
}
