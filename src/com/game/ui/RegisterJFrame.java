package com.game.ui;

import javax.json.*;
import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;


public class RegisterJFrame extends JFrame implements MouseListener, ActionListener {
    // 加载用户数据
    UserControl userControl = new UserControl();
    // 输入框
    JTextField username = new JTextField();
    JPasswordField password = new JPasswordField();
    JPasswordField rePassword = new JPasswordField();
    // 按钮
    JButton register = new JButton();
    JButton reset = new JButton();
    // 密码输入框可视化
    JButton pwdVisibleButton = new JButton();
    JButton pwdVisible2Button = new JButton();
    // 密码可视化状态
    boolean pwdVisible = false;
    boolean pwdVisible2 = false;


    public RegisterJFrame() throws IOException {
        // 界面初始化
        initJFrame();
        // 图片初始化
        initImage();

        this.setVisible(true);

    }

    // 界面图片初始化
    private void initImage() {
        // 添加文字图片
        JLabel nameLabel = new JLabel(new ImageIcon("image/register/注册用户名.png"));
        nameLabel.setBounds(80,150,79,17);
        this.getContentPane().add(nameLabel);
        JLabel pwdLabel = new JLabel(new ImageIcon("image/register/注册密码.png"));
        pwdLabel.setBounds(80,210,64,17);
        this.getContentPane().add(pwdLabel);
        JLabel rePwdLabel = new JLabel(new ImageIcon("image/register/再次输入密码.png"));
        rePwdLabel.setBounds(75,270,96,17);
        this.getContentPane().add(rePwdLabel);

        // 设置输入框
        username.setBounds(190,145,200,30);
        this.getContentPane().add(username);
        password.setBounds(190,205,200,30);
        password.setEchoChar('*');
        this.getContentPane().add(password);
        rePassword.setBounds(190,265,200,30);
        rePassword.setEchoChar('*');
        this.getContentPane().add(rePassword);

        // 添加密码可视化
        pwdVisibleButton.setIcon(new ImageIcon("image/register/眼睛_隐藏.png"));
        pwdVisible2Button.setIcon(new ImageIcon("image/register/眼睛_隐藏.png"));
        pwdVisibleButton.setBounds(400,203,35,35);
        pwdVisible2Button.setBounds(400,263,35,35);
        initButton(pwdVisibleButton);
        initButton(pwdVisible2Button);
        // 绑定监听事件
        pwdVisibleButton.addMouseListener(this);
        pwdVisible2Button.addMouseListener(this);

        this.getContentPane().add(pwdVisibleButton);
        this.getContentPane().add(pwdVisible2Button);

        // 按钮设置
        register.setIcon(new ImageIcon("image/register/注册按钮.png"));
        register.setBounds(80, 320, 128, 47);
        initButton(register);
        this.getContentPane().add(register);
        reset.setIcon(new ImageIcon("image/register/重置按钮.png"));
        reset.setBounds(280, 320, 128, 47);
        initButton(reset);
        this.getContentPane().add(reset);
        // 绑定监听事件
        register.addMouseListener(this);
        reset.addMouseListener(this);

        // 添加背景图片
        JLabel backgroundLabel = new JLabel(new ImageIcon("image/register/background.png"));
        backgroundLabel.setBounds(0, 0, this.getWidth(), this.getHeight());
        this.getContentPane().add(backgroundLabel);
    }

    // 按钮样式设置
    private void initButton(JButton button) {
        // 设置按钮内容区域透明
        button.setContentAreaFilled(false);
        // 不绘制边框
        button.setBorderPainted(false);
        // 设置组件透明
        button.setOpaque(false);
        // 不绘制焦点边框
        button.setFocusPainted(false);
    }

    private void initJFrame() {
        // 设置界面尺寸
        this.setSize(488, 430);
        // 设置界面标题
        this.setTitle("注册-拼图游戏单机版v1.0");
        // 设置界面居中
        this.setLocationRelativeTo(null);
        // 设置界面置顶
        this.setAlwaysOnTop(true);
        // 设置界面关闭模式
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Object obj = e.getSource();
        if (obj == register) {
            System.out.println("点击注册");
            String name = username.getText();
            String pwd = new String(password.getPassword());
            String rePwd = new String(rePassword.getPassword());
            if (!pwd.equals(rePwd)){
                new ErrorTip("两次输入的密码不一致");
                return;
            }
            try {
                if (userControl.checkUser(name)){
                    new ErrorTip("该用户名已存在");
                    return;
                }
                if (userControl.addUser(name, pwd)){
                    this.dispose();
                    new LoginJFrame();
                }else {
                    new ErrorTip("注册失败");
                    return;
                }
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }else if (obj == reset) {
            System.out.println("点击重置");
            username.setText("");
            password.setText("");
            rePassword.setText("");
            this.getContentPane().repaint();
        }else if (obj == pwdVisibleButton) {
            if (pwdVisible) {
                pwdVisible = false;
                password.setEchoChar('*');
                pwdVisibleButton.setIcon(new ImageIcon("image/register/眼睛_隐藏.png"));
            }else {
                pwdVisible = true;
                password.setEchoChar((char) 0);
                pwdVisibleButton.setIcon(new ImageIcon("image/register/眼睛_显示.png"));
            }
        }else if (obj == pwdVisible2Button) {
            if (pwdVisible2) {
                pwdVisible2 = false;
                rePassword.setEchoChar('*');
                pwdVisible2Button.setIcon(new ImageIcon("image/register/眼睛_隐藏.png"));
            }else {
                pwdVisible2 = true;
                rePassword.setEchoChar((char) 0);
                pwdVisible2Button.setIcon(new ImageIcon("image/register/眼睛_显示.png"));
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Object obj = e.getSource();
        if (obj == register) {
            register.setIcon(new ImageIcon("image/register/注册按下.png"));
        }else if (obj == reset) {
            reset.setIcon(new ImageIcon("image/register/重置按下.png"));
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Object obj = e.getSource();
        if (obj == register) {
            register.setIcon(new ImageIcon("image/register/注册按钮.png"));
        }else if (obj == reset) {
            reset.setIcon(new ImageIcon("image/register/重置按钮.png"));
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
