package ui;

import model.*;
import persistance.JsonReader;
import persistance.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

    //This is the method that is called when the JButton btn is clicked TODO all comments
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("loadFile")) {
            loadStarship();
            loadWindow.dispose();
            //TODO open the main frames
        }

        if (e.getActionCommand().equals("doNotLoadFile")) {
            initializeCrew();
            loadWindow.dispose();
            //TODO open captain name frame
        }
    }

    // MODIFIES: this, Starship, CrewMember
    // EFFECTS: initializes crew
    private void initializeCrew() {
        List<CrewMember> crewMembers = new ArrayList<>();
        crewMembers.add(new CrewMember("William", "Riker", Rank.COMMANDER, Division.COMMAND));
        crewMembers.add(new CrewMember("Geordi", "La Forge", Rank.LIEUTENANT_COMMANDER, Division.ENGINEERING));
        crewMembers.add(new CrewMember("Data", "", Rank.COMMANDER, Division.COMMAND));
        crewMembers.add(new CrewMember("Reginald", "Baclay", Rank.LIEUTENANT, Division.ENGINEERING));
        crewMembers.add(new CrewMember("Keiko", "O'Brien", Rank.CIVILIAN, Division.COMMAND));
        crewMembers.add(new CrewMember("Jadzia", "Dax", Rank.LIEUTENANT, Division.MEDICAL));
        crewMembers.add(new CrewMember("Julian", "Bashir", Rank.LIEUTENANT, Division.MEDICAL));
        crewMembers.add(new CrewMember("Lwaxana", "Troi", Rank.CIVILIAN, Division.OTHER));
        crewMembers.add(new CrewMember("Tribble", "", Rank.OTHER, Division.OTHER));
        crewMembers.add(new CrewMember("Morn", "", Rank.CIVILIAN, Division.OTHER));
        crewMembers.add(new CrewMember("Elim", "Garak", Rank.CIVILIAN, Division.OTHER));
        crewMembers.add(new CrewMember("Worf", "Rozhenko", Rank.LIEUTENANT_COMMANDER, Division.COMMAND));
        crewMembers.add(new CrewMember("Quark", "", Rank.CIVILIAN, Division.OTHER));
        crewMembers.add(new CrewMember("Odo", "", Rank.CIVILIAN, Division.OTHER));
        crewMembers.add(new CrewMember("B'Elanna", "Torres", Rank.LIEUTENANT, Division.ENGINEERING));
        crewMembers.add(new CrewMember("Tom", "Paris", Rank.ENSIGN, Division.COMMAND));
        crewMembers.add(new CrewMember("Harry", "Kim", Rank.ENSIGN, Division.COMMAND));
        crewMembers.add(new CrewMember("The", "Doctor", Rank.OTHER, Division.MEDICAL));
        crewMembers.add(new CrewMember("Seven of Nine", "", Rank.CIVILIAN, Division.SCIENCES));
        crewMembers.add(new CrewMember("Iceb", "", Rank.CADET, Division.SCIENCES));
        crewMembers.add(new CrewMember("Giant", "Spock", Rank.OTHER, Division.OTHER));
        starship.setCrewMembers(crewMembers);
    }

    // EFFECTS: prints mission log TODO delete unused print methods
    public void printAwayMissionLog() {
        if (starship.getMissionLog().isEmpty()) {
            System.out.println("\nNo previous missions.");
        } else {
            System.out.println("\nMission log:");
            for (AwayMission am : starship.getMissionLog()) {
                String s;
                if (am.getIsObjectiveComplete()) {
                    s = "complete";
                } else {
                    s = "incomplete";
                }

                System.out.println("\nMission ID: " + am.getAwayMissionID());
                System.out.println("Stardate: " + starship.stardateToString(am.getStardate()));
                System.out.println("Objective " + s);
            }
        }
    }

    // EFFECTS: prints current crew
    public void printCurrentCrew() {
        System.out.println("\nCrew:");

        int i = 1;
        for (CrewMember cm : starship.getCrewMembers()) {
            System.out.println("\t" + i + ". " + cm.nameToString(false));
            i++;
        }
    }

    // EFFECTS: prints stats on given crew member
    public void printCrewMemberStats(int i) {
        if (i >= 1 && i <= starship.getCrewMembers().size()) {
            CrewMember cm = starship.getCrewMembers().get(i - 1);

            System.out.println("\n" + cm.nameToString(false));
            System.out.println("Rank: " + cm.getRank().name());
            System.out.println("Division: " + cm.getDivision().name());
            System.out.println("Health Status: " + cm.getHealthStatus().name());
        } else {
            System.out.println("\nPlease select a valid number.");
        }
    }

    // EFFECTS: prints away team
    public void printAwayTeam() {
        if (starship.getCurrentAwayMission().getAwayTeam().isEmpty()) {
            System.out.println("\nNo one assigned to the away team.");
        } else {
            System.out.println("\nAway Team:");

            int i = 1;
            for (CrewMember cm : starship.getCurrentAwayMission().getAwayTeam()) {
                System.out.println("\t" + i + ". " + cm.nameToString(false));
                i++;
            }
        }
    }

    // EFFECTS: saves the starship data to file
    private void saveStarship() {
        try {
            jsonWriter.open();
            jsonWriter.write(starship);
            jsonWriter.close();
            System.out.println("\nSaved starship data for " + starship.getShipName() + " (" + starship.getShipID()
                    + ") to " + JSON_STARSHIP);
        } catch (FileNotFoundException e) {
            System.out.println("\nUnable to write to file: " + JSON_STARSHIP);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads starship data from file
    public void loadStarship() {
        try {
            this.starship = jsonReader.read();
            //TODO update accordingly
            System.out.println("\nLoaded starship data for " + starship.getShipName() + " (" + starship.getShipID()
                    + ") from " + JSON_STARSHIP);
        } catch (IOException e) {
            System.out.println("\nUnable to read from file: " + JSON_STARSHIP);
        }
    }
}
