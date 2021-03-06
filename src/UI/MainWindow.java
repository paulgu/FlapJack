package UI;

import Engine.Casino;
import Engine.UIListener;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

/**
 * User: AnubhawArya
 * Date: 9/13/13
 * Time: 3:05 PM
 */
public class MainWindow implements ItemListener, ActionListener {
    static JFrame frame;
    private JPanel mainWindow;
    private JComboBox casinoList;
    private JLabel numDecks;
    private JCheckBox soft17s, doubleSplitting, resplitAces;
    private JButton goButton;
    ArrayList<Casino> casinos;
    static MainWindow ui;

    public static void main(String[] args) {
        frame = new JFrame("FlapJack");
        ui = new MainWindow();
        frame.setContentPane(ui.mainWindow);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ui.initUI();
        frame.pack();
        frame.setVisible(true);
    }

    private void initUI() {
        initListeners();
        initCasinos();
    }

    private void initListeners() {
        goButton.addActionListener(this);
        casinoList.addItemListener(this);
    }

    private void initCasinos() {
        casinos = new ArrayList<Casino>();
        casinos.add(new Casino("Bellagio", 6, true, true, true));
        casinos.add(new Casino("Caesar's Palace", 2, true, false, false));
        casinos.add(new Casino("MGM Grand", 6, false, true, true));
        for (Casino casino : casinos)
            casinoList.addItem(casino.getName());
        setLabels((String) casinoList.getSelectedItem());
    }

    public void itemStateChanged(ItemEvent itemEvent) {
        setLabels((String) itemEvent.getItem());
    }

    public void setLabels(String val) {
        for (Casino casino : casinos) {
            if (val.equals(casino.getName())) {
                numDecks.setText(String.valueOf(casino.getNumberOfDecks()));
                soft17s.setSelected(casino.isHitOnSoft17s());
                doubleSplitting.setSelected(casino.isDoubleAfterSplit());
                resplitAces.setSelected(casino.isResplitAfterAce());
                frame.pack();
                return;
            }
        }
    }

    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == goButton)
            for (Casino casino : casinos)
                if (casinoList.getSelectedItem().equals(casino.getName()))
                    UIListener.setCasino(casino);
    }
}
