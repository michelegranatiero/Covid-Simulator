import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class MyFrame extends JFrame {

    static int refreshRate = 15;   //in millisecondi (usare multipli di 5)
    static Timer timer;
    static Color backCol1 = new Color(100, 120, 160);
    static Color backCol2 = new Color(20, 91, 156);
    static CardLayout cardLayout;
    static JPanel cardsPanel;
    static MyButton stopButton;


    public MyFrame(){
        super("Simulatore Covid");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        //container
        cardsPanel = new JPanel();
        setContentPane(cardsPanel);
        cardLayout = new CardLayout();
        cardsPanel.setLayout(cardLayout);

        //Menù - Card1 Panel
        Menu card1 = new Menu();
        cardsPanel.add(card1, "1");

        //Simulation - Card2 Panel
        JPanel card2 = new JPanel();
        cardsPanel.add(card2, "2");
        card2.setLayout(new BoxLayout(card2, BoxLayout.Y_AXIS));

        //topContainer in Card2
        JPanel topContainer = new JPanel();
        topContainer.setLayout(new BoxLayout(topContainer, BoxLayout.X_AXIS));
        topContainer.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        topContainer.setBackground(Color.gray);
        card2.add(topContainer);

        //Panels in top Container
        GraphPanel graph = new GraphPanel();
        topContainer.add(graph);

        StatsPanel stats = new StatsPanel();
        topContainer.add(stats);

        //simulationPanel in Card2
        MyPanel simulationPanel = new MyPanel();
        card2.add(simulationPanel);

        //botPanel in Card2
        JPanel botPanel = new JPanel();
        card2.add(botPanel);
        botPanel.setBackground(backCol2);
        stopButton = new MyButton("STOP");
        stopButton.setPreferredSize(new Dimension(88, 25));
        botPanel.add(stopButton);



        //JButton startButton = new JButton("START");
        //simulationPanel.add(startButton); //a quale pannello?
        stopButton.addActionListener(e -> stopButtonClick());


        //Timer
        ActionListener actLis = e -> repaintInvoker(simulationPanel, graph, stats);
        timer = new Timer(refreshRate, actLis);
        timer.start();

        //Card shown at start
        cardLayout.show(cardsPanel,"2");

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

    boolean isPlaying = true;
    public void stopButtonClick(){
        if(isPlaying){
            timer.stop();
            isPlaying = false;
            stopButton.setText("RESUME");
        }else{
            timer.start();
            isPlaying = true;
            stopButton.setText("STOP");
        }
    }

    static void disabler(){
        stopButton.setEnabled(false);
    }








    public static void main(String[] args) {
        MyFrame Frame = new MyFrame();

    }
}
