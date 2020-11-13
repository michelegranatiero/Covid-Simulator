import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MyPanel extends JPanel {

    static int frameWidth = 900, frameHeight = 650;
    int refreshRate = 10;   //in milliseconds
    int time = 0;           //tempo reale (milliseconds)
    int dayValue = 500;     //quanto vale un giorno (milliseconds)
    int numDays = 0;        //conteggio giorni
    int numPeople = 100;

    ArrayList<Person> people = new ArrayList<Person>();
    ArrayList<Point> points = new ArrayList<Point>();

    int dayCycle = 0;   //inizializzato per ciclare un giorno
    ActionListener actLis;
    Timer timer;

    public MyPanel(){

        this.setPreferredSize(new Dimension(frameWidth, frameHeight));

        for(int i=0; i<numPeople; i++){
            people.add(new Person());
        }

        //Timer for animation
        actLis = e -> repaint();
        timer = new Timer(refreshRate, actLis);
        timer.start();

    }




    public void paint(Graphics g){


        time +=refreshRate;
        if(dayCycle == dayValue){
            dayCycle = 0;
            numDays++;
            System.out.println(numDays);
        }else{
            dayCycle += refreshRate;
        }

        if(numDays == 50){
            timer.stop();
        }



        //points.add(new Point(numDays, Person.numInfected));//track of infected people

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
