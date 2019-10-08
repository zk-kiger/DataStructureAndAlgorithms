package com.kiger.cirlinklist;

import java.util.Scanner;

/**
 * @ClassName Josephus
 * @Description TODO
 * @Author zk_kiger
 * @Date 2019/9/14 12:11
 * @Version 1.0
 */

public class Josephus {

    private static final int MAX = 100;
    private static int n,m;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        do {
            if (n > MAX) {
                System.out.println("人数太多,请重新输入!");
            }
            System.out.printf("请输入人数n(最多%d个): ",MAX);
            n = input.nextInt();
        } while (n > MAX);
        System.out.println("请输入初始密码m: ");
        m = input.nextInt();
        // 创建循环链表
        CirLink<Element> cirLink = new CirLink<>();

        // 创建有n个结点的循环链表
        for (int i = 1; i <= n; i++) {
            System.out.printf("请输入第%d个人的密码: ",i);
            int password = input.nextInt();
            Element e = new Element(i, password);
            cirLink.add(e);
        }
        System.out.println("循环链表创建完成!");
        System.out.println("-----------------打印循环链表-----------------");
        cirLink.print();
        System.out.println("-----------------打印出队情况-----------------");
        // 运行约瑟夫环问题
        cirLink.JosephsOperate(m);
    }

}
