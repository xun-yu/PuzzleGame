package com.game.ui.test;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Test3 implements ActionListener {
    static JButton jtb1 = new JButton();
    static JButton jtb2 = new JButton();
    public Test3() {
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


        jtb1.setText("hello");
        jtb2.setText("world");
        jtb1.setBounds(10, 10, 100, 30);
        jtb2.setBounds(10, 50, 100, 30);
        jtb1.addActionListener(this);
        jtb2.addActionListener(this);
        frame.add(jtb1);
        frame.add(jtb2);

        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        if (button == jtb1) {
            System.out.println("hello");
        }else if (button == jtb2) {
            Random r = new Random();
            System.out.println("world");
            jtb2.setLocation(r.nextInt(100), r.nextInt(100));
        }
    }
}
