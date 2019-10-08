package com.kiger.cirlinklist;

/**
 * @ClassName Josephus
 * @Description TODO
 * @Author zk_kiger
 * @Date 2019/9/14 11:32
 * @Version 1.0
 */

public class CirLink<T> {

    // 创建内部类Node,代表链表结点
    private class Node {
        private Element data;
        private Node next;

        public Node() {}

        public Node(Element data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    // 头结点
    private Node first;
    // 尾结点
    private Node last;
    // 保存含有的结点数
    private int size;

    // 创建空链表
    public CirLink() {
        first = null;
        last = null;
    }

    // 使用尾插法向链表插入元素
    public void add(Element element) {
        // 如果为空链表
        if (first == null) {
            first = new Node(element, first);
            last = first;
        } else {
            // 创建一个新结点
            Node newNode = new Node(element, first);

            // 尾结点next指向新的结点
            last.next = newNode;

            // 尾结点指向新的结点
            last = newNode;
        }

        size++;
    }

    // 打印链表
    public void print() {
        Node curNode = first;

        for (int i = 0; i < size; i++) {
            System.out.println(curNode.data);
            curNode = curNode.next;
        }

    }

    // 约瑟夫环问题
    public void JosephsOperate(int m) {
        boolean flag = true;
        Node pre = null;
        Node cur = null;
        pre = cur = last;

        while (flag) {
            for (int i = 1; i <= m; i++) {
                pre = cur;
                cur = cur.next;
            }

            if (pre == cur) flag = false;

            m = cur.data.password;
            System.out.printf("第%d个人出列(密码:%d)\n",cur.data.id,cur.data.password);
            pre.next = cur.next;
            cur = pre;
        }
    }

}
