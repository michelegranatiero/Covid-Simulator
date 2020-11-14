import java.awt.*;
import java.util.ArrayList;

public class Person {
    int ovalDim = 10;
    int x,y;
    int vx, vy;
    String type;   // colore
    int status = 1;     //movimento si/no
    ArrayList<Person> meetings= new ArrayList<>();    //incontri di ciascun individuo in un giorno
    int recoveryTime = 5 *1000; //in milliseconds

    static int numInfected = 0;

    //aspetti sanitari





    public Person(){
        x = (int)(Math.random()*(MyPanel.frameWidth - ovalDim));
        y = (int)(Math.random()*(MyPanel.frameHeight - ovalDim));


        //infected at start
        if (Math.random()<0.05){    //make 5% of the people infected at start
            type = "yellow";
            numInfected++;
        }

        //people that can move
        if (status == 1){    //everybody
            vx = (int)(Math.random()*(10+1)+-5);
            vy = (int)(Math.random()*(10+1)+-5);
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
            case "yellow": //infected
                g.setColor(Color.yellow);
                break;
            case "blue": //recovered
                g.setColor(Color.blue);
        }

        if(type == "yellow"){

            //recoveryTime update
            recoveryTime -=16;

            //Person recovered
            if (recoveryTime<= 0){
                type = "blue";
                numInfected--;
            }
        }

        //x and y are updated by their velocities
        x += vx;
        y += vy;

        //border bounce
        if(x<0 || x>=(MyPanel.frameWidth - ovalDim)){
            vx *= -1;
        }
        if(y<0 || y>=(MyPanel.frameHeight - ovalDim)){
            vy *= -1;
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

            if(this.type == "green" && p.type == "yellow"){

            }else if(this.type == "yellow" && p.type == "green"){
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

    public void resetMeetings(){
        meetings.clear();
    }
























}
