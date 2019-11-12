package com.kiger.fileDecompression;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @ClassName Compress
 * @Description 压缩文件类
 * @Author zk_kiger
 * @Date 2019/11/7 18:55
 * @Version 1.0
 */

public class Compress {
    static final int CHAR_INDEX = 256;
    static final int BUFFER_SIZE = 128;
    // 用来记录文件中字符出现的次数,下标对应字符的ASCII码
    private int[] times = new int[CHAR_INDEX];
    // 用来记录每个字符对应的huffman编码
    private String[] huffmanCodes = new String[CHAR_INDEX];
    // 优先队列用于创建huffman树,自动从小到大排序结点
    private PriorityQueue<Node> queue = new PriorityQueue<>(new Comparator<Node>() {
        @Override
        public int compare(Node o1, Node o2) {
            return o1.getWeight() - o2.getWeight();
        }
    });

    public Compress() {
        for (int i = 0; i < huffmanCodes.length; i++) {
            huffmanCodes[i] = "";
        }
    }

    /**
     * 压缩文件
     * @param fromPath 被压缩文件路径
     * @param toPath   已压缩文件路径
     */
    public void compress(String fromPath, String toPath) {
        compress_(fromPath, toPath);
    }
    private void compress_(String fromPath, String toPath) {

        // 1.读取文件并统计字符权值
        statCharWeight(fromPath);

        // 2.根据权值创建Huffman树
        Node root = createHuffman();

        // 3.根据前序遍历获得编码表
        getHuffmanCode(root, "");

        System.out.println("正在压缩文件...");
        // 4.根据编码表压缩文件
        compressFile(fromPath, toPath);
        System.out.println("文件压缩完成...");

    }

    // 根据编码表压缩文件
    byte value = 0;
    int index = 0;
    int writeBufferSize = 0;
    byte[] writeBuffer = new byte[BUFFER_SIZE];
    int lastIndex = 0;   // 最后一个字节补0的个数
    private void compressFile(String fromPath, String toPath) {
        File toFile = new File(toPath);
        try (
             BufferedInputStream bis = new BufferedInputStream(new FileInputStream(fromPath));
             BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(toFile))
                ) {

            // 将每个编码的长度写入文件
            StringBuilder code = new StringBuilder();
            for (int i = 0; i < CHAR_INDEX; i++) {
                bos.write(huffmanCodes[i].length());
//                if (huffmanCodes[i].length() != 0)
//                    System.out.println(i + " : " + huffmanCodes[i]);
                code.append(huffmanCodes[i]);
            }
            // 再将哈夫曼编码写入文件
            char[] charArray = code.toString().toCharArray();
            for (int i = 0; i < charArray.length; i++) {
                if (charArray[i] == '0')
                    value = CLR_BYTE(value, index);
                if (charArray[i] == '1')
                    value = SET_BYTE(value, index);
                index++;
                if (index >= 8) {
                    index = 0;
                    writeInBuffer(bos, value);
                }
            }
            if (index != 0) {
                writeInBuffer(bos, value);
            }

            // 写文件内容
            index = 0;
            value = 0;
            byte[] bytes = new byte[BUFFER_SIZE];
            int len;
            double length = 0;
            double fileTotalSize = (double)bis.available();
            while ((len = bis.read(bytes)) != -1) {
                length += len;
                double jd = (length/fileTotalSize)*100;
                System.out.printf("压缩进度：%.2f%%\n",jd);
                // 用于拼接字符编码
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < len; i++) {
                    int temp = bytes[i];
                    if (temp < 0) {
                        sb.append(huffmanCodes[CHAR_INDEX + temp]);
//                        System.out.print((CHAR_INDEX + temp) + " ");
                    } else {
                        sb.append(huffmanCodes[temp]);
//                        System.out.print(temp + " ");
                    }
                }
//                System.out.print(sb.toString());
                // 将拼接好的01字符,每8位转为一个字节存到缓存区
                char[] chars = sb.toString().toCharArray();
                for (int i = 0; i < chars.length; i++) {
                    if (chars[i] == '0')
                        value = CLR_BYTE(value, index);
                    if (chars[i] == '1')
                        value = SET_BYTE(value, index);
                    index++;
                    if (index >= 8) {
                        writeInBuffer(bos, value);
                        index = 0;
                    }
                }
            }
            if (index != 0) {
                lastIndex = 8 - index;
                writeInBuffer(bos, value);
                writeInBuffer(bos, (byte) lastIndex);
//                System.out.println(lastIndex);
            } else {
                writeInBuffer(bos, (byte) lastIndex);
            }
            // 将缓存中的字节写入到文件中
            byte[] data = Arrays.copyOfRange(writeBuffer, 0, writeBufferSize);
            bos.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 前序遍历获得哈夫曼编码表
    private void getHuffmanCode(Node root, String code) {
        if (root.getLeftChild() != null)
            getHuffmanCode(root.getLeftChild(), code + "0");
        if (root.getRightChild() != null)
            getHuffmanCode(root.getRightChild(), code + "1");
        if (root.getLeftChild() == null && root.getRightChild() == null) {
//            System.out.println(root.getIndex() + " 的编码为：" + code);
            huffmanCodes[root.getIndex()] = code;
        }
    }

    // 创建Huffman树
    private Node createHuffman() {

        // 将字符结点存入到优先队列中
        for (int i = 0; i < times.length; i++) {
            if (times[i] != 0){
//                System.out.println("i = " + i + " : " + "value = " + times[i]);
                queue.add(new Node(i, times[i]));
            }

        }

        // 根据优先队列构建哈夫曼树
        while (queue.size() > 1) {
            // 权值最小
            Node rightChild = queue.remove();
            // 权值仅次于rightChild
            Node leftChild = queue.remove();
            Node newNode = new Node(-1, rightChild.getWeight() + leftChild.getWeight());
            newNode.setLeftChild(leftChild);
            newNode.setRightChild(rightChild);
            queue.add(newNode);
        }

        // 返回根结点
        return queue.peek();
    }

    // 计算字符权值
    private void statCharWeight(String fromPath) {

        try (
                BufferedInputStream bis = new BufferedInputStream(new FileInputStream(fromPath))
                ) {
            byte[] bytes = new byte[BUFFER_SIZE];
            int len;
            while ((len = bis.read(bytes)) != -1) {
                // 用缓存中的字节统计出现权值
                for (int i = 0; i < len; i++) {
                    int temp = bytes[i];
                    if (temp < 0)
                        times[CHAR_INDEX + temp]++;
                    else
                        times[temp]++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //指定位，置1
    private byte SET_BYTE(byte value, int index){
        return (value) |= (1 << ((index) ^ 7));
    }
    //指定位，置0
    private byte CLR_BYTE(byte value, int index){
        return (value) &= (~(1 << ((index) ^ 7)));
    }

    // 写入缓存,达到要求再写入文件
    private void writeInBuffer(BufferedOutputStream bos, byte value) throws IOException {
        if (writeBufferSize < BUFFER_SIZE) {
//            System.out.print(value + " ");
//            System.out.println(Integer.toBinaryString((byte)value) + " ");
            writeBuffer[writeBufferSize] = value;
            if (++writeBufferSize >= BUFFER_SIZE) {
                bos.write(writeBuffer);
                writeBufferSize = 0;
            }
        }
    }

}
