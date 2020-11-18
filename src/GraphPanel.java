import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GraphPanel extends JPanel {

    private Timer timer;
    private ActionListener actLis;

    public GraphPanel(){

        this.setBackground(Color.gray);

        //Timer per animazione
        actLis = e -> repaint();
        timer = new Timer(MyFrame.refreshRate, actLis);
        timer.restart();
    }
}
