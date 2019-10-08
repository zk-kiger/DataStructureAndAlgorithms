package com.kiger.Sequence;

import java.util.Arrays;

/**
 * @ClassName SequenceStack
 * @Description 顺序栈实现
 * @Author zk_kiger
 * @Date 2019/6/20 21:16
 * @Version 1.0
 */

public class SequenceStack<T> {
    private final int DEFAULT_SIZE = 10;

    // 保存当前数组的长度
    private int capacity;

    // 数组长度不够时，程序每次增加的数组长度
    private int capacityIncrement = 0;

    // 保存顺序栈的数据元素
    private Object[] elementData;

    // 顺序栈元素个数
    private int size;

    // 以默认长度创建空的顺序栈
    public SequenceStack() {
        capacity = DEFAULT_SIZE;
        elementData = new Object[capacity];
    }

    // 以一个初始化元素创建顺序栈
    public SequenceStack(T element) {
        this();
        elementData[0] = element;
        size++;
    }

    /**
     * 以指定长度创建顺序栈
     * @param element 第一个元素
     * @param initSize 指定顺序栈的底层数组长度
     */
    public SequenceStack(T element, int initSize) {
        capacity = initSize;
        elementData = new Object[capacity];
        elementData[0] = element;
        size++;
    }

    /**
     * 以指定长度创建顺序栈,同时指定底层数组增量
     * @param element 第一个元素
     * @param initSize 指定顺序栈的底层数组长度
     * @param capacityIncrement 底层数组长度不够时，每次增加的增量
     */
    public SequenceStack(T element, int initSize, int capacityIncrement) {
        capacity = initSize;
        this.capacityIncrement = capacityIncrement;
        elementData = new Object[capacity];
        elementData[0] = element;
        size++;
    }

    /**
     * 获取元素个数
     * @return
     */
    public int length() {
        return size;
    }

    /**
     * 入栈
     * @param element
     */
    public void push(T element) {
        this.ensureCapacity(size + 1);

        // 将元素放到数组，同时长度+1
        elementData[size++] = element;
    }

    /**
     * 扩大数组容量
     * @param minCapacity 所需的数组容量
     */
    private void ensureCapacity(int minCapacity) {
        // 数组原有长度小于所需容量
        if(minCapacity > capacity) {
            // 如果给定了数组增量
            if(capacityIncrement > 0) {
                while (minCapacity > capacity) {
                    capacity += capacityIncrement;
                }
            } else {
                while (minCapacity > capacity) {
                    capacity <<= 1;
                }
            }

            // 将原有的数组长度变为新的capacity
            elementData = Arrays.copyOf(elementData, capacity);
        }
    }

    /**
     * 出栈
     * @return 出栈元素
     */
    public T pop() {
        // 若当前为空栈
        if(size == 0) {
            return null;
        }

        T oldValue = (T)elementData[size-1];
        // 释放栈顶元素，同时长度-1
        elementData[--size] = null;

        return oldValue;
    }

    /**
     * 返回栈顶元素
     * @return 栈顶元素
     */
    public T getPeek() {
        if (size == 0) {
            return null;
        }

        return (T)elementData[size-1];
    }

    /**
     * 返回是否为空
     * @return
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 清空栈
     */
    public void clear() {
        Arrays.fill(elementData, null);
        size = 0;
    }

    public String toString() {
        if (size == 0) {
            return "[]";
        } else {
            StringBuilder sb = new StringBuilder("[");
            for (int i = size - 1; i >= 0; i--) {
                sb.append(elementData[i].toString() + ", ");
            }

            sb.append("]");

            int length = sb.length();

            // 删除多余的“,”和空格
            return sb.delete(length - 3, length - 1).toString();
        }
    }
}
