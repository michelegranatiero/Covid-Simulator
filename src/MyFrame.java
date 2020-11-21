import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class MyFrame extends JFrame {

    static int refreshRate = 10;   //in millisecondi (usare multipli di 5)

    private ActionListener actLis;
    static Timer timer;



    public MyFrame(){
        super("Simulatore Covid");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        JPanel topContainer = new JPanel();
        topContainer.setLayout(new BoxLayout(topContainer, BoxLayout.X_AXIS));
        topContainer.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));

        topContainer.setBackground(Color.gray);
        this.add(topContainer);

        GraphPanel graph = new GraphPanel();
        topContainer.add(graph);

        StatsPanel stats = new StatsPanel();
        topContainer.add(stats);



        //Outside topContainer
        //this.add(Box.createRigidArea(new Dimension(4,5)));

        MyPanel simulationPanel = new MyPanel();
        this.add(simulationPanel);




        actLis = e -> repaintInvoker(simulationPanel, graph, stats);
        timer = new Timer(refreshRate, actLis);
        timer.start();




        this.pack();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
    }

    public void repaintInvoker(MyPanel p1, GraphPanel p2, StatsPanel p3){
        p1.repaint();
        p2.repaint();
        p3.updateLabels();
    }


    public static void main(String[] args) {
        MyFrame Frame = new MyFrame();
    }

}
