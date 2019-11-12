package com.kiger.Sequence;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * @ClassName SparseMatrix
 * @Description 系数矩阵计算器
 * @Author zk_kiger
 * @Date 2019/10/17 10:57
 * @Version 1.0
 */

public class SparseMatrix {

    public SparseMatrix() {}

    public static void main(String[] args) {
        SparseMatrix sparseMatrix = new SparseMatrix();
        int[][] a = {{1,0,0},{0,1,0}};
        int[][] b = {{1,1,0},{1,1,0}};
        Triad triad1 = new Triad(a);
        Triad triad2 = new Triad(b);
        Triad triad = sparseMatrix.add(triad1, triad2);
        System.out.println(triad);
    }

    /**
     * 三元组相加
     */
    public Triad add(Triad triad1, Triad triad2) {
        int[][] t1 = triad1.getTriad();
        int[][] t2 = triad2.getTriad();
        ArrayList<int[]> list = new ArrayList<>();
        for (int i = 0; i < t1.length; i++) list.add(t1[i]);
        for (int i = 0; i < t2.length; i++) list.add(t2[i]);
         Collections.sort(list, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                System.out.println(o1);
                System.out.println(o2);
                if (o1 == null || o2 == null) {
                    return 0;
                }
                if (o1[0] == o2[0] && o1[1] == o2[1]) {
                    o1[2] += o2[2];
                    list.remove(o2);
                    return 0;
                }
                return o1[0] - o2[0];
            }
        });

        list.forEach(a -> System.out.print(a));

        Triad triad = new Triad((int[][]) list.toArray());

        return triad;
    }

}
