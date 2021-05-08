package simulation;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.util.ArrayList;

public class MyPanel extends JPanel {

    MyFrame frame;
    static int frameWidth = 1200, frameHeight = 500;
    //tempo reale (milliseconds)
    //private int dayValue = 500;   //quanto vale un giorno (milliseconds)
    //private int dayCycle = 0;     //inizializzato per ciclare un giorno
    private int contIncontri = General.velocity; //dynamic velocity vd
    private boolean flag = false;
    private boolean end1 = false;   //fine per malattia vince
    private boolean end2 = false;   //fine per collasso
    private boolean end3 = false;   //fine per malattia debellata
    private final ArrayList<Person> people = new ArrayList<>();
    private final ArrayList<Person> deaths = new ArrayList<>();
    private final ArrayList<Person> canBeTested;    //utile per strategia
    private final ArrayList<Person> redsArray;      //utile per strategia
    Strategies strategy;


    public MyPanel(MyFrame myframe, int numDays){
        frame = myframe;

        this.setPreferredSize(new Dimension(frameWidth, frameHeight));
        this.setBackground(MyFrame.backCol2);
        this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));

        //genero la popolazione
        for(int i=0; i<General.initPopulation; i++){
            people.add(new Person());
        }
        Person zero = people.get(0);
        canBeTested = new ArrayList<>(people);
        redsArray = new ArrayList<>();
        zero.setFirstYellow(); //inserisco il virus nella popolazione (paziente 0)
        strategy = new Strategies(frame);

    }

    @Override
    public void paintComponent(Graphics g2){
        Graphics2D g = (Graphics2D)g2;
        g.setRenderingHints(new RenderingHints(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON));


        if(contIncontri>=General.velocity){     //i giorni avanzano quando la media degli incontri giornalieri è >= al parametro indicato

            frame.plusOneNumDays();
            contIncontri = 0;

            //STRATEGIA

            if((Person.reds >= 1 && (Person.yellows + Person.reds > frame.getPopulation()*2/100)) || flag){
                flag = true;
                strategy.startStrategy(canBeTested, redsArray, frame.getNumDays());
            }


            //controlli giornalieri
            for(Person p: people){

                p.resetMeetings();      //reset incontri Giornaliero

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
                        frame.population--; //!fare una funzione!
                    }
                }
            }
        }


        //check collisioni
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

        //rimuovere deceduti da array People
        for(Person p: deaths){
            people.remove(p);
        }

        //repaint frame precedente
        super.paintComponent(g);

        //paint Simulation.Person
        for (Person p: people){
            p.paint(g);
        }
        for(Person p: deaths){
            p.paint(g);
        }

        //STOP CONDITIONS
        if(Person.blacks>=General.initPopulation){
            if(end1){
                //System.out.println("LA MALATTIA HA VINTO!");
                frame.timer.stop();
                frame.disabler("LA MALATTIA HA VINTO!");
            }else{end1=true;}
        }
        if(General.resources <= 0){
            if(end2){
                //System.out.println("COLLASSO! RISORSE TERMINATE!");
                General.resources = 0;
                frame.timer.stop();
                frame.disabler("COLLASSO! RISORSE TERMINATE!");

            }else{end2 = true;}

        }
        if((Person.blues>0 || Person.greens>0) && Person.yellows==0 && Person.reds==0){
            if(end3){
                //System.out.println("MALATTIA DEBELLATA!");
                frame.timer.stop();
                frame.disabler("MALATTIA DEBELLATA!");
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
