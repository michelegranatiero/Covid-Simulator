import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.util.ArrayList;

public class GraphPanel extends JPanel {

    static ArrayList<Point> gPoints = new ArrayList<>();
    static ArrayList<Point> yPoints = new ArrayList<>();
    static ArrayList<Point> rPoints = new ArrayList<>();
    static ArrayList<Point> bPoints = new ArrayList<>();
    static ArrayList<Point> bkPoints = new ArrayList<>();


    static int graphWidth;
    static int topPanelHeight = 100;
    private final int panelWidth = MyPanel.frameWidth/2;
    private final int daysInGraph = 100; //NUMERO MAX DI GIORNI CHE RIENTRANO NEL GRAFICO

    private final int vLineInterval = 10; //intervallo di giorni trascorsi tra due linee verticali

    private final int strokeWidth = 4;
    private final BasicStroke myStroke= new BasicStroke(strokeWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);

    private final float c = General.initPopulation / (float)(topPanelHeight -10); //costante per mettere in scala il grafico verticalmente


    public GraphPanel(){

        this.setBackground(MyFrame.backCol1);
        this.setPreferredSize(new Dimension(panelWidth, topPanelHeight));

    }


    @Override
    public void paintComponent(Graphics g1){

        super.paintComponent(g1);

        graphWidth = MyPanel.numDays * panelWidth/daysInGraph - panelWidth/daysInGraph;

        gPoints.add(new Point(graphWidth, Math.round(Person.greens/c)));
        yPoints.add(new Point(graphWidth, Math.round(Person.yellows/c)));
        rPoints.add(new Point(graphWidth, Math.round(Person.reds/c)));
        bPoints.add(new Point(graphWidth, Math.round(Person.blues/c)));
        bkPoints.add(new Point(graphWidth, Math.round(Person.blacks/c)));

        drawCharts(g1, gPoints, Person.myGreen);
        drawCharts(g1, yPoints, Person.myYellow);
        drawCharts(g1, rPoints, Person.myRed);
        drawCharts(g1, bPoints, Person.myBlue);
        drawCharts(g1, bkPoints, Person.myBlack);

    }

    public void drawCharts(Graphics g1, ArrayList<Point> a, Color c){
        if(a.size()>=1) {
            float flowFactor = 0;
            for (int i = 1; i < a.size(); i++) {
                if(MyPanel.numDays >= daysInGraph){
                    flowFactor = (daysInGraph-MyPanel.numDays)*((float)panelWidth/daysInGraph);//fattore per fare scorrere il grafico
                    if(a.get(i).getTime()+flowFactor < 0){
                        continue;
                    }
                }
                Point p1 = a.get(i - 1);
                Point p2 = a.get(i);
                Graphics2D g2 = (Graphics2D) g1;
                g2.setStroke(myStroke);
                g2.setColor(c);
                g2.draw(new Line2D.Float(p1.getTime()+flowFactor, topPanelHeight-strokeWidth - p1.getValue(), p2.getTime()+flowFactor, topPanelHeight - strokeWidth-p2.getValue()));

                if(p2.getDay()%(vLineInterval) == 0){
                    g2.setStroke(new BasicStroke(1));
                    g2.setColor(new Color(250,250,250, 10));
                    g2.draw(new Line2D.Float(p2.getTime()+flowFactor, 0, p2.getTime()+flowFactor, topPanelHeight));
                    g2.setFont(new Font("Segoe UI", Font.BOLD, 10));
                    g2.drawString(" "+p2.getDay(), p2.getTime()+flowFactor, 10);

                }
            }
        }
    }
























}
