package com.kiger.Sequence;

import java.util.Arrays;

/**
 * @ClassName SequenceQueue
 * @Description 顺序队列实现
 * @Author zk_kiger
 * @Date 2019/6/20 22:35
 * @Version 1.0
 */

public class SequenceQueue<T> {
    // 默认队列长度
    private final int DEFAULT_SIZE = 16;

    // 队列容量
    private int capacity;

    // 定义数组用于保存顺序队列
    private Object[] elementData;

    // 保存队列元素个数
    private int size = 0;

    // 队列第一个对象的位置
    private int front;

    // 队列当前对象的位置
    private int rear;

    // 默认容量
    public SequenceQueue() {
        front = rear = 0;
        capacity = DEFAULT_SIZE;
        elementData = new Object[capacity];
    }

    // 指定队列容量
    public SequenceQueue(int initSize) {
        front = rear = 0;
        capacity = initSize;
        elementData = new Object[capacity];
    }

    // 获取队列长度
    public int length() {
        return size;
    }

    // 添加元素到队列
    public void put(T element) throws Exception {
        if(size > 0 && front == rear){
            throw new Exception("队列已满");
        } else {
            elementData[rear] = element;
            rear = (rear + 1) % capacity;
            size++;
        }
    }

    // 移除队首元素
    public T remove() {
        T top = null;

        if(size == 0) {
            throw new RuntimeException("队列为空");
        } else {
            // 获取队列队首元素
            top = (T)elementData[front];
            // 将队首元素位置值为空
            elementData[front] = null;
            front = (front + 1) % capacity;
            size--;
        }

        return top;
    }

    // 清空队列
    public void clear() {
        Arrays.fill(elementData, null);
        size = 0;
    }

    // 判断队列是否为空
    public boolean isEmpty() {
        return size == 0;
    }

    // 获取队首元素
    public T getFrontElement() {
        if(size == 0) {
            throw new RuntimeException("队列为空");
        } else {
            return (T) elementData[front];
        }
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        } else {
            StringBuilder sb = new StringBuilder("[");

            for (int i = front, j = 0; j < size; i++, j++) {
                if(i == capacity) {
                    i = 0;
                }
                sb.append(elementData[i].toString() + ", ");
            }

            int len = sb.length();

            return sb.delete(len - 2, len).append("]").toString();
        }
    }
}
