import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Menu extends JPanel {

    GridBagConstraints c;
    ArrayList<JLabel> labelList;
    ArrayList<JTextField> textList;
    static MyButton buttonClear;
    static MyButton buttonStart;




    public Menu(){
        super(new GridBagLayout());
        c = new GridBagConstraints();
        setBorder(BorderFactory.createEmptyBorder(20,100,20,100));
        setPreferredSize(new Dimension(700,500));
        setBackground(MyFrame.backCol1);


        JLabel labelTitle = new JLabel("SIMULATOR");
        labelTitle.setForeground(Color.white);
        labelTitle.setHorizontalAlignment(JLabel.CENTER);
        c.insets = new Insets(0,0,0,0);
        c.gridx=0;
        c.gridy=0;
        c.weightx=1;
        c.weighty=1;
        c.gridwidth=2;
        add(labelTitle, c);

        labelList = new ArrayList<>();
        String[] lnames = {"Popolazione", "Numero incontri giornalieri", "Infettività", "Sintomaticità", "Letalità", "Durata Virus", "Costo Tampone (risorse)", "***Risorse***"};
        c.gridwidth=1;
        c.insets = new Insets(5,100,5,5);
        genVerticalLabelsFromArray(labelList, lnames, this);

        textList = new ArrayList<>();
        c.gridx=1;
        c.gridy=0;
        c.ipadx = 100;
        c.insets = new Insets(5,5,5,100);
        genVerticalCustomTextFromArray(textList, this, lnames.length);

        buttonClear = new MyButton("Clear");
        c.ipadx=300;
        c.gridx=0;
        c.gridy++;
        c.insets = new Insets(40,100,5,5);
        add(buttonClear, c);

        buttonStart = new MyButton("Start");
        c.gridx=1;
        c.insets = new Insets(40,5,5,100);
        add(buttonStart, c);

        c.fill = GridBagConstraints.BOTH;

        buttonStart.addActionListener(e->MyFrame.card2Creator());

    }

    public void genVerticalLabelsFromArray(ArrayList<JLabel> a, String[] names, JPanel panel){
        for (String name : names) {
            JLabel l = new JLabel(name);
            l.setVerticalAlignment(JLabel.CENTER);
            l.setHorizontalAlignment(JLabel.LEFT);
            a.add(l);
            c.gridy++;
            panel.add(l, c);
        }
    }

    public void genVerticalCustomTextFromArray(ArrayList<JTextField> a, JPanel panel, int iterations) {
        for(int i = 0; i<iterations; i++ ){
            JTextField l = new JTextField();
            a.add(l);
            c.gridy++;
            panel.add(l, c);

        }
    }


}
