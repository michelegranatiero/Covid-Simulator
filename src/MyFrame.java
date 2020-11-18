import javax.swing.*;
import java.awt.event.ActionListener;

public class MyFrame extends JFrame {

    static int refreshRate = 10;   //in millisecondi (usare multipli di 5)

    private ActionListener actLis;
    static Timer timer;



    public MyFrame(){
        super("Simulatore Covid");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        GraphPanel graph = new GraphPanel();
        this.add(graph);

        MyPanel simulationPanel = new MyPanel();
        this.add(simulationPanel);




        actLis = e -> repaintInvoker(simulationPanel, graph);
        timer = new Timer(refreshRate, actLis);
        timer.start();




        this.pack();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
    }

    public void repaintInvoker(JPanel p1, JPanel p2){
        p1.repaint();
        p2.repaint();
    }


    public static void main(String[] args) {
        MyFrame Frame = new MyFrame();
    }

}
