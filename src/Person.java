import java.awt.*;
import java.util.ArrayList;

public class Person {
    private final int ovalDim = 10;
    static Color myGreen = new Color(0, 180, 51);
    static Color myYellow = new Color(255, 153, 0);
    static Color myRed = new Color(140,0,0);
    static Color myBlue = new Color(50,170,255);

    private int x,y;
    private int vx, vy;
    private String type;   // colore

    private boolean movement = true;     //movimento si/no

    private boolean incTimer = false;    //incubation timer on/off
    private int incDays = 0;
    private int theDay;                 //chosen day for checkSymptomaticity
    private int theDay2;                 //chosen day for checkLethality

    //private boolean recTimer = false;    //recoverytimer on/off
    //private int recDays = 0;

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

        //Movement
        vx = (int)(Math.random()*General.speed + 1)*(Math.random()<0.5?1:-1);
        vy = (int)(Math.random()*General.speed + 1)*(Math.random()<0.5?1:-1);

    }






    public void paint(Graphics g){
        //set the color of the Person object based on the health status
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
                g.setColor(Color.black);
                break;
        }



        //x and y are updated by their velocities
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
    }



    public void collision(Person p){

        //Represent Person as Rectangle for simple collision detection
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
                //recTimer = false;
                //recDays = 0;
        }
    }

    public void setYellowFromGreen(){
        if(this.type.equals("green")){
            this.incTimer = true;
        }
    }

    public boolean test(){ //only for greens and yellows
        General.resources -= General.swabCost;

        return this.getType().equals("yellow");
    }





















}
