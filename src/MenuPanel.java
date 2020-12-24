import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class MenuPanel extends JPanel {

    GridBagConstraints c;
    ArrayList<JLabel> labelList;
    ArrayList<JTextField> textList;


    public MenuPanel(){
        super(new GridBagLayout());
        c = new GridBagConstraints();
        setBorder(BorderFactory.createEmptyBorder(10,50,20,50));
        setPreferredSize(new Dimension(710,510));
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
        c.weighty=1;
        c.gridwidth=4;
        add(labelTitle, c);

        labelList = new ArrayList<>();
        String[] labTexts = {"Popolazione", "N° incontri giornalieri", "Infettività", "Sintomaticità", "Letalità", "Durata Virus (gg)", "***Risorse***", "Strategia"};
        c.gridx = 1;
        c.anchor = GridBagConstraints.CENTER;
        c.gridwidth = 1;
        //c.weightx = 0.5;
        c.insets = new Insets(5,5,5,5);
        genLabelsFromArray(labelList, labTexts, this);


        textList = new ArrayList<>();
        c.gridx=2;
        c.gridy=1;
        c.gridwidth = 1;
        c.insets = new Insets(5,5,5,5);
        genTextFields(textList, this, labTexts.length);

        MyButton buttonClear = new MyButton("Clear");
        c.insets = new Insets(5,5,5,0);
        //c.ipadx=300;
        c.gridx=1;
        c.gridy++;
        //c.insets = new Insets(40,100,5,5);
        add(buttonClear, c);

        MyButton buttonStart = new MyButton("Start");
        c.gridwidth = 2;
        c.gridx=2;
        //c.insets = new Insets(40,5,5,100);
        add(buttonStart, c);


        //PERCENT LABELS
        JLabel percent = new JLabel("%");
        percent.setFont(new Font("Segoe UI", Font.BOLD, 16));
        percent.setVerticalAlignment(JLabel.CENTER);
        percent.setHorizontalAlignment(JLabel.LEFT);
        c.insets = new Insets(0,0,0,0);
        c.gridwidth = 1;
        c.gridx=3;
        c.gridy=3;
        add(percent, c);

        JLabel per2 = new JLabel(percent.getText());
        per2.setFont(percent.getFont());
        per2.setVerticalAlignment(percent.getVerticalAlignment());
        per2.setHorizontalAlignment(percent.getHorizontalAlignment());
        c.gridy++;
        add(per2, c);

        JLabel per3 = new JLabel(percent.getText());
        per3.setFont(percent.getFont());
        per3.setVerticalAlignment(percent.getVerticalAlignment());
        per3.setHorizontalAlignment(percent.getHorizontalAlignment());
        c.gridy++;
        add(per3, c);







        //ACTION LISTENERS
        buttonStart.addActionListener(e->buttonStartClick(textList));   //START BUTTON
        buttonClear.addActionListener(e -> buttonClearClick(textList));

    }

    public void buttonStartClick(ArrayList<JTextField> texts){

        General.initPopulation = Integer.parseInt(texts.get(0).getText());
        General.population = General.initPopulation;    //UPDATE
        General.velocity = Integer.parseInt(texts.get(1).getText());
        General.infectivity = Double.parseDouble(texts.get(2).getText())/100;
        General.symptomaticity = Double.parseDouble(texts.get(3).getText())/100;
        General.lethality = Double.parseDouble(texts.get(4).getText())/100;
        //General.recoveryTime = Integer.parseInt(texts.get(5).getText());
        //General.resources = Integer.parseInt(texts.get(6).getText());
        General.strategy = Integer.parseInt(texts.get(7).getText());
        //BOTTOM
        MyFrame.card2Creator();
    }

    public void buttonClearClick(ArrayList<JTextField> ctl){
        for(JTextField ct: ctl){
            ct.setText("");
        }
    }

    public void genLabelsFromArray(ArrayList<JLabel> a, String[] texts, JPanel panel){
        for (String text : texts) {
            JLabel l = new JLabel(text);
            //l.setBorder(BorderFactory.createStrokeBorder(new BasicStroke()));   //BORDO PROVA
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
            t.setPreferredSize(new Dimension(100, 5));
            t.setMinimumSize(new Dimension(50,5));
            t.setFont(new Font("Segoe UI", Font.BOLD, 16));
            t.setHorizontalAlignment(SwingConstants.CENTER);
            t.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(MyFrame.backCol2, 1), BorderFactory.createEmptyBorder()));
            a.add(t);
            panel.add(t, c);
            c.gridy++;
        }
    }



    public void theFilter(int i, JTextField t){
        switch (i){
            case 0: //Popolazione
                t.setText(General.initPopulation +"");
                t.setCaretPosition(t.getText().length());
                ((AbstractDocument)t.getDocument()).setDocumentFilter(new PopulationDocFilter());
                break;
            case 1: // Numero Incontri Giornalieri (velocity)
                t.setText(General.velocity+"");
                //t.setEnabled(false);
                //t.setBackground(MyFrame.backCol1);
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
                break;
            case 6: // Risorse
                t.setText(General.resources+"");
                t.setEnabled(false);
                t.setBackground(MyFrame.backCol1);
                break;
            case 7: //Strategia
                t.setText(General.strategy+"");
                break;
        }
    }




}
