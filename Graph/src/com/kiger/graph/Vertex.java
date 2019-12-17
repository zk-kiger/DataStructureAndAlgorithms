package com.kiger.graph;

/**
 * @ClassName Vertex
 * @Description 邻接表的顶点
 * 该类借鉴于一种Bag的数据结构
 * @Author zk_kiger
 * @Date 2019/11/12 22:30
 * @Version 1.0
 */

public class Vertex<T> {
    // 存储顶点数据
    private T data;
    // 下一条边信息
    private Edge first;
    // 该顶点连顶点数
    private int size;
    // 该结点入度个数
    private int in;

    public Vertex() {}

    // 使用链表的头插法将边结点的信息插入顶点后面
    public void add(Edge item) {
        if (first == null) {
            first = item;
        } else {
            item.setNext(first);
            first = item;
        }
        size++;
    }

    // 获得下标为index的元素
    public Edge get(int index) {
        if (index >= size)
            return null;
        Edge cur = first;
        while (index != 0) {
            cur = cur.getNext();
            --index;
        }
        return cur;
    }


    public int getIn() {
        return in;
    }

    public void setIn(int in) {
        this.in = in;
    }

    // 获取元素个数
    public int size() {
        return size;
    }

    // 判断是否为空
    public boolean isEmpty() {
        return (first == null);
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Edge getFirst() {
        return first;
    }

    public void setFirst(Edge first) {
        this.first = first;
    }

    @Override
    public String toString() {
        return "Vertex{" +
                "data=" + data +
                ", first=" + first +
                ", size=" + size +
                '}';
    }
}
