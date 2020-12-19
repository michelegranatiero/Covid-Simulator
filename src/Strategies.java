import java.util.ArrayList;

public class Strategies {

    private int test = 0;
    private int block = 0;
    private ArrayList<Person> people;

    public Strategies( /*parametro*/ ){

    }

    public void testACampione(){
        int sample = people.size()/4;
        ArrayList<Person> sampleArray = new ArrayList<>();
        for( int i = 0; i < sample; i++){
            int r = (int)(Math.random()*people.size());
            sampleArray.add(people.remove(r));
        }
        //se è rosso lo fermo a prescindere (for p in rossi)
    }


}
