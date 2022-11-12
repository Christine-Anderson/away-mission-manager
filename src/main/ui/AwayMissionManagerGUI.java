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
 * Away Mission Manager GUI
 */
public class AwayMissionManagerGUI extends JFrame implements ActionListener {
    private static final int DESKTOP_WINDOW_WIDTH = 800;
    private static final int DESKTOP_WINDOW_HEIGHT = 600;
    private static final int LOAD_WINDOW_VGAP = 10;
    private static final int LOAD_WINDOW_HGAP = 10;
    private static final int INPUT_NAME_VGAP = 10;
    private static final int CREW_MANAGER_WINDOW_VGAP = 10;

    private static final String JSON_STARSHIP = "./data/starship.json";
    private final DefaultListModel<String> l1 = new DefaultListModel<>();

    private Starship starship;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private JDesktopPane desktop;
    private JInternalFrame loadWindow;
    private JInternalFrame inputNameWindow;
    private JInternalFrame missionManagerWindow;
    private JInternalFrame crewManagerWindow;
    private JInternalFrame awayMissionLog;

    private JPanel jpanel;
    private JButton button;
    private JLabel label;
    private ImageIcon image;
    private JTextField textField1;
    private JTextField textField2;
    private  JList<String> list1;


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

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void createLoadWindow() {
        loadWindow = new JInternalFrame("Loading...", false, false, false, false);

        Container pane = loadWindow.getContentPane();
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
        addComponentsLoadWindow(pane);

        loadWindow.pack();
        loadWindow.setVisible(true);
        desktop.add(loadWindow);
    }

    private void addComponentsLoadWindow(Container pane) {
        JPanel jpanel1 = addPanel(pane);
        jpanel1.setLayout(new BoxLayout(jpanel1, BoxLayout.Y_AXIS));
        addLabel(Starship.SHIP_NAME + " (" + Starship.SHIP_ID + ")", jpanel1);
        addImage(jpanel1);
        addLabel("Would you like to load previous starship data?", jpanel1);

        pane.add(Box.createRigidArea(new Dimension(0, LOAD_WINDOW_VGAP)));

        JPanel jpanel2 = addPanel(pane);
        jpanel2.setLayout(new BoxLayout(jpanel2, BoxLayout.X_AXIS));
        addButton("yes", "loadFile", false, jpanel2);
        jpanel2.add(Box.createRigidArea(new Dimension(LOAD_WINDOW_HGAP, 0)));
        addButton("no", "doNotLoadFile", false,  jpanel2);
    }

    public void createInputNameWindow() {
        inputNameWindow = new JInternalFrame("Captain's Name", false, false, false, false);

        Container pane = inputNameWindow.getContentPane();
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
        addLabel("Enter your first and last name:", pane);
        pane.add(Box.createRigidArea(new Dimension(0, INPUT_NAME_VGAP)));

        textField1 = new JTextField( "First Name", 10);
        textField2 = new JTextField( "Last Name", 10);
        pane.add(textField1);
        pane.add(textField2);
        pane.add(Box.createRigidArea(new Dimension(0, INPUT_NAME_VGAP)));

        addButton("enter", "setCaptainName", true, pane);

        inputNameWindow.pack();
        inputNameWindow.setVisible(true);
        desktop.add(inputNameWindow);
    }

    public void createMissionManagerWindow() {
        missionManagerWindow = new JInternalFrame("Mission Manager", false, false, false, false);

        Container pane = missionManagerWindow.getContentPane();
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));

        addButton("start mission", "start mission", true, pane);
        addButton("end mission", "end mission", true, pane);
        addButton("emergency beam out", "emergency beam out", true, pane);
        addButton("print mission log", "print mission log", true, pane);

        missionManagerWindow.pack();
        missionManagerWindow.setVisible(true);
        desktop.add(missionManagerWindow);
    }

    public void createCrewManagerWindow() {
        crewManagerWindow = new JInternalFrame("Crew Manager", false, false, false, false);

        Container pane = crewManagerWindow.getContentPane();
        pane.setLayout(new BoxLayout(pane, BoxLayout.X_AXIS));
        addComponentsCrewManagerWindow(pane);

        crewManagerWindow.pack();
        crewManagerWindow.setVisible(true);
        desktop.add(crewManagerWindow);
    }

    private void addComponentsCrewManagerWindow(Container pane) {
        JPanel jpanel1 = addPanel(pane);
        jpanel1.setLayout(new BoxLayout(jpanel1, BoxLayout.Y_AXIS));
        createCrewMemberList(jpanel1);
        pane.add(Box.createRigidArea(new Dimension(0, CREW_MANAGER_WINDOW_VGAP)));
        addButton("add", "addAwayTeamMember", true, jpanel1);
        pane.add(Box.createRigidArea(new Dimension(0, CREW_MANAGER_WINDOW_VGAP)));
        addButton("stats", "statsCrewMember", true, jpanel1);

        JPanel jpanel2 = addPanel(pane);
        jpanel2.setLayout(new BoxLayout(jpanel2, BoxLayout.Y_AXIS));
        createAwayTeamList(jpanel2);
        pane.add(Box.createRigidArea(new Dimension(0, CREW_MANAGER_WINDOW_VGAP)));
        addButton("remove", "removeAwayTeamMember", true, jpanel2);
    }

    public void createAwayMissionLog() {
        awayMissionLog = new JInternalFrame("Away Mission Log", false, true, false, false);
        JTextArea missionLog = new JTextArea(awayMissionLogToString());
        missionLog.setBounds(10,30, 200,200);
        awayMissionLog.add(missionLog);
        awayMissionLog.setSize(300,300);
        awayMissionLog.setLayout(null);

        awayMissionLog.setVisible(true);
        desktop.add(awayMissionLog);
    }

    private void createCrewMemberList(Container pane) {
        for (CrewMember cm: starship.getCrewMembers()) {
            l1.addElement(cm.nameToString(true));
        }

        list1 = new JList<>(l1);
        list1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list1.setVisibleRowCount(5);
        JScrollPane listScroller = new JScrollPane(list1);
        listScroller.setPreferredSize(new Dimension(150, 200));

        pane.add(listScroller);
    }

    private void createAwayTeamList(Container pane) {
        final DefaultListModel<String> l2 = new DefaultListModel<>();
        JList<String> list2 = new JList<>(l2);
        pane.add(list2);
    }

    private JPanel addPanel(Container pane) {
        jpanel = new JPanel();
        pane.add(jpanel);
        jpanel.setVisible(true);
        return jpanel;
    }

    private void addButton(String buttonLabel, String actionCommand, boolean isCentred, Container container) {
        button = new JButton(buttonLabel);
        button.setActionCommand(actionCommand);
        button.addActionListener(this);
        if (isCentred) {
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
        }
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
        Image newImage = scaleImage.getScaledInstance(300, 150, java.awt.Image.SCALE_SMOOTH);
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
            createMissionManagerWindow();
            createCrewManagerWindow();
        }

        if (e.getActionCommand().equals("doNotLoadFile")) {
            initializeCrew();
            loadWindow.dispose();
            createInputNameWindow();
        }

        if (e.getActionCommand().equals("setCaptainName")) {
            String fn = textField1.getText();
            String ln = textField2.getText();


            if(!fn.equals("") && !ln.equals("")) {
                starship.setFirstNameOfCaptain(fn);
                starship.setLastNameOfCaptain(ln);
                welcomeCaptain();
                inputNameWindow.dispose();
                createMissionManagerWindow();
                createCrewManagerWindow();
            } else {
                JOptionPane.showMessageDialog(desktop,"Please enter a valid name.","Alert", JOptionPane.WARNING_MESSAGE);
            }
        }

        //TODO check that mission is not null for start, end, and emergency beam out
        if (e.getActionCommand().equals("start mission")) {
            if (!starship.getCurrentAwayMission().getIsActive()
                    && !starship.getCurrentAwayMission().getAwayTeam().isEmpty()) {
                starship.startAwayMission();
                JOptionPane.showMessageDialog(desktop,"Make it so.");
            } else if (starship.getCurrentAwayMission().getIsActive()) {
                JOptionPane.showMessageDialog(desktop,"Away Mission has already started.","Alert", JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(desktop,"Please select an away team.","Alert", JOptionPane.WARNING_MESSAGE);
            }
        }

        if (e.getActionCommand().equals("end mission")) {
            if (starship.getCurrentAwayMission().getIsActive()) {
                starship.endAwayMission();
                JOptionPane.showMessageDialog(desktop,"Good job number one.");
            } else {
                JOptionPane.showMessageDialog(desktop,"Away Mission has not started.","Alert", JOptionPane.WARNING_MESSAGE);
            }
        }

        if (e.getActionCommand().equals("emergency beam out")) {
            if (starship.getCurrentAwayMission().getIsActive()) {
                starship.emergencyBeamOut();
                JOptionPane.showMessageDialog(desktop,"Beam me up Scottie!");
            } else {
                JOptionPane.showMessageDialog(desktop,"Away Mission has not started.","Alert", JOptionPane.WARNING_MESSAGE);
            }
        }

        if (e.getActionCommand().equals("print mission log")) {
            if (starship.getMissionLog().isEmpty()) {
                JOptionPane.showMessageDialog(desktop,"No previous missions.","Alert", JOptionPane.WARNING_MESSAGE);
            } else {
                createAwayMissionLog();
            }
        }

        if (e.getActionCommand().equals("addAwayTeamMember")) {

        }

        if (e.getActionCommand().equals("statsCrewMember")) {

        }

        if (e.getActionCommand().equals("removeAwayTeamMember")) {

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
    public String awayMissionLogToString() {
        String awayMissionLog = "Mission log:";
            for (AwayMission am : starship.getMissionLog()) {
                String s;
                if (am.getIsObjectiveComplete()) {
                    s = "complete";
                } else {
                    s = "incomplete";
                }

               awayMissionLog = awayMissionLog + "\nMission ID: " + am.getAwayMissionID() + "\nStardate: " + starship.stardateToString(am.getStardate()) + "\nObjective " + s + "\n";
            }

            return awayMissionLog;
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
    private void loadStarship() {
        try {
            this.starship = jsonReader.read();
            welcomeCaptain();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(desktop,"Unable to read from file: " + JSON_STARSHIP,"Alert", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void welcomeCaptain() {
        JOptionPane.showMessageDialog(desktop,"Welcome Captain " + starship.getLastNameOfCaptain());
    }
}
