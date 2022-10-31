package ui;

import model.Starship;

import javax.swing.*;
import java.awt.*;

public class AwayMissionManagerGUI extends JFrame /*implements ActionListener*/ {
    private static final int WIDTH = 500;
    private static final int HEIGHT = 500;
    private static final int VGAP = 15;

    public AwayMissionManagerGUI() {
        //Create and set up the window.
        super("Away Mission Manager Application");
        setDefaultCloseOperation(EXIT_ON_CLOSE); //update to call the quit method

        //Set up the content pane.
        addComponentsToPane(this.getContentPane());

        //Display the window.
        this.setPreferredSize(new Dimension(WIDTH,HEIGHT));
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public static void addComponentsToPane(Container pane) {


//        addLabel(Starship.SHIP_NAME + " (" + Starship.SHIP_ID + ")", pane);
//
//        //this.setIconImage();
//
//        //Would you like to load previous starship data?
//
//        pane.setLayout(new BoxLayout(pane, BoxLayout.X_AXIS));
//        addButton("yes", pane);
//        addButton("no", pane);
    }

    public static void addPanel(Container container) {
        JPanel jpanel1 = new JPanel();
        //jpanel1.setBounds(40,80,200,200);
        this.add(jpanel1);
        jpanel1.setSize(WIDTH * 80 / 100,HEIGHT * 80 / 100);
        jpanel1.setLayout(null);
        jpanel1.setVisible(true);

        JPanel jpanel2 = new JPanel();
        //jpanel2.setBounds(40,80,200,200);
        jpanel2.add(jpanel2);
        jpanel2.setSize(WIDTH * 20 / 100,HEIGHT * 20 / 100);
        jpanel2.setLayout(null);
        jpanel2.setVisible(true);
    }

    private static void addButton(String text, Container container) {
        JButton button = new JButton(text);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        container.add(button);
    }

    private static void addLabel(String text, Container container) {
        JLabel label = new JLabel(text);
        container.add(label);
    }

    //This is a common need in almost every non-trivial GUI program. Basically you group your componenets,
    // create a JPanel for each group and set each layout for each JPanel. Then add the components to each respective
    // JPanel and then add each JPanel to the ContentPane of the JFrame, which also needs to have its layout set.
}
