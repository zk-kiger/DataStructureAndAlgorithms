package com.kiger.Sequence;

import java.util.Arrays;

/**
 * @ClassName SequenceList
 * @Description 线性表顺序存储实现
 * @Author zk_kiger
 * @Date 2019/6/18 20:36
 * @Version 1.0
 */

public class SequenceList<T> {
    // 默认初始化数组大小为16
    private final int DEFAULT_SIZE = 16;

    // 数组容量
    private int capacity;

    // 定义一个数组，用于保存线性表
    private Object[] elementData;

    // 保存顺序表中元素的个数
    private int size = 0;

    /**
     * 以默认容量创建空的线性表
     */
    public SequenceList() {
        capacity = DEFAULT_SIZE;
        elementData = new Object[capacity];
    }

    /**
     * 以一个初始化元素创建默认容量的线性表
     *
     * @param element 初始化元素
     */
    public SequenceList(T element) {
        this();
        elementData[0] = element;
        size++;
    }

    /**
     * 以一个初始化元素创建指定容量的线性表
     *
     * @param element  初始化元素
     * @param initSize 指定容量
     */
    public SequenceList(T element, int initSize) {
        capacity = 1;

        // 把capacity设为大于initSize的最小的2的n次方
        while (capacity < initSize) {
            capacity <<= 1;
        }

        elementData = new Object[capacity];
        elementData[0] = element;
        size++;
    }

    /**
     * 获取线性表的大小(元素个数)
     *
     * @return 元素个数
     */
    public int length() {
        return size;
    }

    /**
     * 获取索引i的元素
     *
     * @param i 索引
     * @return 索引i的元素
     */
    public T get(int i) {
        if (i < 0 || i > size - 1) {
            throw new IndexOutOfBoundsException("索引超出线性表范围");
        }

        return (T) elementData[i];
    }

    /**
     * 根据元素查找在线性表中的索引
     *
     * @param element 查找元素
     * @return 索引
     */
    public int indexOf(T element) {
        for (int i = 0; i < size; i++) {
            if (elementData[i].equals(element)) {
                return i;
            }
        }

        return -1;
    }

    /**
     * 在顺序表指定索引处插入元素
     *
     * @param element 插入元素
     * @param index   指定索引
     */
    public void insert(T element, int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("索引超出线性表范围");
        }

        // 让数组容量扩大
        ensureCapacity(size + 1);

        // 调用System.arraycopy(),让数组index及后面的数据向后移动一位
        System.arraycopy(elementData, index, elementData, index + 1, size - index);

        elementData[index] = element;
        size++;
    }

    /**
     * 在顺序表末端添加一个元素
     *
     * @param element 添加元素
     */
    public void add(T element) {
        insert(element, size);
    }

    /**
     * 为数组扩容
     *
     * @param minCapacity 所需的最小容量
     */
    public void ensureCapacity(int minCapacity) {
        // 如果需要的数组容量大于当前数组容量
        if (minCapacity > capacity) {
            while (capacity < minCapacity) {
                capacity <<= 1;
            }
        }

        // 调用Arrays.copyOf()可以让系统重新生成一个数组，并将引用赋给elementData
        elementData = Arrays.copyOf(elementData, capacity);
    }

    /**
     * 删除指定索引的元素
     *
     * @param index 索引
     * @return 删除元素
     */
    public T delete(int index) {
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException("索引超出线性表范围");
        }

        // 获取删除索引处的元素
        T oldValue = (T) elementData[index];
        // 计算需要移动的元素个数
        int numMoved = size - index - 1;

        if (numMoved > 0) {
            // 将index之后的元素向前移动一位
            System.arraycopy(elementData, index + 1, elementData, index, numMoved);
        }

        //让顺序表最后一位为空
        elementData[--size] = null;
        return oldValue;
    }

    /**
     * 删除最后一个元素
     *
     * @return 删除元素
     */
    public T remove() {
        return delete(size - 1);
    }

    /**
     * 判断线性表是否为空
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 清空线性表
     */
    public void clear() {
        // 将所有元素变为null
        Arrays.fill(elementData, null);
        size = 0;
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        } else {
            StringBuilder sb = new StringBuilder("[");

            for (int i = 0; i < size; i++) {
                sb.append(elementData[i].toString() + ", ");
            }

            int len = sb.length();

            return sb.delete(len - 2, len).append("]").toString();
        }
    }
}
