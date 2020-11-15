import java.awt.*;
import java.util.ArrayList;

public class Person {
    private int ovalDim = 10;
    private int x,y;
    private int vx, vy;
    private String type = "green";   // colore
    private boolean movement = true;     //movimento si/no

    private boolean incTimer = false;    //incubation timer on/off
    private int incDays = 0;

    private boolean recTimer = false;    //recoverytimer on/off
    private int recDays = 0;

    private ArrayList<Person> meetings= new ArrayList<>();    //incontri di ciascun individuo in un giorno
    private int recoveryTime = 5 *1000; //in milliseconds

    static int numInfected = 0;

    //aspetti sanitari





    public Person(){
        x = (int)(Math.random()*(MyPanel.frameWidth - ovalDim));
        y = (int)(Math.random()*(MyPanel.frameHeight - ovalDim));

        //people that can move
        if (movement){    //everybody
            vx = (int)((Math.random()*5)+1)*(Math.random()<0.5?1:-1);
            vy = (int)((Math.random()*5)+1)*(Math.random()<0.5?1:-1);
        }

        //randomize how long it takes to be recovered
        //recoveryTime = (int)(Math.random()*(7000-5000+1)+5000); //5-7 seconds (in milliseconds)
    }






    public void paint(Graphics g){
        //set the color of the Person object based on the health status
        switch(type) {
            case "green": //normal
                g.setColor(Color.green);
                break;
            case "yellow": //infected no symptoms
                g.setColor(Color.orange);
                break;
            case "red": //infected with symptoms
                g.setColor(Color.red);
                break;
            case "blue": //recovered
                g.setColor(Color.blue);
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
                    this.recTimer = true;
                }
            }else if((this.type.equals("yellow") || this.type.equals("red")) && p.type.equals("green")){
                if(Math.random() < General.infectivity){
                    p.incTimer = true;
                    p.recTimer = true;
                }
            }
        }
    }

    public void strategy1(){
        switch (type){
            case "green":

                break;
            case "yellow":

                break;
            case "red":

                break;
            case "blue":

                break;
            case "black":

                break;

        }
    }

    public String getType(){
        return this.type;
    }

    public void setType(String s){
        this.type = s;
    }

    public void resetMeetings(){
        meetings.clear();
    }

    public ArrayList<Person> getMeetings(){
        return meetings;
    }

    public void checkInfectivity(){
        if(incTimer){
            if(incDays == General.incubationPeriod && this.type.equals("green")){
                this.type = "yellow";
                incTimer = false;
                //incDays = 0;
            }else{
                incDays++;
            }
        }
    }

    public void checkSymptomaticity(){
        if(this.type.equals("yellow")){
            if(Math.random() < General.symptomaticity){
                this.type = "red";
            }
        }
    }

    public void checkLethality(){
        if(this.type.equals("red")){
            if(Math.random() < General.lethality){
                this.type = "black";
                this.movement = false;
            }
        }
    }

    public void checkRecovery(){
        if(recTimer){
            if (recDays == General.recoveryTime && (this.type.equals("red") || this.type.equals("yellow"))) {
                this.type = "blue";
                recTimer = false;
                //recDays = 0;
            } else {
                recDays++;
            }
        }
    }

    public void setYellow(){
        this.type = "yellow";
        this.recTimer = true;
        this.incTimer = true;
    }
























}
