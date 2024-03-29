package simulation;

import java.awt.*;
import java.util.ArrayList;

public class Person {
    private final int ovalDim = 11;
    static int opacity = 255;
    static Color myGreen = new Color(0, 180, 51, opacity);
    static Color myYellow = new Color(255, 153, 0, opacity);
    static Color myRed = new Color(190,0,0, opacity);
    static Color myBlue = new Color(50,170,255, opacity);
    static Color myBlack = new Color(0,0,0, opacity);


    private int x,y;
    private int vx, vy;
    private String type;   //colore

    private boolean movement = true;     //movimento si/no

    private boolean incTimer = false;    //incubation timer on/off
    private int incDays = 0;
    private int theDay;                 //chosen day for checkSymptomaticity
    private int theDay2;                 //chosen day for checkLethality

    //CONTATORI
    static int greens = 0;
    static int yellows = 0;
    static int reds = 0;
    static int blues = 0;
    static int blacks = 0;


    private final ArrayList<Person> meetings= new ArrayList<>();    //incontri di ciascun individuo in un giorno


    public Person(){
        this.setType("green");
        greens++;

        x = (int)(Math.random()*(MyPanel.frameWidth - ovalDim));
        y = (int)(Math.random()*(MyPanel.frameHeight - ovalDim));

        //movimento
        vx = (int)(Math.random()*General.speed + 1)*(Math.random()<0.5?1:-1);
        vy = (int)(Math.random()*General.speed + 1)*(Math.random()<0.5?1:-1);

    }


    public void paint(Graphics g2){
        Graphics2D g = (Graphics2D)g2;
        g.setRenderingHints(new RenderingHints(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON));
        //imposto il colore della persona in base allo stato di salute
        switch(type) {
            case "green": //normal
                g.setColor(myGreen);
                break;
            case "yellow": //infected no symptoms
                g.setColor(myYellow);
                break;
            case "red": //infected with symptoms
                g.setColor(myRed);
                break;
            case "blue": //recovered
                g.setColor(myBlue);
                break;
            case "black": //death
                g.setColor(myBlack);
                break;
        }



        //x and y aggiornate con le loro velocità
        if(this.movement) {
            x += vx;
            y += vy;

            //border bounce
            if (x < 0 || x >= (MyPanel.frameWidth - ovalDim)) {
                vx *= -1;
            }
            if (y < 0 || y >= (MyPanel.frameHeight - ovalDim)) {
                vy *= -1;
            }
        }

        g.fillOval(x, y, ovalDim, ovalDim);

        //OVAL BORDER
        //g.setColor(Color.BLACK);
        //g.drawOval(x, y, ovalDim, ovalDim);
    }



    public void collision(Person p){

        //Rappresento una persona con un rettangolo per semplificare la collision detection
        Rectangle r1 = new Rectangle(this.x, this.y, ovalDim, ovalDim);
        Rectangle r2 = new Rectangle(p.x, p.y, ovalDim, ovalDim);

        //collision check
        if(r1.intersects(r2)){
            //add to meeting list
            this.meetings.add(p);
            p.meetings.add(this);

            if(this.type.equals("green") && (p.type.equals("yellow") || p.type.equals("red"))){
                if(Math.random() < General.infectivity){
                    this.incTimer = true;
                    //this.recTimer = true;
                }
            }else if((this.type.equals("yellow") || this.type.equals("red")) && p.type.equals("green")){
                if(Math.random() < General.infectivity){
                    p.incTimer = true;
                    //p.recTimer = true;
                }
            }
        }
    }


    public String getType(){
        return this.type;
    }

    public void setType(String s){
        this.type = s;
    }

    public boolean getMovement(){
        return this.movement;
    }

    public void setMovement(boolean movement) {
        this.movement = movement;
    }

    public boolean isIncTimer() {
        return incTimer;
    }

    public void resetMeetings(){
        meetings.clear();
    }

    public ArrayList<Person> getMeetings(){
        return meetings;
    }

    public void checkInfectivity(){
        if(incTimer && this.type.equals("green")){
            if(incDays == General.incubationPeriod){
                this.type = "yellow";
                yellows++;
                greens--;
                incTimer = false;
                incDays = 0;
                checkSymptomaticity();
            }else{
                incDays++;
            }
        }
    }

    public void checkSymptomaticity(){
        if(this.type.equals("yellow")){
           if(incDays == 0){
               if(Math.random() < General.symptomaticity){
                   incTimer = true;
                   theDay = (int)(Math.random()*General.symptomaticityPeriod + 1);
               }else{
                   theDay = General.recoveryTime + 1;
               }
           }else if(incTimer && theDay==incDays){
               this.type = "red";
               incTimer = false;
               reds++;
               yellows--;
               this.movement = false;
               checkLethality();
               incDays--;
           }else if(!incTimer && theDay==incDays){
               this.type = "blue";
               yellows--;
               this.checkRecovery();
           }
           incDays++;
        }
    }

    public void checkLethality(){
        if(this.type.equals("red")){
            if(incDays == theDay){
                if(Math.random() < General.lethality) {
                    incTimer = true;
                    theDay2 = incDays + (int)(Math.random()*(General.recoveryTime-incDays) + 1);
                }else{
                    theDay2 = General.recoveryTime + 1;
                }
            }else if(incTimer && theDay2 == incDays){
                this.type = "black";
                incTimer = false;
                blacks++;
                reds--;
                this.movement = false;
            }else if(!incTimer && theDay2 == incDays) {
                this.type = "blue";
                reds--;
                this.checkRecovery();
                incDays--;
            }
            incDays++;

        }
    }

    private void checkRecovery(){
        if(this.type.equals("blue")){
                blues++;
                this.movement = true;
        }
    }

    public void setFirstYellow(){ //start virus
        if(this.type.equals("green")){
            this.incTimer = true;
            this.checkInfectivity();
        }
    }

    public boolean test(){ //test (solo per greens and yellows)
        General.resources -= General.swabCost;
        return this.getType().equals("yellow");
    }


    static void resetContatori(){
        greens = 0;
        yellows = 0;
        reds = 0;
        blues = 0;
        blacks = 0;
    }
















}
