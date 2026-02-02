package com.game.ui.test;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyTest extends JFrame implements KeyListener {
    JButton button = new JButton();
    public KeyTest() {
        // 设置界面尺寸
        this.setSize(800, 600);
        // 设置界面标题
        this.setTitle("拼图游戏单机版v1.0");
        // 设置界面居中
        this.setLocationRelativeTo(null);
        // 设置界面置顶
        this.setAlwaysOnTop(true);
        // 设置界面关闭模式
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 取消居中放置
        this.setLayout(null);

this.addKeyListener(this);

        this.setVisible(true);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println("点击");
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("按下");
    }

    @Override
    public void keyReleased(KeyEvent e) {
System.out.println("松开");
    }
}
