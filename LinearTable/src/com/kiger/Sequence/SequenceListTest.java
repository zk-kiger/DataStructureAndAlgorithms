package com.kiger.Sequence;

import org.junit.Test;

/**
 * @ClassName SequenceListTest
 * @Description 测试
 * @Author zk_kiger
 * @Date 2019/6/18 21:30
 * @Version 1.0
 */

public class SequenceListTest {
    @Test
    public void test() {
        SequenceList<String> sList = new SequenceList<>();

        // 添加元素
        sList.add("w");
        sList.add("s");
        sList.add("z");
        System.out.println(sList);

        // 指定位置插入
        sList.insert("lala", 1);
        System.out.println(sList);

        // 指定位置删除
        sList.delete(0);
        System.out.println(sList);

        // 获取指定位置元素
        System.out.println(sList.get(1));

        // 清空
        sList.clear();
        System.out.println(sList);
    }
}
