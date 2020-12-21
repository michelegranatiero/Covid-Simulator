import java.util.ArrayList;

public class Strategies {

    private static int cadence = 7;
    private static int count = 0;
    //private static boolean countSwitch = false;

    private ArrayList<Person> people; //only greens and yellows

    public Strategies( ArrayList<Person> arr, int days){
        if(count == 0 || (days - count) >= cadence){
            people = arr;
            count = days;
            switch(General.strategy){
                case 0:
                    testACampione();
                    break;
                case 1:
                    //
                    break;
            }
        }
    }

    public void testACampione(){
        int sample = General.population/8; //dimensione del campione
        ArrayList<Person> sampleArray = new ArrayList<>();

        while(sampleArray.size() < sample && sampleArray.size() < people.size()){
            int r = (int)(Math.random()*people.size());
            sampleArray.add(people.remove(r));
        }

        for(Person p : sampleArray){
            if(p.test()){
                p.setMovement(false);
            }
        }

    }


}
