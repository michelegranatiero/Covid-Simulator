import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.util.ArrayList;

public class MyPanel extends JPanel {

    static int frameWidth = 1200, frameHeight = 500;
    static int numDays = 0;         //conteggio
    //tempo reale (milliseconds)
    //private int dayValue = 500;   //quanto vale un giorno (milliseconds)
    //private int dayCycle = 0;     //inizializzato per ciclare un giorno
    private int contIncontri = General.velocity; //dynamic velocity vd
    private boolean end1 = false;   //
    private boolean end2 = false;
    private boolean end3 = false;
    private final ArrayList<Person> people = new ArrayList<>();
    private final ArrayList<Person> deaths = new ArrayList<>();
    private final ArrayList<Person> canBeTested;
    private final ArrayList<Person> redsArray;
    Strategies strategy;



    public MyPanel(){

        this.setPreferredSize(new Dimension(frameWidth, frameHeight));
        this.setBackground(MyFrame.backCol2);
        this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));


        for(int i=0; i<General.initPopulation; i++){
            people.add(new Person());
        }
        people.get(0).setYellowFromGreen();
        canBeTested = new ArrayList<>(people);
        redsArray = new ArrayList<>();

    }

    @Override
    public void paintComponent(Graphics g){

        if(contIncontri>=General.velocity){     // change possibility: if dayCycle=dayValue;
            //dayCycle = 0;
            numDays++;
            contIncontri = 0;


            //STRATEGY
            if(Person.reds >= 1){
                strategy = new Strategies(canBeTested, redsArray, numDays);
            }


            //printExit();
            //int vel = 0;  //control
            for(Person p: people){
                //vel += p.getMeetings().size();//control
                p.resetMeetings();

                General.resources--;    //può essere ottimizzato
                if(p.getMovement()){
                    General.resources++;
                }
                //change of "state"/color
                switch (p.getType()) {
                    case "red" -> {     // red to black/blue maybe
                        General.resources -= General.careCost;
                        p.checkLethality();
                        canBeTested.remove(p);
                        redsArray.add(p);
                    }
                    case "yellow" -> p.checkSymptomaticity();   // yellow to red/blue maybe
                    case "green" -> p.checkInfectivity();   // green to yellow
                    case "blue" -> {
                        canBeTested.remove(p);
                        redsArray.remove(p);
                    }
                    case "black" -> {
                        redsArray.remove(p);
                        deaths.add(p);
                        General.population--;
                    }
                }
            }
            //vel = Math.round((float)vel/people.size());   //control
            //System.out.println(vel); //control

        }/*else{
            dayCycle += MyFrame.refreshRate;
        } */


        //check collisions
        for(int i = 0; i<people.size(); i++){
            for(int j = i+1; j<people.size(); j++){
                people.get(i).collision(people.get(j));
            }
        }
        //check meetings
        for(Person p: people){
            contIncontri+= p.getMeetings().size();
        }
        contIncontri = Math.round((float)contIncontri/people.size()); //update dynamic velocity vd
        General.r0 = contIncontri * General.recoveryTime * General.infectivity; //update r0 factor

        //remove deaths from people
        for(Person p: deaths){
            people.remove(p);
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

        //STOP CONDITIONS
        if(Person.blacks>=General.initPopulation){
            if(end1){
                System.out.println("LA MALATTIA HA VINTO!");
                MyFrame.timer.stop();
                MyFrame.disabler("LA MALATTIA HA VINTO!");
            }else{end1=true;}
        }
        if(General.resources <= 0){
            if(end2){
                System.out.println("COLLASSO! RISORSE TERMINATE!");
                General.resources = 0;
                MyFrame.timer.stop();
                MyFrame.disabler("COLLASSO! RISORSE TERMINATE!");

            }else{end2 = true;}

        }
        if((Person.blues>0 || Person.greens>0) && Person.yellows==0 && Person.reds==0){
            if(end3){
                System.out.println("MALATTIA DEBELLATA!");
                MyFrame.timer.stop();
                MyFrame.disabler("MALATTIA DEBELLATA!");
            }else{
                boolean end = true;
                for(Person p: people){
                    if(p.getType().equals("green") && p.isIncTimer()){  //il virus potrebbe non essersi ancora manifestato
                        end = false;
                        break;
                    }
                }
                if(end){
                    end3=true;
                }
            }
        }
    }






    


}
