package ui;

import model.Starship;
import persistance.JsonReader;
import persistance.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

/**
 * GUI for initial window asking the user if they would like to load their data
 */
public class AwayMissionManagerGUI extends JFrame implements ActionListener {
    private static final int DESKTOP_WINDOW_WIDTH = 800;
    private static final int DESKTOP_WINDOW_HEIGHT = 600;
    private static final int LOAD_WINDOW_WIDTH = 400;
    private static final int LOAD_WINDOW_HEIGHT = 250;
    private static final int LOAD_WINDOW_VGAP = 10;
    private static final int LOAD_WINDOW_HGAP = 10;

    private static final String JSON_STARSHIP = "./data/starship.json";
    private Starship starship;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private JDesktopPane desktop;
    private JInternalFrame loadWindow;
    private JPanel jpanel;
    private JButton button;
    private JLabel label;
    private ImageIcon image;

    //TODO dont forget method comments!
    public AwayMissionManagerGUI() throws FileNotFoundException {
        starship = new Starship("Captain", "Name");
        jsonWriter = new JsonWriter(JSON_STARSHIP);
        jsonReader = new JsonReader(JSON_STARSHIP);
        desktop = new JDesktopPane();

        setContentPane(desktop);
        setTitle("Away Mission Manager Application");
        setSize(DESKTOP_WINDOW_WIDTH, DESKTOP_WINDOW_HEIGHT);

        createLoadWindow();
        desktop.add(loadWindow);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);


//        //Create and set up the window.
//        super("Away Mission Manager Application");
//        setDefaultCloseOperation(EXIT_ON_CLOSE);

//        //Display the window.
//        this.setPreferredSize(new Dimension(WIDTH,HEIGHT));
//        this.pack();
//        this.setLocationRelativeTo(null);
//        this.setResizable(false);
//        this.setVisible(true);
    }

    public void createLoadWindow() {
        loadWindow = new JInternalFrame("Loading...", false, false, false, false);

        //Set up the content pane.
        Container pane = loadWindow.getContentPane();
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
        pane.add(Box.createRigidArea(new Dimension(0, LOAD_WINDOW_VGAP)));
        addComponentsToPane(pane);
        pane.add(Box.createRigidArea(new Dimension(0, LOAD_WINDOW_VGAP)));

        loadWindow.pack();
        //loadWindow.setLocationRelativeTo(null);
        loadWindow.setVisible(true);
    }

        private void addComponentsToPane(Container pane) {
        JPanel jpanel1 = addPanel(80, pane);
        //jpanel1.setBackground(Color.yellow);
        jpanel1.setLayout(new BoxLayout(jpanel1, BoxLayout.Y_AXIS));
        addLabel(Starship.SHIP_NAME + " (" + Starship.SHIP_ID + ")", jpanel1);
        addImage(jpanel1);
        addLabel("Would you like to load previous starship data?", jpanel1);

        pane.add(Box.createRigidArea(new Dimension(0, LOAD_WINDOW_VGAP)));

        JPanel jpanel2 = addPanel(20, pane);
        //jpanel2.setBackground(Color.green);
        jpanel2.setLayout(new BoxLayout(jpanel2, BoxLayout.X_AXIS));
        addButton("yes", "loadFile", jpanel2);
        jpanel2.add(Box.createRigidArea(new Dimension(LOAD_WINDOW_HGAP, 0)));
        addButton("no", "doNotLoadFile", jpanel2);
    }

    private JPanel addPanel(int percentHeight, Container pane) {
        jpanel = new JPanel();
        pane.add(jpanel);
        jpanel.setPreferredSize(new Dimension(LOAD_WINDOW_WIDTH, LOAD_WINDOW_HEIGHT * percentHeight / 100));
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

    public void addCentered(Component internalFrame) {
        desktop.add(internalFrame);
        internalFrame.setLocation((desktop.getWidth()-internalFrame.getWidth())/2, (desktop.getHeight()-internalFrame.getHeight())/2);
        internalFrame.setVisible(true);
    }

    //This is the method that is called when the JButton btn is clicked TODO all comments
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("loadFile")) {
        }

        if (e.getActionCommand().equals("doNotLoadFile")) {
        }
    }
}
