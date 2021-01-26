package simulation;

import javax.swing.*;
import java.awt.*;

public class MyButton extends JButton {

    MyButton(String s){
        super(s);
        this.setBackground(MyFrame.backCol1);
        this.setForeground(Color.white);
        this.setFocusPainted(false);
        this.setFont(new Font("Segoe UI", Font.BOLD, 14));
    }
}