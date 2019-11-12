package com.kiger.binaryTree;

import java.util.ArrayDeque;
import java.util.Scanner;
import java.util.Stack;

/**
 * @ClassName SuffixExpression
 * @Description 算术表达式求值
 * @Author zk_kiger
 * @Date 2019/9/26 11:32
 * @Version 1.0
 */

public class SuffixExpressionUtil {

    private SuffixExpressionUtil() {
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        char s[] = input.nextLine().toCharArray();
        ArrayDeque<String> suffix = SuffixExpressionUtil.TransformSuffix(s);
        System.out.println(suffix);
        Integer result = SuffixExpressionUtil.SuffixCalculate(suffix);
        System.out.println(result);
    }

    // 将中缀表达式转化为后缀表达式
    public static ArrayDeque<String> TransformSuffix(char[] s) {
        ArrayDeque<String> queue = new ArrayDeque<>();
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length; ) {
            // ‘ ’用于分割大于9的整数
            if (s[i] == ' ') {
                i++;
                continue;
            }

            // 如果是运算数将运算数全部入队(后缀表达式的队列)
            else if (s[i] >= '0' && s[i] <= '9') {
                int sum = 0;
                while(i < s.length && s[i] >= '0' && s[i] <= '9') {
                    sum = sum*10 + s[i]-'0';
                    i++;
                }
                queue.add(Integer.toString(sum));
            }

            // 如果遇到右括号,则将运算栈运算符全部入队,直到遇到左括号
            else if (s[i] == ')') {
                while(!stack.isEmpty() && stack.peek() != '(') {
                    queue.add(stack.pop() + "");
                }
                // 左括号出栈
                stack.pop();
                i++;
            }

            // 如果是操作符,根据符号优先级
            // 若下一个符号优先级大于栈顶符号,入栈
            // 若下一个符号优先级小于等于(+\-, *\/)栈顶符号,栈顶入队,一直循环比较
            else {
                while(!stack.isEmpty() && compare(stack.peek(), s[i]) < 0) {
                    queue.add(stack.pop() + "");
                }
                stack.add(s[i]);
                i++;
            }
        }

        // 如果栈中还有操作符,那么全部入队
        while (!stack.isEmpty()) {
            queue.add(stack.pop() + "");
        }

        return queue;
    }

    // 后缀表达式进行计算
    public static int SuffixCalculate(ArrayDeque<String> queue) {
        // 使用一个辅助栈来存储数字
        Stack<Integer> res = new Stack<>();

        while (!queue.isEmpty()){
            String s = queue.pop();
            // 如果队列为操作符,那么从运算栈中取2个运算符进行计算,并将计算结果重新入栈
            if (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/")) {
                int a = res.pop();
                int b = res.pop();
                int result = cal(b, a, s);
                res.push(result);
            } else {
                res.add(Integer.parseInt(s));
            }
        }

        return res.pop();
    }

    private static int compare(char peek, char c) {
        if (peek == '(' || c == '(') return 1;
        if (c == '+' || c == '-') return -1;
        if (c == '*' && (peek == '*' || peek == '/')) return -1;
        if (c == '/' && (peek == '*' || peek == '/')) return -1;
        return 1;
    }

    private static int cal(int a, int b, String s) {
        //计算
        if (s.equals("+")) {
            return a + b;
        } else if (s.equals("-")) {
            return a - b;
        } else if (s.equals("*")) {
            return a * b;
        } else {
            return a / b;
        }
    }

}
