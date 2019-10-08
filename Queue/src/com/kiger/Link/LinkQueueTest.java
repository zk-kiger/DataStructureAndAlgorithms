package com.kiger.Link;

import org.junit.Test;

/**
 * @ClassName LinkQueueTest
 * @Description TODO
 * @Author zk_kiger
 * @Date 2019/6/22 13:40
 * @Version 1.0
 */

public class LinkQueueTest {
    @Test
    public void test() {
        LinkQueue<String> queue = new LinkQueue<>();
        System.out.println("队列是否为空："  + queue.isEmpty());
        queue.put("A");
        queue.put("B");
        queue.put("C");
        queue.put("D");
        System.out.println(queue);
        System.out.println("队首元素：" + queue.getFrontElemennt());
        System.out.println("队列长度：" + queue.length());
        System.out.println("移除队首：" + queue.remove());
        System.out.println(queue);
        System.out.println("清空队列");
        queue.clear();
        System.out.println(queue);
        System.out.println("队列是否为空："  + queue.isEmpty());
    }
}
