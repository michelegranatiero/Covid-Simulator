import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class MyFrame extends JFrame {

    static int refreshRate = 15;   //in millisecondi (usare multipli di 5)
    static Timer timer;
    static Color backCol1 = new Color(100, 120, 160);
    static Color backCol2 = new Color(20, 91, 156);
    private final CardLayout cardLayout;
    private final JPanel cardsPanel;


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

        //JButton startButton = new JButton("START");
        //simulationPanel.add(startButton); //a quale pannello?
        //startButton.addActionListener(e -> startButtonClick());


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

    public void startButtonClick(){
        cardLayout.show(cardsPanel,"2");
        //avvia timer...
    }





    public static void main(String[] args) {
        MyFrame Frame = new MyFrame();
    }
}
