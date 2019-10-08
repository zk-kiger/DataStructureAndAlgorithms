package com.kiger.Link;

import org.junit.Test;

/**
 * @ClassName LinkListTest
 * @Description 测试
 * @Author zk_kiger
 * @Date 2019/6/19 21:25
 * @Version 1.0
 */

public class LinkListTest {
    @Test
    public void test1() {
        // 测试构造函数
        LinkList<String> list = new LinkList("好");
        System.out.println(list);

        // 测试添加元素
        list.add("ni");
        list.add("没");
        System.out.println(list);

        // 在头部添加
        list.addAtHead("五月");
        System.out.println(list);

        // 在指定位置添加
        list.insert("摩卡", 2);
        System.out.println(list);

        // 获取指定位置处的元素
        System.out.println("第2个元素是（从0开始计数）：" + list.get(2));

        // 返回元素索引
        System.out.println("摩卡在的位置是：" + list.locate("摩卡"));
        System.out.println("moka所在的位置：" + list.locate("moka"));

        // 获取长度
        System.out.println("当前线性表的长度：" + list.length());

        // 判断是否为空
        System.out.println(list.isEmpty());

        // 删除最后一个元素
        list.remove();
        System.out.println("调用remove()后：" + list);

        // 获取长度
        System.out.println("当前线性表的长度：" + list.length());

        // 删除指定位置处元素
        list.delete(3);
        System.out.println("删除第4个元素后：" + list);

        // 获取长度
        System.out.println("当前线性表的长度：" + list.length());

        // 清空
        list.clear();
        System.out.println(list);

        // 判断是否为空
        System.out.println(list.isEmpty());
    }

    public static void main(String[] args) {
        PolynomialCalculation pc = new PolynomialCalculation();
        LinkList<Polynomial> pa = pc.setLinkListA();
        LinkList<Polynomial> pb = pc.setLinkListB();

        System.out.println("pa = " + pa.toString());
        System.out.println("pb = " + pb.toString());

        LinkList linkList = PolynomialCalculation.AddPolyn(pa, pb);
        System.out.println("pa + pb = " + linkList.toString());

        LinkList linkList1 = PolynomialCalculation.SubPolyn(pa, pb);
        System.out.println("pa - pb = " + linkList1.toString());

        LinkList linkList2 = PolynomialCalculation.MultPolyn(pa, pb);
        System.out.println("pa * pb = " + linkList2.toString());

        LinkList linkList3 = PolynomialCalculation.CoefPolyn(pa);
        System.out.println("pa' = " + linkList3.toString());
    }
}
