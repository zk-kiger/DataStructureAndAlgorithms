package com.kiger.Demo;

import java.util.Scanner;
import java.util.Stack;

/**
 * @ClassName Dijkstra双栈算术表达式求值
 * @Description TODO
 * @Author zk_kiger
 * @Date 2019/9/5 22:50
 * @Version 1.0
 */

public class DijkstraDoubleStackArithmetic {

    public static void main(String[] args) {
        Stack<String> ops = new Stack<>();
        Stack<Double> vals = new Stack<>();
        String str = new Scanner(System.in).nextLine();
        for (int i = 0; i < str.length(); i++) {
            String s = String.valueOf(str.charAt(i));
            // 读取字符串，如果是运算符入运算符栈
            if (s.equals("("));
            else if (s.equals("+")) ops.push(s);
            else if (s.equals("-")) ops.push(s);
            else if (s.equals("*")) ops.push(s);
            else if (s.equals("/")) ops.push(s);
            else if (s.equals("sqrt")) ops.push(s);
            else if (s.equals(")")) {
                // 如果为右括号，弹出运算符和操作数，计算结果入栈
                String op = ops.pop();
                double v = vals.pop();
                if (op.equals("+")) v += vals.pop();
                else if (s.equals("-")) v -= vals.pop();
                else if (s.equals("*")) v *= vals.pop();
                else if (s.equals("/")) v /= vals.pop();
                else if (s.equals("sqrt")) v = Math.sqrt(v);
                vals.push(v);
            }
            // 如果是数字，那么入数字栈
            else {
                vals.push(Double.parseDouble(s));
            }
        }
        System.out.println(vals.pop());
    }
}
