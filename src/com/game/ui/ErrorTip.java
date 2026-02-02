package com.game.ui;

import javax.swing.*;

public class ErrorTip {
    public ErrorTip(String title) {
        // 错误弹窗
        JDialog dialog = new JDialog();
        dialog.setSize(250,100);
        dialog.setModal(true);
        dialog.setAlwaysOnTop(true);
        dialog.setLocationRelativeTo(null);
        JLabel jLabel = new JLabel(title);
        dialog.add(jLabel);
        dialog.setVisible(true);
    }
}
