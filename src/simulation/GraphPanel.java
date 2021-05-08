package simulation;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.util.ArrayList;

public class GraphPanel extends JPanel {

    ArrayList<Point> gPoints = new ArrayList<>();
    ArrayList<Point> yPoints = new ArrayList<>();
    ArrayList<Point> rPoints = new ArrayList<>();
    ArrayList<Point> bPoints = new ArrayList<>();
    ArrayList<Point> bkPoints = new ArrayList<>();


    static int graphWidth;
    static int topPanelHeight = 100;
    private final int panelWidth = MyPanel.frameWidth/2;
    private final int daysInGraph = 100; //NUMERO MAX DI GIORNI CHE RIENTRANO NEL GRAFICO

    private final int vLineInterval = 10; //intervallo di giorni trascorsi tra due linee verticali

    private final int strokeWidth = 4;
    private final BasicStroke myStroke= new BasicStroke(strokeWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);

    private final float c = General.initPopulation / (float)(topPanelHeight -10); //costante per mettere in scala il grafico verticalmente
    MyFrame frame;
    boolean resetGraphicsflag = false;

    public GraphPanel(MyFrame myframe){
        frame = myframe;
        this.setBackground(MyFrame.backCol1);
        this.setPreferredSize(new Dimension(panelWidth, topPanelHeight));

    }


    @Override
    public void paintComponent(Graphics g2){

        Graphics2D g1 = (Graphics2D)g2;
        g1.setRenderingHints(new RenderingHints(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON));

        super.paintComponent(g1);

        graphWidth = frame.getNumDays() * panelWidth/daysInGraph - panelWidth/daysInGraph;

        //liste che contengono tuti i punti da collegare
        gPoints.add(new Point(graphWidth, Math.round(Person.greens/c), frame.getNumDays()));
        yPoints.add(new Point(graphWidth, Math.round(Person.yellows/c), frame.getNumDays()));
        rPoints.add(new Point(graphWidth, Math.round(Person.reds/c), frame.getNumDays()));
        bPoints.add(new Point(graphWidth, Math.round(Person.blues/c), frame.getNumDays()));
        bkPoints.add(new Point(graphWidth, Math.round(Person.blacks/c), frame.getNumDays()));

        //costruisco i grafici per ogni tipo (colore)
        drawCharts(g1, gPoints, Person.myGreen);
        drawCharts(g1, yPoints, Person.myYellow);
        drawCharts(g1, rPoints, Person.myRed);
        drawCharts(g1, bPoints, Person.myBlue);
        drawCharts(g1, bkPoints, Person.myBlack);

    }

    //Costruisco il grafico collegando i punti a due a due con una linea
    public void drawCharts(Graphics g1, ArrayList<Point> a, Color c){
        if(a.size()>=1) {
            float flowFactor = 0;
            for (int i = 1; i < a.size(); i++) {
                if(frame.getNumDays() >= daysInGraph){
                    flowFactor = (daysInGraph-frame.getNumDays())*((float)panelWidth/daysInGraph);//fattore per fare scorrere il grafico
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

                //Genero delle linee ad intervalli per tenere traccia dei giorni sul grafico
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
