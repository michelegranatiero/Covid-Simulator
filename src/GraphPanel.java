import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.geom.Line2D;
import java.util.ArrayList;

public class GraphPanel extends JPanel {

    static ArrayList<Point> gPoints = new ArrayList<Point>();
    static ArrayList<Point> yPoints = new ArrayList<Point>();
    static ArrayList<Point> rPoints = new ArrayList<Point>();
    static ArrayList<Point> bPoints = new ArrayList<Point>();
    static ArrayList<Point> bkPoints = new ArrayList<Point>();

    private final int panelWidth = MyPanel.frameWidth/2, panelHeight=110;

    public GraphPanel(){

        this.setBackground(Color.gray);
        this.setPreferredSize(new Dimension(panelWidth, panelHeight ));
        //this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));


    }

    @Override
    public void paintComponent(Graphics g1){

        super.paintComponent(g1);

        int graphWidth = MyPanel.numDays*10;

        float c = General.initPopulation / (float)(panelHeight-10); //costante per mettere in scala il grafico

        gPoints.add(new Point(graphWidth, Math.round(Person.greens/c)));
        yPoints.add(new Point(graphWidth, Math.round(Person.yellows/c)));
        rPoints.add(new Point(graphWidth, Math.round(Person.reds/c)));
        bPoints.add(new Point(graphWidth, Math.round(Person.blues/c)));
        bkPoints.add(new Point(graphWidth, Math.round(Person.blacks/c)));

        drawCharts(g1, gPoints, Person.myGreen);
        drawCharts(g1, yPoints, Person.myYellow);
        drawCharts(g1, rPoints, Person.myRed);
        drawCharts(g1, bPoints, Color.blue);
        drawCharts(g1, bkPoints, Color.black);


    }

    public void drawCharts(Graphics g1, ArrayList<Point> a, Color c){
        if(a.size()>2) {
            for (int i = 1; i < a.size(); i++) {
                Point p1 = a.get(i - 1);
                Point p2 = a.get(i);
                Graphics2D g2 = (Graphics2D) g1;
                int strokeWidth = 4;
                BasicStroke myStroke= new BasicStroke(strokeWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
                g2.setStroke(myStroke);
                g2.setColor(c);
                g2.draw(new Line2D.Float(p1.getTime(), panelHeight-strokeWidth-p1.getValue(), p2.getTime(), panelHeight-strokeWidth-p2.getValue()));
            }
        }
    }

























}
