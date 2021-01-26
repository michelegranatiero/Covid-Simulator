package simulation;

import docFilters.*;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;

public class MenuPanel extends JPanel {

    private final GridBagConstraints c;
    static ArrayList<JLabel> labelList;
    static ArrayList<JTextField> textList;
    private JComboBox<String> cb;
    static JLabel resMax;


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

        //genero una JLabel per ogni parametro
        labelList = new ArrayList<>();
        String[] labTexts = {"Popolazione", "N° incontri giornalieri", "Infettività", "Sintomaticità", "Letalità", "Durata Virus (gg)", "Risorse", "Strategia"};
        c.gridx = 1;
        c.anchor = GridBagConstraints.CENTER;
        c.gridwidth = 1;
        c.insets = new Insets(5,5,5,5);
        genLabelsFromArray(labelList, labTexts, this);

        //genero un JTextField per ogni parametro
        textList = new ArrayList<>();
        c.gridx=2;
        c.gridy=1;
        c.gridwidth = 1;
        c.insets = new Insets(5,5,5,5);
        genTextFields(textList, this, labTexts.length);

        //Pulsante Clear
        MyButton buttonClear = new MyButton("Clear");
        c.insets = new Insets(5,5,5,0);
        c.gridx=1;
        c.gridy++;
        add(buttonClear, c);

        //Pulsante Start
        MyButton buttonStart = new MyButton("Start");
        c.gridwidth = 2;
        c.gridx=2;
        add(buttonStart, c);


        //Labels percentuali
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

        //Label risorse massime
        resMax = new JLabel(General.resMax+" max");
        resMax.setFont(new Font("Segoe Condensed", Font.PLAIN, 12));
        resMax.setVerticalAlignment(percent.getVerticalAlignment());
        resMax.setHorizontalAlignment(percent.getHorizontalAlignment());
        c.gridy = 7;
        add(resMax, c);

        //ACTION LISTENERS PULSANTI
        buttonStart.addActionListener(e->buttonStartClick(textList));   //START BUTTON
        buttonClear.addActionListener(e -> buttonClearClick(textList)); //CLEAR BUTTON

    }

    public void buttonStartClick(ArrayList<JTextField> texts){

        try{    //imposto i parametri
            General.initPopulation = Integer.parseInt(texts.get(0).getText());
            General.population = General.initPopulation;    //UPDATE
            General.velocity = Integer.parseInt(texts.get(1).getText());
            General.infectivity = Double.parseDouble(texts.get(2).getText())/100;
            General.symptomaticity = Double.parseDouble(texts.get(3).getText())/100;
            General.lethality = Double.parseDouble(texts.get(4).getText())/100;
            General.recoveryTime = Integer.parseInt(texts.get(5).getText());
            General.resources = Integer.parseInt(texts.get(6).getText());
            General.strategy = cb.getSelectedIndex();
            //BOTTOM
            MyFrame.card2Creator(); //Genero la simulazione
        }catch(Exception e){
            System.out.println("Something went wrong");
        }

    }

    public void buttonClearClick(ArrayList<JTextField> ctl){
        for(JTextField ct: ctl){
            ct.setText(""); //svuoto tutti i JTextField
        }
    }

    //genero le JLabels da un array
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

    //genero i JTextField da un array
    public void genTextFields(ArrayList<JTextField> a, JPanel panel, int iterations) {
        for(int i = 0; i<iterations; i++ ){
            if(i == 7){     //genero l'unico combo box del menù per la strategia
                cb = new JComboBox<>(Strategies.sNames);
                cb.setBackground(Color.white);
                cb.setPreferredSize(new Dimension(50,5));
                cb.setFocusable(false);
                cb.setFont(new Font("Segoe Condensed", Font.PLAIN, 14));
                c.insets = new Insets(5,5,5,0);
                c.gridwidth = 2;
                panel.add(cb, c);
                c.gridwidth = 1;
                c.gridy++;
                break;
            }   //genero i textfield
            JTextField t = new JTextField();
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


    //valori iniziali JtextField e filtri per il testo
    static void theFilter(int i, JTextField t){
        switch (i){
            case 0: //Popolazione
                t.setText(General.initPopulation +"");
                t.setCaretPosition(t.getText().length());
                ((AbstractDocument)t.getDocument()).setDocumentFilter(new PopulationDocFilter());
                t.addFocusListener(new FocusAdapter() {
                    @Override
                    public void focusLost(FocusEvent e) {
                        General.updateRes();
                        MenuPanel.resMax.setText(General.resMax+" max");
                        ((AbstractDocument) textList.get(6).getDocument()).setDocumentFilter(new NumberDocFilter());
                        textList.get(6).setText(General.resources+"");
                        ((AbstractDocument)textList.get(6).getDocument()).setDocumentFilter(new ResourcesDocFilter());
                    }

                    @Override
                    public void focusGained(FocusEvent e) {
                        General.updateRes();
                        MenuPanel.resMax.setText(General.resMax+" max");
                        ((AbstractDocument) textList.get(6).getDocument()).setDocumentFilter(new NumberDocFilter());
                        textList.get(6).setText(General.resources+"");
                        ((AbstractDocument)textList.get(6).getDocument()).setDocumentFilter(new ResourcesDocFilter());
                    }

                });
                break;
            case 1: //Numero Incontri Giornalieri (velocity)
                t.setText(General.velocity+"");
                ((AbstractDocument)t.getDocument()).setDocumentFilter(new VelocityDocFilter());
                break;
            case 2: //Infettività
                t.setText((int)(General.infectivity*100)+"");
                ((AbstractDocument)t.getDocument()).setDocumentFilter(new PercentDocFilter());
                break;
            case 3: //Sintomaticità
                t.setText((int)(General.symptomaticity*100)+"");
                ((AbstractDocument)t.getDocument()).setDocumentFilter(new PercentDocFilter());
                break;
            case 4: //Letalità
                t.setText((int)(General.lethality*100)+"");
                ((AbstractDocument)t.getDocument()).setDocumentFilter(new PercentDocFilter());
                break;
            case 5: //Durata Virus
                t.setText(General.recoveryTime+"");
                ((AbstractDocument)t.getDocument()).setDocumentFilter(new RecoveryDocFilter());
                t.addFocusListener(new FocusAdapter() {
                    @Override
                    public void focusLost(FocusEvent e) {
                        General.updateRes();
                        MenuPanel.resMax.setText(General.resMax+" max");
                        ((AbstractDocument) textList.get(6).getDocument()).setDocumentFilter(new NumberDocFilter());
                        textList.get(6).setText(General.resources+"");
                        ((AbstractDocument)textList.get(6).getDocument()).setDocumentFilter(new ResourcesDocFilter());
                    }

                    @Override
                    public void focusGained(FocusEvent e) {
                        General.updateRes();
                        MenuPanel.resMax.setText(General.resMax+" max");
                        ((AbstractDocument) textList.get(6).getDocument()).setDocumentFilter(new NumberDocFilter());
                        textList.get(6).setText(General.resources+"");
                        ((AbstractDocument)textList.get(6).getDocument()).setDocumentFilter(new ResourcesDocFilter());
                    }
                });
                break;
            case 6: //Risorse
                t.setText(General.resources+"");
                ((AbstractDocument)t.getDocument()).setDocumentFilter(new ResourcesDocFilter());
                t.addFocusListener(new FocusAdapter() {
                    @Override
                    public void focusLost(FocusEvent e) {
                        ((AbstractDocument) t.getDocument()).setDocumentFilter(new NumberDocFilter());
                        t.setText(General.resources+"");
                        ((AbstractDocument)t.getDocument()).setDocumentFilter(new ResourcesDocFilter());

                    }

                    @Override
                    public void focusGained(FocusEvent e) {
                        ((AbstractDocument) t.getDocument()).setDocumentFilter(new NumberDocFilter());
                        t.setText(General.resources+"");
                        ((AbstractDocument)t.getDocument()).setDocumentFilter(new ResourcesDocFilter());
                    }

                });
                break;
        }
    }
}
