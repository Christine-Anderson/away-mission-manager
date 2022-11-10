package ui;

import model.Starship;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

/**
 * Away mission manager GUI
 */
public class AwayMissionManagerGUI extends JFrame implements ActionListener {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;
    private static final int VGAP = 15;
    private static final int HGAP = 15;
    private JPanel jpanel;
    private JButton button;
    private JLabel label;
    private ImageIcon image;

    //TODO dont forget method comments!
    public AwayMissionManagerGUI() {
        //Create and set up the window.
        super("Away Mission Manager Application");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //Set up the content pane.
        Container pane = this.getContentPane();
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
        pane.add(Box.createRigidArea(new Dimension(0, VGAP)));
        addComponentsToPane(pane);
        pane.add(Box.createRigidArea(new Dimension(0, VGAP)));

        //Display the window.
        this.setPreferredSize(new Dimension(WIDTH,HEIGHT));
        this.pack();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
    }

    private void addComponentsToPane(Container pane) {
        JPanel jpanel1 = addPanel(80, pane);
        //jpanel1.setBackground(Color.yellow);
        jpanel1.setLayout(new BoxLayout(jpanel1, BoxLayout.Y_AXIS));
        addLabel(Starship.SHIP_NAME + " (" + Starship.SHIP_ID + ")", jpanel1);
        addImage(jpanel1);
        addLabel("Would you like to load previous starship data?", jpanel1);

        pane.add(Box.createRigidArea(new Dimension(0, VGAP)));

        JPanel jpanel2 = addPanel(20, pane);
        //jpanel2.setBackground(Color.green);
        jpanel2.setLayout(new BoxLayout(jpanel2, BoxLayout.X_AXIS));
        addButton("yes", "loadFile", jpanel2);
        jpanel2.add(Box.createRigidArea(new Dimension(HGAP, 0)));
        addButton("no", "doNotLoadFile", jpanel2);
    }

    private JPanel addPanel(int percentHeight, Container pane) {
        jpanel = new JPanel();
        pane.add(jpanel);
        jpanel.setPreferredSize(new Dimension(WIDTH,HEIGHT * percentHeight / 100));
        jpanel.setVisible(true);
        return jpanel;
    }

    private void addButton(String buttonLabel, String actionCommand, Container container) {
        button = new JButton(buttonLabel);
        button.setActionCommand(actionCommand);
        button.addActionListener(this);
        container.add(button);
    }

    private void addLabel(String text, Container container) {
        label = new JLabel(text);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        container.add(label);
    }

    private void addImage(Container container) {
        image = new ImageIcon("images/Starship.png");
        Image scaleImage = image.getImage();
        Image newImage = scaleImage.getScaledInstance(300, 150,  java.awt.Image.SCALE_SMOOTH);
        image = new ImageIcon(newImage);

        label = new JLabel();
        label.setIcon(image);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        container.add(label);
    }

    //This is the method that is called when the JButton btn is clicked TODO all comments
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("loadFile")) {
            app.loadStarship();
            this.getContentPane().removeAll();
        }

        if (e.getActionCommand().equals("doNotLoadFile")) {
            this.getContentPane().removeAll();
        }
    }
}
