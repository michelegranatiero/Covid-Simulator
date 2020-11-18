import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GraphPanel extends JPanel {

    private ArrayList<Point> points = new ArrayList<Point>();

    private int time = 0;           //tempo reale (milliseconds)
    private int dayValue = 500;     //quanto vale un giorno (milliseconds)
    private int dayCycle = 0;   //inizializzato per ciclare un giorno

    public GraphPanel(){

        this.setBackground(Color.gray);
        this.setPreferredSize(new Dimension(400, 600));

    }

    public void paintComponent(Graphics g){

        /*
        time +=MyFrame.refreshRate;
        if(dayCycle == dayValue) {
            dayCycle = 0;

            points.add(new Point(MyPanel.numDays, Person.yellows));
            g.setColor(Color.BLACK);
            for(Point p: points) {
                g.fillOval(p.getTime(), 200 - p.getValue(), 5, 5); //Graph "settings"
            }
        }else{
            dayCycle += MyFrame.refreshRate;
        }

         */

        super.paintComponent(g);

        points.add(new Point(MyPanel.numDays, Person.yellows));//track of infected people
        //draw Graph points
        g.setColor(Color.BLACK);
        for(Point p: points){
            g.fillOval(p.getTime(), 200-p.getValue(), 5,5); //Graph "settings"
        }


    }

























}
