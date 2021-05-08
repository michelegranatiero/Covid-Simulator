package simulation;

import java.util.ArrayList;

public class Strategies {

    private final int cadence = 1;
    private int count = 0;
    private boolean switcher = true;
    MyFrame frame;

    private ArrayList<Person> canBeTested; //only greens and yellows
    private ArrayList<Person> redsArray;
    static String[] sNames = {"Immunità di Gregge", "Test a Campione", "Traccia Sintomatici", "Metà in Lockdown"};

    public Strategies(MyFrame myframe){
        frame = myframe;

    }

    public void startStrategy(ArrayList<Person> cbt, ArrayList<Person> reds, int days){
        if(count == 0 || (days - count) >= cadence){
            canBeTested = cbt;
            redsArray = reds;
            count = days;
            switch(General.strategy){
                case 0:     //immunità di gregge
                    break;
                case 1:     //a campione
                    testACampione();
                    break;
                case 2:     //in base agli incontri giornalieri
                    testMeetings();
                    break;
                case 3:     //fermo mezza popolazione
                    if(switcher){
                        stopHalfPop();
                        switcher = false;
                    }
                    break;
            }
        }
    }

    public void testACampione(){
        int sample = frame.getPopulation()/8; //dimensione del campione
        ArrayList<Person> sampleArray = new ArrayList<>();

        while(sampleArray.size() < sample && sampleArray.size() < canBeTested.size()){
            int r = (int)(Math.random()* canBeTested.size());
            sampleArray.add(canBeTested.remove(r));
        }

        for(Person p : sampleArray){
            if(p.test()){
                p.setMovement(false);
            }
        }

    }

    public void testMeetings(){ //entrati in contatto con un sintomatico quel giorno
        ArrayList<Person> testArr= new ArrayList<>();
        for(Person p1: redsArray){
            for(Person p2: p1.getMeetings()){
                if(!testArr.contains(p2) && canBeTested.contains(p2)){
                    testArr.add(p2);
                }
            }
        }
        for(Person p: testArr){
            if(p.test()){
                p.setMovement(false);
            }
        }
    }

    public void stopHalfPop(){
        if(canBeTested.size()<=frame.getPopulation()/2){
            for(Person p: canBeTested){
                p.setMovement(false);
            }
        }else{
            for(int i = 0; i < frame.getPopulation()/2; i++){
                canBeTested.get(i).setMovement(false);
            }
        }
    }
}
