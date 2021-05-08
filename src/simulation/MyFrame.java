package simulation;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class MyFrame extends JFrame {

    ImageIcon logo = new ImageIcon(".//res//covid.png");

    static int refreshRate = 20;   //in millisecondi (usare multipli di 5)
    Timer timer;
    static Color backCol1 = new Color(100-10, 120-10, 160-10);
    static Color backCol2 = new Color(20, 91, 156);
    CardLayout cardLayout;
    JPanel cardsPanel;
    MyButton pauseButton;
    MyButton exitButton;
    JPanel card2;
    GraphPanel graph;
    ActionListener actLis;
    int numDays = 0;
    int population = General.initPopulation;  // numero di persone esistenti all'inizio della simulazione


    public MyFrame(){
        super("Simulatore Covid");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        setIconImage(logo.getImage());

        //container
        cardLayout = new CardLayout();
        cardsPanel = new JPanel(cardLayout);
        setContentPane(cardsPanel);

        //Menù - Card1 Panel
        MenuPanel card1 = new MenuPanel(this);
        cardsPanel.add(card1, "1");
        this.setMinimumSize(new Dimension(600, 400));

        cardLayout.show(cardsPanel,"1");

        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }

    public void card2Creator(){

        //Simulation - Card2 Panel
        card2 = new JPanel(new BorderLayout());
        cardsPanel.add(card2, "2");
        //card2.setLayout(new BoxLayout(card2, BoxLayout.Y_AXIS));

        //topContainer in Card2
        JPanel topContainer = new JPanel();
        topContainer.setLayout(new BoxLayout(topContainer, BoxLayout.X_AXIS));
        topContainer.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        topContainer.setBackground(backCol1);
        card2.add(topContainer, BorderLayout.NORTH);

        //Panels in top Container
        graph = new GraphPanel(this);
        topContainer.add(graph);

        StatsPanel stats = new StatsPanel(this);
        topContainer.add(stats);

        //simulationPanel in Card2
        MyPanel simulationPanel = new MyPanel(this, numDays);
        card2.add(simulationPanel, BorderLayout.CENTER);

        //botPanel in Card2
        JPanel botPanel = new JPanel(new BorderLayout());
        card2.add(botPanel, BorderLayout.SOUTH);
        botPanel.setBackground(backCol2);
        pauseButton = new MyButton("PAUSE");
        pauseButton.setPreferredSize(new Dimension(200, 35));
        botPanel.add(pauseButton, BorderLayout.CENTER);
        exitButton = new MyButton("EXIT");
        exitButton.setPreferredSize(new Dimension(200, 35));
        botPanel.add(exitButton, BorderLayout.EAST);///////////////////////////////

        //ACTION LISTENERS
        pauseButton.addActionListener(e -> pauseButtonClick());
        exitButton.addActionListener(e -> exitButtonClick());

        //Timer
        actLis = e -> updateInvoker(simulationPanel, graph, stats);
        timer = new Timer(refreshRate, actLis);


        JFrame f1 = (JFrame) SwingUtilities.getWindowAncestor(cardsPanel);
        f1.pack();
        f1.setLocationRelativeTo(null);
        f1.setResizable(false);
        cardLayout.show(cardsPanel, "2");
        this.timer.start();
    }



    //refresh del simulatore
    public static void updateInvoker(MyPanel p1, GraphPanel p2, StatsPanel p3){
        p1.repaint();
        p2.repaint();
        p3.updateLabels();
    }

    //azione del pulsante STOP/RESUME
    private boolean isPlaying = true;
    public void pauseButtonClick(){
        if(isPlaying){
            timer.stop();
            isPlaying = false;
            pauseButton.setText("RESUME");
        }else{
            timer.start();
            isPlaying = true;
            pauseButton.setText("PAUSE");
        }
    }

    //disabilitare il pulsante STOP alla fine della simulazione
    public void disabler(String s){
        pauseButton.setEnabled(false);
        pauseButton.setText(s);
    }

    public void exitButtonClick(){
        dispose();
        Person.resetContatori();
        General.resources = General.resMax;
        MyFrame frame = new MyFrame();
        frame.setVisible(true);
    }

    public void plusOneNumDays(){
        numDays++;
    }

    public int getNumDays(){
        return numDays;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }



    public static void main(String[] args) {
        new MyFrame();

    }
}
