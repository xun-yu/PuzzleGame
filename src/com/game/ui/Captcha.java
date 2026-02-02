package com.game.ui;

import java.util.Random;

// 生成验证码
// 4位字母+1位数字
public class Captcha {
    private final char[] alphabet = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
            'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    public String getCaptcha(){
        Random r = new Random();
        StringBuilder captcha = new StringBuilder();
        // 随机挑选字母，区分大小写
        for (int i = 0; i < 4; i++) {
            int index = r.nextInt(alphabet.length);
            if (r.nextBoolean()) {
                // 插入小写字母
                captcha.append(alphabet[index]);
            }else {
                // 插入大写字母
                captcha.append(Character.toUpperCase(alphabet[index]));
            }
        }
        // 生成随机数字，并随机插入字符串中任一位置
        int index = r.nextInt(5);
        captcha.insert(index, r.nextInt(10));
        // 返回验证码
        return captcha.toString();
    }
}
