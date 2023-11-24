package ui;

import javax.swing.*;

public class TestImage {
    public static void main(String[] args) {
        ImageIcon image = new ImageIcon("/Users/shaynabahia/project_p7i9k/src/main/images/sammy.jpeg");
        JOptionPane.showMessageDialog(null, new JLabel(image));
    }
}