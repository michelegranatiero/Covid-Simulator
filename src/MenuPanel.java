import javax.swing.*;
import javax.swing.text.AbstractDocument;
import java.awt.*;
import java.util.ArrayList;

public class MenuPanel extends JPanel {

    GridBagConstraints c;
    ArrayList<JLabel> labelList;
    ArrayList<JTextField> textList;
    static MyButton buttonClear;
    static MyButton buttonStart;




    public MenuPanel(){
        super(new GridBagLayout());
        c = new GridBagConstraints();
        setBorder(BorderFactory.createEmptyBorder(10,50,20,50));
        setPreferredSize(new Dimension(700,500));
        setBackground(MyFrame.backCol1);
        c.fill = GridBagConstraints.BOTH;

        JLabel labelTitle = new JLabel("SIMULATOR");
        labelTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        labelTitle.setBorder(BorderFactory.createEmptyBorder(20,0,20,0));
        labelTitle.setForeground(Color.white);
        labelTitle.setHorizontalAlignment(JLabel.CENTER);
        c.insets = new Insets(0,0,0,0);
        c.gridx=0;
        c.gridy=0;
        c.weightx=1;
        c.weighty=1;
        c.gridwidth=4;
        add(labelTitle, c);

        labelList = new ArrayList<>();
        String[] labTexts = {"Popolazione", "N° incontri giornalieri", "Infettività", "Sintomaticità", "Letalità", "Durata Virus", "***Risorse***"};
        c.gridwidth=1;
        c.insets = new Insets(5,100,5,5);
        genLabelsFromArray(labelList, labTexts, this);


        textList = new ArrayList<>();
        c.gridx=1;
        c.gridy=1;
        c.ipadx = 100;
        c.insets = new Insets(5,5,5,100);
        genTextFields(textList, this, labTexts.length);

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



        //ACTION LISTENERS
        buttonStart.addActionListener(e->MyFrame.card2Creator());   //START BUTTON
        buttonClear.addActionListener(e -> buttonClearClick(textList));

    }

    public void buttonClearClick(ArrayList<JTextField> ctl){
        for(JTextField ct: ctl){
            ct.setText("");
        }
    }

    public void genLabelsFromArray(ArrayList<JLabel> a, String[] texts, JPanel panel){
        for (String text : texts) {
            JLabel l = new JLabel(text);
            l.setFont(new Font("Segoe UI", Font.BOLD, 16));
            l.setVerticalAlignment(JLabel.CENTER);
            l.setHorizontalAlignment(JLabel.LEFT);
            a.add(l);
            c.gridy++;
            panel.add(l, c);
        }
    }

    public void genTextFields(ArrayList<JTextField> a, JPanel panel, int iterations) {
        for(int i = 0; i<iterations; i++ ){
            JTextField t = new JTextField();
            ((AbstractDocument)t.getDocument()).setDocumentFilter(new NumberDocFilter());
            theFilter(i, t);
            t.setFont(new Font("Segoe UI", Font.BOLD, 16));
            t.setHorizontalAlignment(SwingConstants.CENTER);
            t.setBorder(BorderFactory.createEtchedBorder(MyFrame.backCol2, MyFrame.backCol2));
            a.add(t);
            panel.add(t, c);
            c.gridy++;
        }
    }



    public void theFilter(int i, JTextField t){
        switch (i){
            case 0: //Popolazione
                t.setText(General.population +"");
                t.setCaretPosition(t.getText().length());

                break;
            case 1: // Numero Incontri Giornalieri (velocity)
                t.setText(General.velocity+"");
                t.setEnabled(false);
                t.setBackground(MyFrame.backCol1);
                break;
            case 2:
                t.setText((int)(General.symptomaticity*100)+"");
                break;
            case 3:
                t.setText((int)(General.infectivity*100)+"");
                break;
            case 4:
                t.setText((int)(General.lethality*100)+"");
                break;
            case 5:
                t.setText(General.recoveryTime+"");
            case 6: // Risorse
                t.setEnabled(false);
                t.setBackground(MyFrame.backCol1);
                break;
        }
    }




}
