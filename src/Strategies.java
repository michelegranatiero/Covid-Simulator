import java.util.ArrayList;

public class Strategies {

    private static int cadence = 7;
    private static int count = 0;
    private static boolean switcher = true;

    private ArrayList<Person> canBeTested; //only greens and yellows
    private ArrayList<Person> redsArray; //

    public Strategies( ArrayList<Person> cbt, ArrayList<Person> reds,int days){
        if(count == 0 || (days - count) >= cadence){
            canBeTested = cbt;
            redsArray = reds;
            count = days;
            switch(General.strategy){
                case 0:     //immunità di gregge
                    break;
                case 1:
                    testACampione();
                    break;
                case 2:
                    testMeetings();
                    break;
                case 3:
                    if(switcher){
                        stopHalfPop();
                        switcher = false;
                    }
            }
        }
    }

    public void testACampione(){
        int sample = General.population/8; //dimensione del campione
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
        if(canBeTested.size()<=General.population/2){
            for(Person p: canBeTested){
                p.setMovement(false);
            }
        }else{
            for(int i = 0; i < General.population/2; i++){
                canBeTested.get(i).setMovement(false);
            }
        }
    }

}
