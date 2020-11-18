import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MyPanel extends JPanel {

    static int frameWidth = 1200, frameHeight = 750;
    private int time = 0;           //tempo reale (milliseconds)
    private int dayValue = 500;     //quanto vale un giorno (milliseconds)
    static int numDays = 0;        //conteggio giorni

    private ArrayList<Person> people = new ArrayList<Person>();
    private ArrayList<Person> deaths = new ArrayList<>();

    private int dayCycle = 0;   //inizializzato per ciclare un giorno



    public MyPanel(){

        this.setPreferredSize(new Dimension(frameWidth, frameHeight));
        this.setBackground(Color.gray);

        for(int i=0; i<General.initPopulation; i++){
            people.add(new Person());
        }

        people.get(0).setYellowFromGreen();

    }

    public void paintComponent(Graphics g){


        time +=MyFrame.refreshRate;
        if(dayCycle == dayValue){
            dayCycle = 0;
            numDays++;
            printExit();

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
                    General.population--;
                }
                //p.resetMeetings(); //azzerare incontri (giornalieri)
            }
            for(Person p: deaths){
                people.remove(p);
            }

            for(Person p: people){
                General.resources--; //può essere ottimizzato
                if(p.getMovement()){
                    General.resources++;
                }
            }


            //STOP CONDITIONS
            if(Person.blacks>=General.initPopulation){
                System.out.println("LA MALATTIA HA VINTO!");
                printExit();
                MyFrame.timer.stop();
            }
            if(General.resources <= 0){
                System.out.println("COLLASSO! RISORSE TERMINATE!");
                printExit();
                MyFrame.timer.stop();
            }
            if((Person.blues>0 || Person.greens>0) && Person.yellows==0 && Person.reds==0){
                boolean end = true;
                for(Person p: people){
                    if(p.getType().equals("green") && p.isIncTimer()){
                        end = false;
                        break;
                    }
                }
                if(end){
                    System.out.println("MALATTIA DEBELLATA!");
                    printExit();
                    MyFrame.timer.stop();
                }
            }

        }else{
            dayCycle += MyFrame.refreshRate;
        }

        //check Collision
        for(int i = 0; i<people.size(); i++){
            for(int j = i+1; j<people.size(); j++){
                people.get(i).collision(people.get(j));
            }
        }

        //repaint "previous frame"
        super.paintComponent(g);

        //paint Person objects
        for (Person p: people){
            p.paint(g);
        }
        for(Person p: deaths){
            p.paint(g);
        }

        /*
        points.add(new Point(numDays, Person.yellows));//track of infected people
        //draw Graph points
        g.setColor(Color.BLACK);
        for(Point p: points){
            g.fillOval(p.getTime(), 200-p.getValue(), 5,5); //Graph "settings"
        }
         */
    }

    public void printExit(){
        System.out.println("Giorno: "+numDays+ "; Risorse: "+General.resources);
        System.out.print("Sani: "+Person.greens);
        System.out.print("; Asintomatici: "+Person.yellows);
        System.out.print("; Sintomatici: "+Person.reds);
        System.out.print("; Guariti: "+Person.blues);
        System.out.println("; Morti: "+Person.blacks+"\n");
    }



























}
