package ui;

import model.Starship;

import javax.swing.*;
import java.awt.*;

public class AwayMissionManagerGUI extends JFrame /*implements ActionListener*/ {
    private static final int WIDTH = 500;
    private static final int HEIGHT = 500;
    private static final int VGAP = 15; //TODO use?
    private ImageIcon test;
    private JPanel jpanel;
    private JButton button;
    private JLabel label;

    public AwayMissionManagerGUI() {
        //Create and set up the window.
        super("Away Mission Manager Application");
        setDefaultCloseOperation(EXIT_ON_CLOSE); //TODO update to call the quit method

        //Set up the content pane.
        Container pane = this.getContentPane();
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
        addComponentsToPane(pane);

        //Display the window.
        this.setPreferredSize(new Dimension(WIDTH,HEIGHT));
        this.pack();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
    }

    private void addComponentsToPane(Container pane) {
        JPanel jpanel1 = addPanel(80, pane);
        jpanel1.setBackground(Color.yellow);
        jpanel1.setLayout(new BoxLayout(jpanel1, BoxLayout.Y_AXIS));

        addLabel(Starship.SHIP_NAME + " (" + Starship.SHIP_ID + ")", jpanel1);
        loadImages();
        addLabel(test, jpanel1);
        addLabel("Would you like to load previous starship data?", jpanel1);


        JPanel jpanel2 = addPanel(20, pane);
        jpanel2.setBackground(Color.green);
        jpanel2.setLayout(new BoxLayout(jpanel2, BoxLayout.X_AXIS));
        addButton("yes", jpanel2);
        addButton("no", jpanel2);

       //this.setIconImage();
    }

    private JPanel addPanel(int percentHeight, Container pane) {
        jpanel = new JPanel();
        pane.add(jpanel);
        jpanel.setPreferredSize(new Dimension(WIDTH,HEIGHT * percentHeight / 100));
        jpanel.setVisible(true);
        return jpanel;
    }

    private void addButton(String text, Container container) {
        button = new JButton(text);
        container.add(button);
    }

    private void addLabel(String text, Container container) {
        label = new JLabel(text);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        container.add(label);
    }

//    public void setDoh() {
//        imageAsLabel = new JLabel(dohImage);
//        lightPanel.add(imageAsLabel);
//    }

    private void loadImages() {
        String sep = System.getProperty("file.separator");
        test = new ImageIcon(System.getProperty("user.dir") + sep
                + "images" + sep + "test.png");
    }

    //This is a common need in almost every non-trivial GUI program. Basically you group your componenets,
    // create a JPanel for each group and set each layout for each JPanel. Then add the components to each respective
    // JPanel and then add each JPanel to the ContentPane of the JFrame, which also needs to have its layout set.
}
