package com.kiger.Sequence;

import org.junit.Test;

/**
 * @ClassName SequenceStackTest
 * @Description 测试
 * @Author zk_kiger
 * @Date 2019/6/20 22:04
 * @Version 1.0
 */

public class SequenceStackTest {
    @Test
    public void test() {
        // 以指定第一个元素和底层数组长度的方式构建顺序栈
        SequenceStack<String> sStack = new SequenceStack<String>("我", 2);
        System.out.println("当前所含内容" + sStack);

        // 压入数据元素,元素格式大于了定义栈时底层数组的长度
        sStack.push("是");
        sStack.push("liuhao");
        sStack.push("程序员");
        // 发现是先入后出的方式打印的
        System.out.println("当前所含内容" + sStack);
        // 获取栈中元素个数
        System.out.println("当前栈中元素个数是：" + sStack.length());
        // 获取栈顶元素
        System.out.println("当前栈顶元素是：" + sStack.getPeek());

        // 弹出元素
        System.out.println("弹出元素：" + sStack.pop());
        // 发现是先入后出的方式打印的
        System.out.println("当前所含内容" + sStack);
        // 获取栈顶元素
        System.out.println("当前栈顶元素是：" + sStack.getPeek());
        // 获取栈中元素个数
        System.out.println("当前栈中元素个数是：" + sStack.length());

        // 判断是否为空栈
        System.out.println("当前栈是否为空：" + sStack.isEmpty());
        // 清空栈
        sStack.clear();
        // 判断是否为空栈
        System.out.println("当前栈是否为空：" + sStack.isEmpty());
        // 获取栈顶元素,空栈时返回null
        System.out.println("当前栈顶元素是：" + sStack.getPeek());
        // 获取栈中元素个数
        System.out.println("当前栈中元素个数是：" + sStack.length());

        // 空栈时进行弹出元素
        System.out.println("弹出元素：" + sStack.pop());
    }
}
