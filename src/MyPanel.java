import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.util.ArrayList;

public class MyPanel extends JPanel {

    static int frameWidth = 1200, frameHeight = 600;
    private int time = 0;           //tempo reale (milliseconds)
    private int dayValue = 500;     //quanto vale un giorno (milliseconds)
    static int numDays = 0;        //conteggio
    private boolean end1 = false;
    private boolean end2 = false;
    private boolean end3 = false;

    private ArrayList<Person> people = new ArrayList<Person>();
    private ArrayList<Person> deaths = new ArrayList<>();

    private int dayCycle = 0;   //inizializzato per ciclare un giorno



    public MyPanel(){

        this.setPreferredSize(new Dimension(frameWidth, frameHeight));
        this.setBackground(Color.gray);
        this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));

        for(int i=0; i<General.initPopulation; i++){
            people.add(new Person());
        }

        people.get(0).setYellowFromGreen();

    }

    @Override
    public void paintComponent(Graphics g){


        time +=MyFrame.refreshRate;
        if(dayCycle == dayValue){
            dayCycle = 0;
            numDays++;

            //STOP CONDITIONS

            if(Person.blacks>=General.initPopulation){
                if(end1){
                    System.out.println("LA MALATTIA HA VINTO!");
                    MyFrame.timer.stop();
                }else{end1=true;}
            }
            if(General.resources <= 0){
                if(end2){
                    System.out.println("COLLASSO! RISORSE TERMINATE!");
                    General.resources = 0;
                    MyFrame.timer.stop();
                }else{end2=true;}

            }
            if((Person.blues>0 || Person.greens>0) && Person.yellows==0 && Person.reds==0){
                if(end3){
                    System.out.println("MALATTIA DEBELLATA!");
                    MyFrame.timer.stop();
                }else{
                    boolean end = true;
                    for(Person p: people){
                        if(p.getType().equals("green") && p.isIncTimer()){
                            end = false;
                            break;
                        }
                    }
                    if(end){
                        end3=true;
                    }
                }
            }

            //DO THE THINGS
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
        System.out.println("; Deceduti: "+Person.blacks+"\n");
    }



























}
