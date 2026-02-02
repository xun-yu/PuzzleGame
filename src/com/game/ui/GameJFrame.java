package com.game.ui;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.Random;

public class GameJFrame extends JFrame implements KeyListener, ActionListener {
    // 存储图片顺序
    int[][] data = new int[4][4];
    int x=0;
    int y=0;
    // 步数计数器
    int step = 0;
    // 随机生成
    Random r = new Random();

    // 新建下级菜单选项
    JMenuItem animalItem = new JMenuItem("动物");
    JMenuItem sportItem = new JMenuItem("运动");
    JMenuItem replayMenuItem = new JMenuItem("重新开始");
    JMenuItem reLoginMenuItem = new JMenuItem("重新登录");
    JMenuItem closeMenuItem = new JMenuItem("关闭游戏");
    JMenuItem aboutMenuItem = new JMenuItem("免责声明");

    // 存储胜利数据
    final int[][] win = new int[][]{
            {1,2,3,4},
            {5,6,7,8},
            {9,10,11,12},
            {13,14,15,0}
    };
    // 图片路径
    String path = "image/%s/%s%d/%d.jpg";
    // 图片类型
    String type = "animal";
    // 图片序号
    int number = 3;

    // 构建游戏界面
    public GameJFrame() {
        // 初始化界面
        initJFrame();

        // 初始化菜单
        initMenu();

        // 生成图片顺序
        initData();

        // 初始化图片
        initImage();



        this.setVisible(true);
    }

    // 数据初始化
    private void initData() {
        // 初始化数据组
        int[] numbers = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        // 打乱顺序
        Random r = new Random();
        for (int i = 0; i < 16; i++) {
            int f = r.nextInt(16);
            int temp = numbers[i];
            numbers[i] = numbers[f];
            numbers[f] = temp;
        }

        // 将数据填入二维数组
        for (int i = 0; i < 16; i++) {
            if (numbers[i] == 0){
                x = i/4;
                y = i%4;
            }
            data[i / 4][i % 4] = numbers[i];
        }
    }

    // 拼图初始化
    private void initImage() {
        // 清除界面中之前的所有图片
        this.getContentPane().removeAll();

        // 判断游戏是否胜利
        if (victory()){
            JLabel jLabel = new JLabel(new ImageIcon("image/win.png"));
            jLabel.setBounds(280,280,197,73);
            this.getContentPane().add(jLabel);
        }

        // 添加步数
        JLabel st = new JLabel("步数：" + step);
        st.setBounds(30,50,120,30);
        this.getContentPane().add(st);

        // 将拼图图片添加到界面
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int count = data[i][j];
                JLabel label = new JLabel(new ImageIcon(String.format(path, type, type, number, count)));
                label.setBounds(j*105+190, i*105+163, 105,105);
                // 设置边框效果
                label.setBorder(new BevelBorder(BevelBorder.LOWERED));
                this.getContentPane().add(label);
            }
        }

        // 添加背景图片
        initBackground();
        // 刷新界面
        this.getContentPane().repaint();
    }

    // 初始化背景图片
    private void initBackground() {
        JLabel back = new JLabel(new ImageIcon("image/background.png"));
        back.setBounds(0, 0, this.getWidth(), this.getHeight());
        this.getContentPane().add(back);
    }


    // 初始化菜单栏
    private void initMenu() {
        // 新建菜单栏
        JMenuBar menuBar = new JMenuBar();
        // 新建菜单选项
        JMenu functionMenu = new JMenu("功能");
        JMenu changeImage = new JMenu("更换图片");
        JMenu aboutMenu = new JMenu("关于我们");

        // 将选项添加到菜单中
        changeImage.add(animalItem);
        changeImage.add(sportItem);

        functionMenu.add(changeImage);
        functionMenu.add(replayMenuItem);
        functionMenu.add(reLoginMenuItem);
        functionMenu.add(closeMenuItem);
        aboutMenu.add(aboutMenuItem);

        // 添加事件监听
        animalItem.addActionListener(this);
        sportItem.addActionListener(this);
        replayMenuItem.addActionListener(this);
        reLoginMenuItem.addActionListener(this);
        closeMenuItem.addActionListener(this);
        aboutMenuItem.addActionListener(this);

        // 将菜单添加到菜单栏中
        menuBar.add(functionMenu);
        menuBar.add(aboutMenu);

        // 将菜单栏添加到界面中
        this.setJMenuBar(menuBar);
    }

    // 初始化界面
    private void initJFrame() {
        // 设置界面尺寸
        this.setSize(800, 700);
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
        // 添加键盘事件监听
        this.addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        // 游戏胜利后，终止键盘操作
        if (victory()) return;
        int code = e.getKeyCode();
        // 按A查看原始图片
        if (code == 65) {
            this.getContentPane().removeAll();
            String pa = String.format("image\\%s\\%s%d\\all.jpg", type, type, number);
            JLabel jLabel = new JLabel(new ImageIcon(pa));
            jLabel.setBounds(190, 163,420,420);
            this.getContentPane().add(jLabel);
            initBackground();
            this.getContentPane().repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // 游戏胜利后，终止键盘操作
        if (victory()) return;

        int code = e.getKeyCode();
//        System.out.println(code);
        // 向左移动
        if (code==37){
//                System.out.println("左");
                if (y==0) return;
                data[x][y] = data[x][y-1];
                data[x][--y] = 0;
                step++;
                initImage();
            }
        // 向上移动
        else if (code==38){
//                System.out.println("上");
                if (x==0) return;
                data[x][y] = data[x-1][y];
                data[--x][y] = 0;
                step++;
                initImage();
            }
        // 向右移动
        else if (code==39){
//                System.out.println("右");
                if (y==3) return;
                data[x][y] = data[x][y+1];
                data[x][++y] = 0;
                step++;
                initImage();
            }
        // 向下移动
        else if (code==40){
//                System.out.println("下");
                if (x==3) return;
                data[x][y] = data[x+1][y];
                data[++x][y] = 0;
                step++;
                initImage();
            }
        // 按A键查看原图，松开恢复拼图
        else if (code==65){
                initImage();
            }
        // 作弊键 W，松开后直接完成拼图
        else if (code==87){
            data = new int[][]{
                    {1, 2, 3, 4},
                    {5, 6, 7, 8},
                    {9, 10, 11, 12},
                    {13, 14, 15, 0}
            };
            // 拼图图片更新
            initImage();
        }
    }

    // 判断游戏胜利
    public boolean victory(){
        for (int i = 0; i < 4; i++) {
            for(int j = 0; j< 4; j++){
                if (data[i][j] != win[i][j])
                    return false;
            }
        }
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        // 重新开始
        if (obj==replayMenuItem){
            this.getContentPane().removeAll();
            System.out.println("重新开始");
            step = 0;
            initData();
            initImage();
        }
        // 重新登录
        else if (obj == reLoginMenuItem){
            System.out.println("重新登录");
//            this.setVisible(false);
            // 关闭当前窗口，并释放占用的资源
            this.dispose();
            // 打开登录界面
            try {
                new LoginJFrame();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        // 退出游戏
        else if (obj == closeMenuItem){
            System.out.println("退出游戏");
            System.exit(0);
        }
        // 免责声明
        else if (obj == aboutMenuItem){
            System.out.println("免责声明");
            JDialog jDialog = new JDialog();
            jDialog.setTitle("免责声明-拼图游戏");
            // 取消界面布局
            jDialog.setLayout(null);
            JTextArea jTextArea = new JTextArea("   本游戏基于黑马程序员课程教学编写设计，仅用于学习用途。");
            // 设置文本框位置
            jTextArea.setBounds(20,30,300,200);
            // 设置字体
            jTextArea.setFont(new Font("宋体", Font.PLAIN, 20));
            // 自动换行
            jTextArea.setLineWrap(true);
            // 设置文本框不可编辑
            jTextArea.setEditable(false);
            // 设置字体颜色
//            jTextArea.setForeground(Color.black);
            // 设置背景透明
            jTextArea.setOpaque(false);
            // 添加到界面中
            jDialog.getContentPane().add(jTextArea);
            // 设置界面大小
            jDialog.setSize(344,344);
            // 设置界面置顶
            jDialog.setAlwaysOnTop(true);
            // 设置居中
            jDialog.setLocationRelativeTo(null);
            // 设置弹窗关闭前，无法操作其他界面
            jDialog.setModal(true);
            // 设置弹窗可见
            jDialog.setVisible(true);
        }
        // 修改图片类型--动物
        else if (obj == animalItem){
            type = "animal";
            Random r = new Random();
            number = r.nextInt(8) + 1;
            initData();
            initImage();
        }
        // 修改图片类型--运动
        else if (obj == sportItem){
            type = "sport";
            Random r = new Random();
            number = r.nextInt(10) + 1;
            initData();
            initImage();
        }
    }
}
