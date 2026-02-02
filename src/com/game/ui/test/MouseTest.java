package com.game.ui.test;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseTest extends JFrame implements MouseListener {
    JButton button = new JButton();
    public MouseTest() {
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

        button.setBounds(0, 0, 80, 60);
        button.addMouseListener(this);
        frame.add(button);

        frame.setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("单击");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("按下不松");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println("松开");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        System.out.println("划入");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        System.out.println("划出");
    }
}
