package simulation;

public class Point {
    private int time, value, day;

    public Point(int t, int v){
        time = t;
        value = v;
        day = MyPanel.numDays;

    }


    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getDay() {
        return day;
    }



}
