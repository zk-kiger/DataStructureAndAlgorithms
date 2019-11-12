package com.kiger.Sequence;

import java.util.Arrays;

/**
 * @ClassName Triad
 * @Description 三元组
 * @Author zk_kiger
 * @Date 2019/10/17 10:59
 * @Version 1.0
 */

public class Triad {

    private int MaxRow;
    private int MaxCol;
    private int[][] triad;

    public Triad(int[][] array) {
        MaxRow = array.length;
        MaxCol = array[0].length;
        System.out.println(MaxRow);
        System.out.println(MaxCol);
        triad = transformToTriad(array);
    }

    /**
     * 将二维数组转换为三元组
     */
    public int[][] transformToTriad(int[][] array) {
        // 遍历array记录非零元素的个数,用于实例化三元组
        int num = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j] > 0) num++;
            }
        }

        triad = new int[num][3];
        // 将二维数组转换为三元组
        int index = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j] > 0) {
                    triad[index][0] = i;
                    triad[index][1] = j;
                    triad[index][2] = array[i][j];
                    index++;
                }
            }
        }

        return triad;
    }

    /**
     * 将三元组又转换为二维数组
     */
    public int[][] transformToArray(Triad triad) {
        int[][] array = new int[triad.getMaxRow()][triad.getMaxCol()];
        int[][] tr = triad.getTriad();
        int row, col, val;
        for (int i = 0; i < tr.length; i++) {
            row = tr[i][0];
            col = tr[i][1];
            val = tr[i][2];
            array[row][col] = val;
        }
        return array;
    }

    public int getMaxRow() {
        return MaxRow;
    }

    public void setMaxRow(int maxRow) {
        MaxRow = maxRow;
    }

    public int getMaxCol() {
        return MaxCol;
    }

    public void setMaxCol(int maxCol) {
        MaxCol = maxCol;
    }

    public int[][] getTriad() {
        return triad;
    }

    public void setTriad(int[][] triad) {
        this.triad = triad;
    }

    @Override
    public String toString() {
        return "Triad{" +
                "MaxRow=" + MaxRow +
                ", MaxCol=" + MaxCol +
                ", triad=" + Arrays.toString(triad) +
                '}';
    }
}
