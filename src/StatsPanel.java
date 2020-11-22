import javax.swing.*;
import java.awt.*;

public class StatsPanel extends JPanel {

    JLabel g = new JLabel();
    JLabel y = new JLabel();
    JLabel r = new JLabel();
    JLabel bl = new JLabel();
    JLabel bk = new JLabel();

    JLabel cl = new JLabel();

    JLabel[] labels;
    Color[] colors;

    public StatsPanel(){
        this.setLayout((new BorderLayout()));
        this.setBackground(MyFrame.backCol1);

        //CENTERPANEL
        JPanel cenPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 40));
        cenPanel.setPreferredSize(new Dimension(MyPanel.frameWidth/10, 110));
        cenPanel.setBackground(MyFrame.backCol1);
        this.add(cenPanel, BorderLayout.CENTER);
        cenPanel.add(cl);
        cl.setFont(new Font("Segoe UI", Font.BOLD, 20));
        cl.setForeground(Color.white);

        //LEFTPANEL
        JPanel rightPanel = new JPanel();
        this.add(rightPanel, BorderLayout.WEST);
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setPreferredSize(new Dimension(MyPanel.frameWidth/10, 110));
        rightPanel.setBackground(MyFrame.backCol1);
        rightPanel.setAlignmentX(RIGHT_ALIGNMENT);

        colors = new Color[]{Person.myGreen, Person.myYellow, Person.myRed, Person.myBlue, Color.black};
        labels = new JLabel[]{g, y, r, bl, bk};

        int i = 0;
        for(JLabel l: labels){
            JPanel p = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 0));
            p.setPreferredSize(new Dimension(MyPanel.frameWidth/10, 110/5));
            p.setBackground(MyFrame.backCol1);
            p.add(l);
            rightPanel.add(p);
            l.setForeground(colors[i]);
            i++;
            l.setFont(new Font("Segoe UI", Font.BOLD, 14));
            //l.setHorizontalAlignment(FlowLayout.RIGHT);
            //font, dimensione...
        }

    }

    public void updateLabels(){
        //CENTERPANEL

        cl.setText("Giorno: "+MyPanel.numDays+ "            Risorse: "+General.resources);

        //LEFTPANEL
        g.setText("Sani: " + Person.greens);
        y.setText("Asintomatici: " + Person.yellows);
        r.setText("Sintomatici: " + Person.reds);
        bl.setText("Guariti: " + Person.blues);
        bk.setText("Deceduti: " + Person.blacks);
    }


}
