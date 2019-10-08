package com.kiger.Link;

import org.junit.Test;

/**
 * @ClassName LinkListTest
 * @Description 测试
 * @Author zk_kiger
 * @Date 2019/6/20 22:15
 * @Version 1.0
 */

public class LinkListTest {
    @Test
    public void test() {

        // 以指定第一个元素和底层数组长度的方式构建顺序栈
        LinkStack<String> linkStack = new LinkStack<String>("我");
        System.out.println("当前所含内容" + linkStack);

        // 压入数据元素,元素格式大于了定义栈时底层数组的长度
        linkStack.push("是");
        linkStack.push("liuhao");
        linkStack.push("程序员");
        // 发现是先入后出的方式打印的
        System.out.println("当前所含内容" + linkStack);
        // 获取栈中元素个数
        System.out.println("当前栈中元素个数是：" + linkStack.length());
        // 获取栈顶元素
        System.out.println("当前栈顶元素是：" + linkStack.getTop());

        // 弹出元素
        System.out.println("弹出元素：" + linkStack.pop());
        // 发现是先入后出的方式打印的
        System.out.println("当前所含内容" + linkStack);
        // 获取栈顶元素
        System.out.println("当前栈顶元素是：" + linkStack.getTop());
        // 获取栈中元素个数
        System.out.println("当前栈中元素个数是：" + linkStack.length());

        // 判断是否为空栈
        System.out.println("当前栈是否为空：" + linkStack.isEmpty());
        // 清空栈
        linkStack.clear();
        // 判断是否为空栈
        System.out.println("当前栈是否为空：" + linkStack.isEmpty());
        // 获取栈顶元素,空栈时返回null
        System.out.println("当前栈顶元素是：" + linkStack.getTop());
        // 获取栈中元素个数
        System.out.println("当前栈中元素个数是：" + linkStack.length());

        // 空栈时进行弹出元素
        System.out.println("弹出元素：" + linkStack.pop());
    }
}
