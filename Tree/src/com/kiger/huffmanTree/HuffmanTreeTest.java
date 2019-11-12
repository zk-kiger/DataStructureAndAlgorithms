package com.kiger.huffmanTree;


import java.util.Scanner;

public class HuffmanTreeTest {

    public static void main(String[] args) {
        HuffmanTree huffmanTree = new HuffmanTree();
        Scanner input = new Scanner(System.in);
        System.out.print("请输入需要编码的字符串: ");
        String needCodeStr = input.next();
        String codeStr = huffmanTree.codeStr(needCodeStr);
        System.out.println("编码: " + codeStr);

        String deCodeStr = huffmanTree.DeCodeStr(codeStr);
        System.out.print("将上面编码译码为字符串: " + deCodeStr);
    }

}
