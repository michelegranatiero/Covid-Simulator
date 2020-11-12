import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MyPanel extends JPanel {

    static int frameWidth = 900, frameHeight = 650;
    int time = 0;
    int numPeople = 100;

    ArrayList<Person> people = new ArrayList<Person>();
    ArrayList<Point> points = new ArrayList<Point>();

    ActionListener actLis;

    public MyPanel(){

        this.setPreferredSize(new Dimension(frameWidth, frameHeight));

        for(int i=0; i<numPeople; i++){
            people.add(new Person());
        }

        //Timer for animation
        actLis = e -> repaint(); //action listener
        Timer t = new Timer(16, actLis);
        t.restart();

    }




    public void paint(Graphics g){
        time +=16;

        points.add(new Point(time/16, Person.numInfected));//track of infected people

        //repaint "previous frame"
        super.paintComponent(g);

        //paint Person objects
        for (Person p: people){
            p.paint(g);
        }

        //check Collision
        for(int i = 0; i<people.size(); i++){
            for(int j = i+1; j<people.size(); j++){
                people.get(i).collision(people.get(j));
            }
        }
        /*
        //draw Graph points
        g.setColor(Color.BLACK);
        for(Point p: points){
            g.fillOval(p.time, 200-p.value, 5,5); //Graph "settings"
        }*/
    }
































}
