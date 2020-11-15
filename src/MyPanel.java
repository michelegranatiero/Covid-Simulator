import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MyPanel extends JPanel {

    static int frameWidth = 900, frameHeight = 650;
    private int refreshRate = 10;   //in milliseconds
    private int time = 0;           //tempo reale (milliseconds)
    private int dayValue = 500;     //quanto vale un giorno (milliseconds)
    static int numDays = 0;        //conteggio giorni
    private int numPeople = 100;

    private ArrayList<Person> people = new ArrayList<Person>();
    private ArrayList<Person> deaths = new ArrayList<>();
    private ArrayList<Point> points = new ArrayList<Point>();

    private int dayCycle = 0;   //inizializzato per ciclare un giorno
    private ActionListener actLis;
    private Timer timer;

    public MyPanel(){

        this.setPreferredSize(new Dimension(frameWidth, frameHeight));

        for(int i=0; i<numPeople; i++){
            people.add(new Person());
        }
        people.get(0).setYellow();

        //Timer for animation
        actLis = e -> repaint();
        timer = new Timer(refreshRate, actLis);
        timer.restart();

    }




    public void paint(Graphics g){


        time +=refreshRate;
        if(dayCycle == dayValue){
            dayCycle = 0;
            numDays++;
            System.out.println(numDays);

            for(Person p: people){
                if(p.getType().equals("red")){ // yellow to red maybe
                    p.checkRecovery();
                    p.checkLethality();
                }
                if(p.getType().equals("yellow")){ // yellow to red maybe
                    p.checkRecovery();
                    p.checkSymptomaticity();
                }
                if(p.getType().equals("green")){ // green to yellow
                    p.checkInfectivity();
                }
                if(p.getType().equals("black")){ // yellow to red maybe
                    deaths.add(p);
                    numPeople--;
                }
                //p.resetMeetings(); //azzerare incontri (giornalieri)
            }
            for(Person p: deaths){
                people.remove(p);
            }

            //for(Person p: people){} //codice per aumentare/diminuire risorse (escludere persone morte)

        }else{
            dayCycle += refreshRate;
        }

        if(numDays == 100){
            timer.stop();
        }



        //points.add(new Point(numDays, Person.numInfected));//track of infected people

        //repaint "previous frame"
        super.paintComponent(g);

        //paint Person objects
        for (Person p: people){
            p.paint(g);
        }
        for(Person p: deaths){
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
