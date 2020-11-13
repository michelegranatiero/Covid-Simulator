import javax.swing.*;

public class MyFrame extends JFrame {

    public MyFrame(){
        super("Simulatore Contagi");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);



        MyPanel simulationPanel = new MyPanel();
        this.add(simulationPanel);


        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }


    public static void main(String[] args) {
        MyFrame Frame = new MyFrame();
    }

}
