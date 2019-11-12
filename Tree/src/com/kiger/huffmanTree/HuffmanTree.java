package com.kiger.huffmanTree;

import java.util.*;

public class HuffmanTree {

    private List<Node> nodes;
    private Node root;
    private Map<String, String> huffmanCodes;

    // 实现排序接口，添加结点自动排序，从小到大排序
    private static class Node implements Comparable<Node>{
        private String data;
        private int weight;
        private Node left;
        private Node right;

        public Node(String data, int weight) {
            this.data = data;
            this.weight = weight;
        }

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        @Override
        public int compareTo(Node o) {
            return this.weight - o.weight;
        }
    }

    public HuffmanTree() {}

    /**
     * 译码
     * @param str 二进制字符
     * @return
     */
    public String DeCodeStr(String str) {
        return DeCodeStr(str, root);
    }
    private String DeCodeStr(String str, Node root) {
        StringBuilder sb = new StringBuilder();
        char[] chars = str.toCharArray();
        Node cur = root;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '0') {
                cur = cur.left;
                if (cur.left == null && cur.right == null) {
                    sb.append(cur.data);
                    cur = root;
                }
            } else {
                cur = cur.right;
                if (cur.left == null && cur.right == null) {
                    sb.append(cur.data);
                    cur = root;
                }
            }
        }
        return sb.toString();
    }

    /**
     * 编码
     * @param str 带编码字符串
     */
    public String codeStr(String str) {
        bulidHaffmanTree(str);
        return codeStr(str, huffmanCodes);
    }
    private String codeStr(String str, Map<String, String> huffmanCodes) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            sb.append(huffmanCodes.get(String.valueOf(str.charAt(i))));
        }
        return sb.toString();
    }

    // 创建Huffman树
    private void bulidHaffmanTree(String str) {
        nodes = new ArrayList<>();
        huffmanCodes = new HashMap<>();

        Map<String, Integer> tempMap = new HashMap<>();
        for (int i = 0; i < str.length(); i++) {
            String c = String.valueOf(str.charAt(i));
            // 如果存在这个结点，那么出现的次数++
            if (tempMap.containsKey(c)) {
                int count = tempMap.get(c);
                tempMap.put(c, ++count);
            } else {
                tempMap.put(c, 1);
            }
        }
        // 将统计好的字符与权值存入nodes中
        for (Map.Entry<String, Integer> k:
                tempMap.entrySet()) {
            nodes.add(new Node(k.getKey(), k.getValue()));
        }

        root = createHaffmanTree(nodes);

        // 生成每个结点的哈夫曼编码
        StringBuilder sb = new StringBuilder();
        getCodes(root, null, sb);

    }
    private Node createHaffmanTree(List<Node> nodes) {
        while (nodes.size() > 1) {
            // 排序，每次取最小的两个结点
            Collections.sort(nodes);

            // 获得权值最小的两个结点，左结点大于右结点
            Node leftChild = nodes.get(1);
            Node rightChild = nodes.get(0);

            // 生成新结点
            Node newNode = new Node(null,leftChild.weight + rightChild.weight);
            newNode.left = leftChild;
            newNode.right = rightChild;

            // 删除权值最小的两个结点
            nodes.remove(0);
            nodes.remove(0);

            // 将新结点加入到集合中
            nodes.add(newNode);
        }
        return nodes.get(0);
    }

    /**
     * 将传入结点的所有叶子结点的哈夫曼编码得到,存储到huffmanCodes中
     * @param root 结点
     * @param code 路径：左结点 - 0，右结点 - 1
     * @param sb 用于拼接路径
     */
    private void getCodes(Node root, String code, StringBuilder sb) {
        StringBuilder newSb = new StringBuilder(sb);
        if (code != null)
            newSb.append(code);
        if (root != null) {
            // 判断当前结点
            if (root.data == null) {
                // 非叶子结点,递归左右结点,记录路径
                getCodes(root.left, "0", newSb);
                getCodes(root.right, "1", newSb);
            } else {
                // 叶子结点，存入Map中
                huffmanCodes.put(root.data, newSb.toString());
            }
        }
    }

    // 打印每个字符对应的哈夫曼编码
    public void PrintHuffmanCode() {
        PrintHuffmanCode(huffmanCodes);
    }
    private void PrintHuffmanCode(Map<String, String> huffmanCodes) {
        for (Map.Entry<String, String> code :
                huffmanCodes.entrySet()) {
            System.out.println(code.getKey() + ":" + code.getValue());
        }
    }

    // 树状打印
    public void PrintTree() {
        PrintTree(root, getDepth());
    }
    private void PrintTree(Node root, int h) {
        if (root == null)
            return;
        PrintTree(root.left, h + 1);
        for (int i = 0; i < h; i++)
            System.out.print(" ");
        System.out.println(root.weight);
        PrintTree(root.right, h + 1);
    }

    // 层序遍历
    public void levelOrder() {
        levelOrder(root);
    }
    private void levelOrder(Node root) {
        ArrayDeque<Node> queue = new ArrayDeque<>();
        Node curr = root;

        if (curr == null)
            return;
        queue.add(curr);

        while (!queue.isEmpty()) {
            curr = queue.remove();
            System.out.print(curr.weight + " ");
            if (curr.left != null)
                queue.add(curr.left);
            if (curr.right != null)
                queue.add(curr.right);
        }
    }

    public int getDepth() {
        return getDepth(root);
    }
    private int getDepth(Node root) {
        return root == null ? 0 : (1 + Math.max(getDepth(root.left), getDepth(root.right)));
    }

    // 快速排序
    private void quickSort(List<Node> nodes, int left, int right) {
        if (left >= right)
            return;
        Node key = nodes.get(left);
        int i = left;
        int j = right;
        while (i != j) {
             while (nodes.get(j).weight >= key.weight && i < j)
                 j--;
             while (nodes.get(j).weight <= key.weight && i < j)
                 i++;
             if (i < j) {
                 exchange(nodes.get(i), nodes.get(j));
             }
        }
        nodes.set(left, nodes.get(i));
        nodes.set(i, key);

        quickSort(nodes, left, i-1);
        quickSort(nodes, i+1, right);
    }

    // 节点交换
    private void exchange(Node n1, Node n2) {
        Node temp = n1;
        n2 = n1;
        n1 = temp;
    }
}
