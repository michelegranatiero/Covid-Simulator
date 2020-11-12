import java.awt.*;

public class Person {
    int ovalDim = 10;
    int x,y;
    int vx, vy;
    int status = 0;
    int recoveryTime = 50 *1000; //in milliseconds
    static int numInfected = 0;





    public Person(){
        x = (int)(Math.random()*(MyPanel.frameWidth - ovalDim));
        y = (int)(Math.random()*(MyPanel.frameHeight - ovalDim));


        //infected at start
        if (Math.random()<0.05){    //make 5% of the people infected at start
            status = 1;
            numInfected++;
        }

        //people that can move
        if (Math.random() < 1){    //everybody
            vx = (int)(Math.random()*(10+1)+-5);
            vy = (int)(Math.random()*(10+1)+-5);
        }

        //randomize how long it takes to be recovered
        //recoveryTime = (int)(Math.random()*(7000-5000+1)+5000); //5-7 seconds (in milliseconds)
    }






    public void paint(Graphics g){
        //set the color of the Person object based on the health status
        switch(status) {
            case 0: //normal
                g.setColor(Color.LIGHT_GRAY);
                break;
            case 1: //infected
                g.setColor(Color.red);
                break;
            case 2: //recovered
                g.setColor(Color.blue);
        }

        if(status == 1){

            //recoveryTime update
            recoveryTime -=16;

            //Person recovered
            if (recoveryTime<= 0){
                status = 2;
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
            if(this.status == 1 && p.status == 0){
                p.status = 1;
                numInfected++;
            }else if(this.status == 0 && p.status == 1){
                this.status = 1;
                numInfected++;
            }
        }
    }
























}
