package com.game.ui.test;

import java.util.Random;

public class test {
    public static void main(String[] args) {
        // 初始化数据组
        int[] data = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        // 打乱顺序
        Random r = new Random();
        for (int i = 0; i < data.length; i++) {
            int f = r.nextInt(data.length);
            int temp = data[i];
            data[i] = data[f];
            data[f] = temp;
        }
        // 遍历数组
        for (int datum : data) {
            System.out.print(datum + " ");
        }

        // 将数据填入二维数组
        int[][] data2 = new int[4][4];
        for (int i = 0; i < data.length; i++) {
            data2[i / 4][i % 4] = data[i];
        }

        // 遍历二维数组
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(data2[i][j] + " ");
            }
            System.out.println();
        }
    }
}
