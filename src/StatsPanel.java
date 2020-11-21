import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;

public class StatsPanel extends JPanel {

    JLabel g = new JLabel();
    JLabel y = new JLabel();
    JLabel r = new JLabel();
    JLabel bl = new JLabel();
    JLabel bk = new JLabel();

    JLabel[] labels;
    Color[] colors;
    String[] texts = new String[]{};


    public StatsPanel(){
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setPreferredSize(new Dimension(MyPanel.frameWidth/12, 110));
        this.setBackground(Color.gray);

        colors = new Color[]{Person.myGreen, Person.myYellow, Person.myRed, Color.blue, Color.black};
        labels = new JLabel[]{g, y, r, bl, bk};

        int i = 0;
        for(JLabel l: labels){
            this.add(l);
            l.setForeground(colors[i]);
            i++;
            l.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            l.setHorizontalAlignment(SwingConstants.RIGHT);
            //font, dimensione...
        }

    }

    public void updateLabels(){
        g.setText("Sani: " + Person.greens);
        y.setText("Asintomatici: " + Person.yellows);
        r.setText("Sintomatici: " + Person.reds);
        bl.setText("Guariti: " + Person.blues);
        bk.setText("Deceduti: " + Person.blacks);
    }


}
