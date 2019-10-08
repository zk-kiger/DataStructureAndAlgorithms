package com.kiger.Sequence;

import org.junit.Test;

/**
 * @ClassName SequenceQueueTest
 * @Description 测试
 * @Author zk_kiger
 * @Date 2019/6/21 21:59
 * @Version 1.0
 */

public class SequenceQueueTest {
    @Test
    public void test() throws Exception {
        SequenceQueue<String> queue = new SequenceQueue<>(5);
        System.out.println("队列是否为空：" + queue.isEmpty());
        queue.put("A");
        queue.put("B");
        queue.put("C");
        queue.put("D");
        System.out.println(queue);
        System.out.println("队首元素为：" + queue.getFrontElement());
        System.out.println("队列长度为：" + queue.length());
        System.out.println("移除元素：" + queue.remove());
        System.out.println("队列长度为：" + queue.length());
        System.out.println(queue);
        queue.clear();
        System.out.println("队列长度为：" + queue.length());
    }
}
