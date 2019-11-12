package com.kiger.arrayBinary;

import org.junit.Test;

import java.util.Scanner;

/**
 * @ClassName ArrayBinTreeTest
 * @Description 测试
 * @Author zk_kiger
 * @Date 2019/6/27 22:30
 * @Version 1.0
 */

public class ArrayBinTreeTest {
    @Test
    public void test() {
        ArrayBinTree<String> binTree = new ArrayBinTree<String>(4,"根");
        binTree.add(0, "0右", false);
        binTree.add(2, "2右", false);
        binTree.add(2, "2左", true);

        binTree.add(0, "0左", true);
        binTree.add(1, "1左", true);
        System.out.println(binTree);

        System.out.println(binTree.getLeft(2));
        System.out.println(binTree.getParent(6));
    }

    @Test
    public void test1() {

    }

    public static int lg2(double N) {
        int k = 0;
        int m = 1;
        Scanner input = new Scanner(System.in);
        while (N >= m) {
            m *= 2;
            k++;
        }
        return k-1;
    }
}
