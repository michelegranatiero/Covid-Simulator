import javax.swing.*;
import java.awt.*;

public class StatsPanel extends JPanel {

    private final JLabel g = new JLabel();
    private final JLabel y = new JLabel();
    private final JLabel r = new JLabel();
    private final JLabel bl = new JLabel();
    private final JLabel bk = new JLabel();

    private final JLabel cl = new JLabel();

    public StatsPanel(){
        this.setLayout((new BorderLayout()));
        this.setBackground(MyFrame.backCol1);

        Color[] colors = new Color[]{Person.myGreen, Person.myYellow, Person.myRed, Person.myBlue, Person.myBlack};
        JLabel[] labels = new JLabel[]{g, y, r, bl, bk};

        //CENTERPANEL
        JPanel cenPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, GraphPanel.topPanelHeight/2 -12));
        cenPanel.setPreferredSize(new Dimension(MyPanel.frameWidth/10, GraphPanel.topPanelHeight));
        cenPanel.setBackground(MyFrame.backCol1);
        this.add(cenPanel, BorderLayout.CENTER);
        cenPanel.add(cl);
        cl.setFont(new Font("Segoe UI", Font.BOLD, 20));
        cl.setForeground(Color.white);

        //LEFTPANEL
        JPanel leftPanel = new JPanel();
        this.add(leftPanel, BorderLayout.WEST);
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setPreferredSize(new Dimension(MyPanel.frameWidth/10, GraphPanel.topPanelHeight));
        leftPanel.setBackground(MyFrame.backCol1);
        leftPanel.setAlignmentX(RIGHT_ALIGNMENT);

        int i = 0;
        for(JLabel l: labels){
            JPanel p = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 0));
            p.setPreferredSize(new Dimension(MyPanel.frameWidth/10, GraphPanel.topPanelHeight/5));
            p.setBackground(MyFrame.backCol1);
            p.add(l);
            leftPanel.add(p);
            l.setForeground(colors[i]);
            i++;
            l.setFont(new Font("Segoe UI", Font.BOLD, 14));
        }
    }

    public void updateLabels(){
        //CENTERPANEL
        cl.setText("Giorno: "+MyPanel.numDays+ "          Risorse: "+ General.resources);

        //LEFTPANEL
        g.setText("Sani: " + Person.greens);
        y.setText("Asintomatici: " + Person.yellows);
        r.setText("Sintomatici: " + Person.reds);
        bl.setText("Guariti: " + Person.blues);
        bk.setText("Deceduti: " + Person.blacks);
    }
}
