package com.kiger.fileDecompression;

import java.io.IOException;

/**
 * @ClassName RunTest
 * @Description TODO
 * @Author zk_kiger
 * @Date 2019/11/7 21:20
 * @Version 1.0
 */

public class RunTest {

    public static void main(String[] args) throws IOException {

        String sourcePath = "D:\\javaProject\\DataStructureAndAlgorithms\\Tree\\src\\com\\kiger\\fileDecompression\\test.txt";
        String compressPath = "D:\\javaProject\\DataStructureAndAlgorithms\\Tree\\src\\com\\kiger\\fileDecompression\\test2.huffmanZip";
        String decompressPath = "D:\\javaProject\\DataStructureAndAlgorithms\\Tree\\src\\com\\kiger\\fileDecompression\\test3.txt";

        Compress compress = new Compress();
        compress.compress(sourcePath, compressPath);
        Decompress decompress = new Decompress();
        decompress.deCompress(compressPath, decompressPath);

    }
}
