package com.kiger.Demo;

import java.util.Scanner;

/**
 * @ClassName HorseStepBoard
 * @Description 马踏棋盘
 * @Author zk_kiger
 * @Date 2019/10/8 10:22
 * @Version 1.0
 */

public class HorseStepBoard {
    // 棋盘大小坐标
    private static Coordinate boardCoord;
    // 骑士起始坐标
    private static Coordinate startCoord;
    // 棋盘 - 使用二维数组
    private static int[][] chessboard;

    // 定义坐标类
    private class Coordinate {
        private int x,y;
        public Coordinate(){}
        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public HorseStepBoard() {
        Scanner input = new Scanner(System.in);

        /** 初始化棋盘信息 */
        System.out.println("请输入棋盘行数、列数: ");
        int board_x, board_y;
        do {
            board_x = input.nextInt();
            board_y = input.nextInt();
        } while (board_x <= 0 || board_y <= 0);
        boardCoord = new Coordinate(board_x, board_y);
        chessboard = new int[board_x+1][board_y+1];

        System.out.println("请输入骑士起始位置(start_x、start_y): ");
        int start_x, start_y;
        do {
            start_x = input.nextInt();
            start_y = input.nextInt();
        } while (start_x <= 0 || start_y <= 0 || start_x > board_x || start_y > board_y);
        startCoord = new Coordinate(start_x, start_y);
    }

    public static void main(String[] args) {
        HorseStepBoard horseStepBoard = new HorseStepBoard();

        long start = System.currentTimeMillis();
        // 调用马踏棋盘方法
        horseStepBoard.travelChessBoard();
        long end = System.currentTimeMillis();

        System.out.println("全过程耗时: " + (end-start) + " ms");
        System.out.println("棋盘信息如下: ");
        horseStepBoard.printChessBoard();
    }

    public boolean travelChessBoard() {
        // 创建骑士移动数组
        int[][] move = {
            {-2,1},{-1,2},{1,2},{2,1},{2,-1},{1,-2},{-1,-2},{-2,-1}
        };
        // 存储该点能够到达的下一个点
        Coordinate[] next;
        // 存储该点能够到达的下一个点的权值(出口数量)
        int[] ways;
        // 骑士当前位置、下一个尝试位置
        Coordinate currPos, temp;

        // 骑士当前位置为分配的起始位置
        currPos = startCoord;
        // 在棋盘中标记骑士位置
        chessboard[startCoord.x][startCoord.y] = 1;

        int wayCount,wayMinWeight,wayMinWeightIndex;

        // 循环 board_x * board_y 个位置
        int totalPos = boardCoord.x * boardCoord.y;
        for (int i = 2; i <= totalPos; i++) {
            // 初始化该位置的next、ways
            ways = new int[8];
            next = new Coordinate[8];
            wayCount = 0;
            // 试探8个方向
            for (int j = 0; j < 8; j++) {
                temp = new Coordinate();
                temp.x = currPos.x + move[j][0];
                temp.y = currPos.y + move[j][1];
                // 检查是否合法
                if (check(temp)) {
                    // 存储到next
                    next[wayCount] = temp;
                    // 出口数量增加
                    wayCount++;
                }
            }

            // 根据该点的出口数量进行贪心算法 - 获得出口中权值最小的,并以该点开始重复寻找下一个点
            if (wayCount == 0) {
                return false;
            } else if (wayCount == 1) {
                wayMinWeightIndex = 0;
            } else {
                for (int j = 0; j < wayCount; j++) {
                    // 试探8个方向
                    for (int k = 0; k < 8; k++) {
                        temp = new Coordinate();
                        temp.x = next[j].x + move[k][0];
                        temp.y = next[j].y + move[k][1];
                        // 检查是否合法
                        if (check(temp)) {
                            // 记录每个出口的权值
                            ways[j]++;
                        }
                    }
                }
                wayMinWeight = ways[0];
                wayMinWeightIndex = 0;
                // 找出出口中权值最小的
                for (int j = 1; j < wayCount; j++) {
                    if (ways[j] < wayMinWeight) {
                        wayMinWeight = ways[j];
                        // 记录该点的下标
                        wayMinWeightIndex = j;
                    }
                }
            }
            currPos = next[wayMinWeightIndex];
            chessboard[currPos.x][currPos.y] = i;
        }
        return true;
    }

    // 打印棋盘信息
    public void printChessBoard() {
        for (int i = 1; i <= boardCoord.x; i++) {
            for (int j = 1; j <= boardCoord.y; j++) {
                System.out.print(chessboard[i][j] + "\t");
            }
            System.out.println("");
        }
    }

    // 判断该点是否合法
    private boolean check(Coordinate coord) {
        if (coord.x <= 0 || coord.y <= 0 || coord.x > boardCoord.x || coord.y > boardCoord.y) {
            return false;
        }
        if (chessboard[coord.x][coord.y] != 0) {
            return false;
        }
        return true;
    }

}
