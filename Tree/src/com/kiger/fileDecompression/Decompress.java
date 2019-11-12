package com.kiger.fileDecompression;

import java.io.*;
import java.util.*;

/**
 * @ClassName Decompress
 * @Description 解压类
 * @Author zk_kiger
 * @Date 2019/11/7 22:14
 * @Version 1.0
 */

public class Decompress {
    static final int CHAR_INDEX = 256;
    static final int BUFFER_SIZE = 128;
    // 每个字符对应哈夫曼编码的长度
    private int[] codelengths = new int[CHAR_INDEX];
    // 每个Huffman编码对应的字符
    private Map<String, Integer> huffmanMap = new HashMap<>();
    // 优先队列用于创建huffman树,自动从小到大排序结点
    private PriorityQueue<Node> queue = new PriorityQueue<>(new Comparator<Node>() {
        @Override
        public int compare(Node o1, Node o2) {
            return o1.getWeight() - o2.getWeight();
        }
    });

    public Decompress() {}

    public void deCompress(String fromPath, String toPath) {
        deCompress_(fromPath, toPath);
    }

    /**
     * 解压文件
     */
    private void deCompress_(String fromPath, String toPath) {

        // 1.读取文件里面的码表并还原码表
        // 2.根据权值重新构建Huffman树
        // 3.根据创建Huffman树遍历将字符写入文件
        System.out.println("开始解压缩文件...");
        decompressFile(fromPath, toPath);
        System.out.println("解压缩文件完成...");

    }


    // 读取文件内容,转为哈夫曼编码并解码写入文件
    private void decompressFile(String fromPath, String toPath) {
        // 前面256个字节存储的是每个字符的权值,从第257个字节读取
        try (
                BufferedInputStream bis = new BufferedInputStream(new FileInputStream(fromPath));
                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(toPath))
                ){
            // 1.读取文件里面的码表并还原码表
            readHuffmanCode(bis);

            // 2.读取剩下的文件内容
            byte[] bytes = new byte[BUFFER_SIZE];
            int len;
            int lastIndex = -1;
            double length = 0;
            double fileTotalSize = (double)bis.available();
            String codeString = "";
            while ((len = bis.read(bytes)) != -1) {
                length += len;
                double jd = (length/fileTotalSize)*100;
                System.out.printf("解压进度：%.2f%%\n",jd);
                StringBuilder sb = new StringBuilder();
                if (bis.available() == 0) {
                    lastIndex = len-1;
                    len -= 2;
                }
                for (int i = 0; i < len; i++) {
                    // 将1字节8位字符串
                    sb.append(tranIntToBin(bytes[i]));
                }
                // 为最后一个字节，需要去掉后面添加的0
                if (lastIndex != -1) {
                    byte value = bytes[lastIndex-1];
                    int lastLen = bytes[lastIndex]&0xff;
//                    System.out.println(lastLen);
                    String s = tranIntToBin(value);
                    sb.append(s, 0, s.length()-lastLen);
                }
//                System.out.println(sb.toString());
                // 根据Huffman编码找到对应的字符
                codeString += sb.toString();
                for (int i = 0; i < codeString.length(); i++) {
                    String s = codeString.substring(0, i+1);
                    if (huffmanMap.containsKey(s)) {
                        writeInBuffer(bos, huffmanMap.get(s));
//                        System.out.print(huffmanMap.get(s) + " ");
                        codeString = codeString.substring(i+1);
                        i = -1;
                    }
                }
            }
            byte[] data = Arrays.copyOfRange(writeBuffer, 0, writeBufferSize);
            bos.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // 读取文件码表 - 得到每个字符对应的编码
    private void readHuffmanCode(BufferedInputStream bis) {
        try {
            int temp;
            int codeTotalLength = 0;
            // 记录每个字符对应的编码长度
            for (int i = 0; i < codelengths.length; i++) {
                temp = bis.read();
                codelengths[i] = temp;
                codeTotalLength += codelengths[i];
            }
            // 得到编码总长度可以获取前多少字节存放编码,用来截取每一个字符对应的编码
            int length = codeTotalLength / 8;
            if ((codeTotalLength%8) != 0)
                length++;
            byte[] bytes = new byte[length];
            int len;
            while ((len = bis.read(bytes)) != -1) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < bytes.length; i++) {
                    // 将字节转为二进制
                    sb.append(tranIntToBin(bytes[i]));
                }
                String code = sb.toString();
                // 读取Huffman编码并存入map中
                for (int i = 0; i < codelengths.length; i++) {
                    if (codelengths[i] != 0) {
                        String s = code.substring(0, codelengths[i]);
//                        System.out.println(i + " : " + codelengths[i] + " : " + s);
                        huffmanMap.put(s, i);
                        code = code.substring(codelengths[i]);
                    }
                }
                break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // 写入缓存,达到要求再写入文件
    int writeBufferSize = 0;
    byte[] writeBuffer = new byte[BUFFER_SIZE];
    private void writeInBuffer(BufferedOutputStream bos, int value) throws IOException {
        if (writeBufferSize < BUFFER_SIZE) {
            writeBuffer[writeBufferSize] = (byte)value;
            if (++writeBufferSize >= BUFFER_SIZE) {
                bos.write(writeBuffer);
                writeBufferSize = 0;
            }
        }
    }

    // 将整数转为8位二进制
    private static String tranIntToBin(byte value) {
        // 该操作非常重要  字节&0xff  强转为int类型
        int num = value&0xff;
//        System.out.println(num + " ");
        String s = "";
        for (int i = 0; i < 8; i++) {
            s = num%2 + s;
            num = num / 2;
        }
        return s;

    }

}
