package com.game.ui.test;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class test2 {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        // 设置界面尺寸
        frame.setSize(800, 600);
        // 设置界面标题
        frame.setTitle("拼图游戏单机版v1.0");
        // 设置界面居中
        frame.setLocationRelativeTo(null);
        // 设置界面置顶
        frame.setAlwaysOnTop(true);
        // 设置界面关闭模式
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 取消居中放置
        frame.setLayout(null);

        JButton jtb = new JButton();
        jtb.setText("hi");
        jtb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("hi");
            }
        });
        jtb.setBounds(0, 0, 80, 50);
        frame.add(jtb);

        frame.setVisible(true);
    }
}
