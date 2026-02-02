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

public class LoginJFrame extends JFrame implements MouseListener, ActionListener {
    // 加载用户信息
    UserControl userControl = new UserControl();
    // 输入存储
    String captcha = "";
    // 输入框
    // 用户名
    JTextField usernameTextField = new JTextField();
    // 密码
    JPasswordField pwdTextField = new JPasswordField();
    // 验证码输入
    JTextField codeTextField = new JTextField();
    // 验证码文字
    JButton captchaJLButton = new JButton();
    // 登录按钮
    JButton loginButton = new JButton();
    // 注册按钮
    JButton registerButton = new JButton();
    // 密码输入框可视化
    JButton pwdVisibleButton = new JButton();
    // 密码可视化状态
    boolean pwdVisible = false;

    public LoginJFrame() throws IOException {
        // 初始化界面
        initJFrame();

        // 初始化图片
        initImage();

        this.setVisible(true);

    }

    // 初始化图片
    private void initImage() {
        // 添加文字图片
        JLabel usernameLabel = new JLabel(new ImageIcon("image/login/用户名.png"));
        JLabel pwdLabel = new JLabel(new ImageIcon("image/login/密码.png"));
        JLabel codeLabel = new JLabel(new ImageIcon("image/login/验证码.png"));
        usernameLabel.setBounds(100,135, 47,17);
        pwdLabel.setBounds(100,185, 47,17);
        codeLabel.setBounds(100,235, 47,17);
        this.getContentPane().add(usernameLabel);
        this.getContentPane().add(pwdLabel);
        this.getContentPane().add(codeLabel);
        // 输入框
        usernameTextField.setBounds(160,130, 220,30);
        pwdTextField.setBounds(160,180, 220,30);
        codeTextField.setBounds(160,230, 110,30);
        pwdTextField.setEchoChar('*');
        this.getContentPane().add(usernameTextField);
        this.getContentPane().add(pwdTextField);
        this.getContentPane().add(codeTextField);
        // 密码可视化
        pwdVisibleButton.setIcon(new ImageIcon("image/login/眼睛_隐藏.png"));
        pwdVisibleButton.setBounds(380,175, 32,32);
        initButton(pwdVisibleButton);
        pwdVisibleButton.addMouseListener(this);
        this.getContentPane().add(pwdVisibleButton);

        // 验证码
        captcha = new Captcha().getCaptcha();
        captchaJLButton.setText(captcha);
        captchaJLButton.setBounds(280,230, 100,30);
        initButton(captchaJLButton);
        // 绑定监听事件
        captchaJLButton.addMouseListener(this);
        this.getContentPane().add(captchaJLButton);
        // 登录与注册按钮
        loginButton.setIcon(new ImageIcon("image/login/登录按钮.png"));
        registerButton.setIcon(new ImageIcon("image/login/注册按钮.png"));
        loginButton.setBounds(100,300, 128,47);
        registerButton.setBounds(260,300, 128,47);
        // 设置按钮格式
        initButton(loginButton);
        initButton(registerButton);

        // 绑定监听事件
        loginButton.addMouseListener(this);
        registerButton.addMouseListener(this);

        this.getContentPane().add(loginButton);
        this.getContentPane().add(registerButton);
        // 背景图片
        JLabel imageLabel = new JLabel(new ImageIcon("image/login/background.png"));
        imageLabel.setBounds(0, 0, 470,390);
        this.getContentPane().add(imageLabel);
    }

    // 优化按钮样式
    private void initButton(JButton btn) {
        // 设置按钮内容区域透明
        btn.setContentAreaFilled(false);
        // 不绘制边框
        btn.setBorderPainted(false);
        // 设置组件透明
        btn.setOpaque(false);
        // 不绘制焦点边框
        btn.setFocusPainted(false);
    }

    // 初始化界面
    private void initJFrame() {
        // 设置界面尺寸
        this.setSize(485, 428);
        // 设置界面标题
        this.setTitle("登录-拼图游戏单机版v1.0");
        // 设置界面居中
        this.setLocationRelativeTo(null);
        // 设置界面置顶
        this.setAlwaysOnTop(true);
        // 设置界面关闭模式
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 关闭组件居中
        this.setLayout(null);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Object source = e.getSource();
        if (source == captchaJLButton){
            // 刷新验证码
            System.out.println("刷新验证码");
            captcha = new Captcha().getCaptcha();
            captchaJLButton.setText(captcha);
//            this.getContentPane().repaint();
        }else if (source == pwdVisibleButton){
            if (pwdVisible){
                pwdVisible = false;
                pwdTextField.setEchoChar('*');
                pwdVisibleButton.setIcon(new ImageIcon("image/login/眼睛_隐藏.png"));
            }else {
                pwdVisible = true;
                pwdTextField.setEchoChar((char)0);
                pwdVisibleButton.setIcon(new ImageIcon("image/login/眼睛_显示.png"));
            }
        }else if (source == loginButton){
            System.out.println("登录点击");
            // 检查验证码是否正确
            if (!captcha.equals(codeTextField.getText())) {
                codeTextField.setText("");
                captcha = new Captcha().getCaptcha();
                captchaJLButton.setText(captcha);
                new ErrorTip("验证码错误，请重新输入");
                return;
            }
            String name = usernameTextField.getText();
            String pwd = new String(pwdTextField.getPassword());
            System.out.println(name);
            System.out.println(pwd);
            if(userControl.checkUser(name, pwd)){
                this.dispose();
                new ErrorTip("登录成功");
                new GameJFrame();
            }else {
                codeTextField.setText("");
                captchaJLButton.setText(new Captcha().getCaptcha());
                new ErrorTip("用户名或密码错误");
            }
        }else if (source == registerButton){
            this.dispose();
            try {
                new RegisterJFrame();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }


    @Override
    public void mousePressed(MouseEvent e) {
        Object source = e.getSource();
        if (source == loginButton){
            System.out.println("登录按下不松");
            loginButton.setIcon(new ImageIcon("image/login/登录按下.png"));
        }else if (source == registerButton){
            registerButton.setIcon(new ImageIcon("image/login/注册按下.png"));
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Object source = e.getSource();
        if (source == loginButton){
            System.out.println("登录释放");
            loginButton.setIcon(new ImageIcon("image/login/登录按钮.png"));
        }else if (source == registerButton){
            registerButton.setIcon(new ImageIcon("image/login/注册按钮.png"));
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
