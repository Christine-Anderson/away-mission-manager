package ui;

import model.*;
import model.Event;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER;
import static javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS;

/**
 * Away Mission Manager GUI
 */
public class AwayMissionManagerGUI extends JFrame implements ActionListener {
    private static final int DESKTOP_WINDOW_WIDTH = 800;
    private static final int DESKTOP_WINDOW_HEIGHT = 600;
    private static final int VGAP = 10;
    private static final int HGAP = 10;

    private static final String JSON_STARSHIP = "./data/starship.json";
    private final DefaultListModel<String> l1 = new DefaultListModel<>();
    private final DefaultListModel<String> l2 = new DefaultListModel<>();

    private Starship starship;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private JDesktopPane desktop;
    private JInternalFrame loadWindow;
    private JInternalFrame inputNameWindow;
    private JInternalFrame missionCreationWindow;
    private JInternalFrame missionManagerWindow;
    private JInternalFrame crewManagerWindow;
    private JInternalFrame saveWindow;

    private JLabel label;
    private JTextField textField1;
    private JTextField textField2;
    private JList<String> list1;
    private JList<String> list2;

    // MODIFIES: this, Starship
    // EFFECTS: Sets up desktop and load window
    public AwayMissionManagerGUI() throws FileNotFoundException {
        starship = new Starship("Captain", "Name");
        jsonWriter = new JsonWriter(JSON_STARSHIP);
        jsonReader = new JsonReader(JSON_STARSHIP);
        desktop = new JDesktopPane();

        setContentPane(desktop);
        setTitle("Away Mission Manager Application");
        setSize(DESKTOP_WINDOW_WIDTH, DESKTOP_WINDOW_HEIGHT);
        createLoadWindow();

        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                JInternalFrame[] frames = desktop.getAllFrames();
                for (JInternalFrame j : frames) {
                    j.dispose();
                }
                createSaveWindow();
            }
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: Creates load window structure and layout
    public void createLoadWindow() {
        loadWindow = new JInternalFrame("Loading...", false, false, false, false);

        Container pane = loadWindow.getContentPane();
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
        addComponentsLoadWindow(pane);

        loadWindow.setSize(400, 275);
        centreWindow(loadWindow);
        loadWindow.setVisible(true);
        desktop.add(loadWindow);
    }

    // MODIFIES: this
    // EFFECTS: Creates and adds containers and components to load window
    private void addComponentsLoadWindow(Container pane) {
        JPanel jpanel1 = addPanel(pane);
        jpanel1.setLayout(new BoxLayout(jpanel1, BoxLayout.Y_AXIS));
        addLabel(Starship.SHIP_NAME + " (" + Starship.SHIP_ID + ")", jpanel1);
        addImage(jpanel1);
        addLabel("Would you like to load previous starship data?", jpanel1);

        pane.add(Box.createRigidArea(new Dimension(0, VGAP)));

        JPanel jpanel2 = addPanel(pane);
        jpanel2.setLayout(new BoxLayout(jpanel2, BoxLayout.X_AXIS));
        addButton("yes", "loadFile", false, jpanel2);
        jpanel2.add(Box.createRigidArea(new Dimension(HGAP, 0)));
        addButton("no", "doNotLoadFile", false, jpanel2);
    }

    // MODIFIES: this
    // EFFECTS: Creates input name window and adds components
    public void createInputNameWindow() {
        inputNameWindow = new JInternalFrame("Captain's Name", false, false, false, false);

        Container pane = inputNameWindow.getContentPane();
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
        addLabel("Enter your first and last name:", pane);
        pane.add(Box.createRigidArea(new Dimension(0, VGAP)));

        textField1 = new JTextField("First Name", 10);
        textField2 = new JTextField("Last Name", 10);
        pane.add(textField1);
        pane.add(textField2);
        pane.add(Box.createRigidArea(new Dimension(0, VGAP)));

        addButton("enter", "setCaptainName", true, pane);

        inputNameWindow.setSize(225, 175);
        centreWindow(inputNameWindow);
        inputNameWindow.setVisible(true);
        desktop.add(inputNameWindow);
    }

    // MODIFIES: this
    // EFFECTS: Creates mission creation window and adds components
    public void createMissionCreationWindow() {
        missionCreationWindow = new JInternalFrame("Mission Creation", false, false, false, false);

        Container pane = missionCreationWindow.getContentPane();
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));

        addButton("create mission", "create mission", true, pane);
        addButton("print mission log", "print mission log", true, pane);

        missionCreationWindow.setSize(225, 115);
        centreWindow(missionCreationWindow);
        missionCreationWindow.setVisible(true);
        desktop.add(missionCreationWindow);
    }

    // MODIFIES: this
    // EFFECTS: Creates mission manager window and adds components
    public void createMissionManagerWindow() {
        missionManagerWindow = new JInternalFrame("Mission Manager", false, false, false, false);

        Container pane = missionManagerWindow.getContentPane();
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));

        addButton("start mission", "start mission", true, pane);
        addButton("end mission", "end mission", true, pane);
        addButton("emergency beam out", "emergency beam out", true, pane);
        addButton("print mission log", "print mission log", true, pane);

        missionManagerWindow.setSize(225, 165);
        missionManagerWindow.setLocation((DESKTOP_WINDOW_WIDTH - missionManagerWindow.getWidth()) / 2,
                (desktop.getHeight() - missionManagerWindow.getHeight()) / 2);
        missionManagerWindow.setVisible(true);
        desktop.add(missionManagerWindow);
    }

    // MODIFIES: this
    // EFFECTS: Creates crew manager window structure and layout
    public void createCrewManagerWindow() {
        crewManagerWindow = new JInternalFrame("Crew Manager", false, false, false, false);

        Container pane = crewManagerWindow.getContentPane();
        pane.setLayout(new BoxLayout(pane, BoxLayout.X_AXIS));
        addComponentsCrewManagerWindow(pane);

        crewManagerWindow.pack();
        crewManagerWindow.setVisible(true);
        desktop.add(crewManagerWindow);
    }

    // MODIFIES: this
    // EFFECTS: Creates and adds containers and components to crew manager window
    private void addComponentsCrewManagerWindow(Container pane) {
        JPanel jpanel1 = addPanel(pane);
        jpanel1.setLayout(new BoxLayout(jpanel1, BoxLayout.Y_AXIS));
        addLabel("Crew Members", jpanel1);
        jpanel1.add(Box.createRigidArea(new Dimension(0, VGAP)));
        createCrewMemberList(jpanel1);
        jpanel1.add(Box.createRigidArea(new Dimension(0, VGAP)));
        addButton("add", "addAwayTeamMember", true, jpanel1);
        addButton("stats", "statsCrewMember", true, jpanel1);

        JPanel jpanel2 = addPanel(pane);
        jpanel2.setLayout(new BoxLayout(jpanel2, BoxLayout.Y_AXIS));
        addLabel("Away Team", jpanel2);
        jpanel2.add(Box.createRigidArea(new Dimension(0, VGAP)));
        createAwayTeamList(jpanel2);
        jpanel2.add(Box.createRigidArea(new Dimension(0, VGAP)));
        addButton("remove", "removeAwayTeamMember", true, jpanel2);
    }

    // MODIFIES: this
    // EFFECTS: Creates crew member list with crew members and adds component to the crew manager window
    private void createCrewMemberList(Container pane) {
        if (l1.isEmpty()) {
            for (CrewMember cm : starship.getCrewMembers()) {
                l1.addElement(cm.nameToString(true));
            }
        }

        list1 = new JList<>(l1);
        list1.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JScrollPane listScroller = new JScrollPane(list1, VERTICAL_SCROLLBAR_ALWAYS, HORIZONTAL_SCROLLBAR_NEVER);
        listScroller.setPreferredSize(new Dimension(125, 200));

        pane.add(listScroller);
    }

    // MODIFIES: this
    // EFFECTS: Creates away team list and adds component to the crew manager window
    private void createAwayTeamList(Container pane) {
        list2 = new JList<>(l2);
        list2.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JScrollPane listScroller = new JScrollPane(list2, VERTICAL_SCROLLBAR_ALWAYS, HORIZONTAL_SCROLLBAR_NEVER);
        listScroller.setPreferredSize(new Dimension(125, 200));

        pane.add(listScroller);
    }

    // MODIFIES: this
    // EFFECTS: Creates away mission log print out window and adds components
    public void createAwayMissionLog() {
        JInternalFrame awayMissionLog = new JInternalFrame("Away Mission Log", false, true, false, false);
        JTextArea missionLog = new JTextArea(awayMissionLogToString());
        JScrollPane scroll = new JScrollPane(missionLog);
        scroll.setPreferredSize(new Dimension(200, 150));

        awayMissionLog.add(scroll);
        awayMissionLog.setSize(250, 200);
        awayMissionLog.setLocation((DESKTOP_WINDOW_WIDTH - awayMissionLog.getWidth()) / 2, 0);
        awayMissionLog.setVisible(true);
        desktop.add(awayMissionLog);
    }

    // MODIFIES: this
    // EFFECTS: Creates crew member stats print out window and adds components
    public void createCrewMemberStats() {
        JInternalFrame crewMemberStats = new JInternalFrame("Crew Member Stats", false, true, false, false);
        JTextArea crewStats = new JTextArea(crewMemberStatsToString());
        JScrollPane scroll = new JScrollPane(crewStats);
        scroll.setPreferredSize(new Dimension(240, 300));

        crewMemberStats.add(scroll);
        crewMemberStats.setSize(250, 300);
        crewMemberStats.setLocation(DESKTOP_WINDOW_WIDTH - crewMemberStats.getWidth(), 0);
        crewMemberStats.setVisible(true);
        desktop.add(crewMemberStats);
    }

    // MODIFIES: this
    // EFFECTS: Creates save window and adds components
    public void createSaveWindow() {
        saveWindow = new JInternalFrame("Saving...", false, false, false, false);

        Container pane = saveWindow.getContentPane();
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
        addLabel("Would you like to save?", pane);
        pane.add(Box.createRigidArea(new Dimension(0, VGAP)));

        JPanel jpanel = addPanel(pane);
        jpanel.setLayout(new BoxLayout(jpanel, BoxLayout.X_AXIS));
        addButton("yes", "saveFile", false, jpanel);
        jpanel.add(Box.createRigidArea(new Dimension(HGAP, 0)));
        addButton("no", "doNotSaveFile", false, jpanel);

        saveWindow.setSize(200, 110);
        centreWindow(saveWindow);
        saveWindow.setVisible(true);
        desktop.add(saveWindow);
    }

    // MODIFIES: this
    // EFFECTS: Creates a JPanel and adds it to the given container
    private JPanel addPanel(Container pane) {
        JPanel jpanel = new JPanel();
        pane.add(jpanel);
        jpanel.setVisible(true);
        return jpanel;
    }

    // MODIFIES: this
    // EFFECTS: Creates an optionally centred JButton with a given action command and adds it to the given container
    private void addButton(String buttonLabel, String actionCommand, boolean isCentred, Container container) {
        JButton button = new JButton(buttonLabel);
        button.setActionCommand(actionCommand);
        button.addActionListener(this);
        if (isCentred) {
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
        }
        container.add(button);
    }

    // MODIFIES: this
    // EFFECTS: Creates a JLabel and adds it to the given container
    private void addLabel(String text, Container container) {
        label = new JLabel(text);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        container.add(label);
    }

    // MODIFIES: this
    // EFFECTS: Creates a scaled ImageIcon and adds it to the given container
    private void addImage(Container container) {
        ImageIcon image = new ImageIcon("images/Starship.png");
        Image scaleImage = image.getImage();
        Image newImage = scaleImage.getScaledInstance(300, 150, java.awt.Image.SCALE_SMOOTH);
        image = new ImageIcon(newImage);

        label = new JLabel();
        label.setIcon(image);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        container.add(label);
    }

    // MODIFIES: this, Starship, AwayMission, CrewMember
    // EFFECTS: Calls the appropriate action command method when a JButton is clicked
    @SuppressWarnings("methodlength")
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "loadFile":
                loadFileAction();
                break;
            case "doNotLoadFile":
                doNotLoadFileAction();
                break;
            case "setCaptainName":
                setCaptainNameAction();
                break;
            case "create mission":
                createMissionAction();
                break;
            case "start mission":
                startMissionAction();
                break;
            case "end mission":
                endMissionAction();
                break;
            case "emergency beam out":
                emergencyBeamOutAction();
                break;
            case "print mission log":
                printMissionLogAction();
                break;
            case "addAwayTeamMember":
                addAwayTeamMemberAction();
                break;
            case "statsCrewMember":
                createCrewMemberStats();
                break;
            case "removeAwayTeamMember":
                removeAwayTeamMemberAction();
                break;
            case "saveFile":
                saveFileAction();
            case "doNotSaveFile":
                doNotSaveFileAction();
            default:
                JOptionPane.showMessageDialog(desktop, "Button not handled.", "Error",
                        JOptionPane.ERROR_MESSAGE);
        }
    }

    // MODIFIES: this, Starship, AwayMission, CrewMember
    // EFFECTS: Loads Starship data from file
    //          if the current away mission is null creates mission creation window
    //          otherwise, creates mission manager and crew manager windows
    private void loadFileAction() {
        loadStarship();
        loadWindow.dispose();

        if (starship.getCurrentAwayMission() == null) {
            createMissionCreationWindow();
        } else {
            createMissionManagerWindow();
            createCrewManagerWindow();
        }
    }

    // MODIFIES: this, Starship, CrewMember
    // EFFECTS: Initializes Starship crew and creates input captain name window
    private void doNotLoadFileAction() {
        initializeCrew();
        loadWindow.dispose();
        createInputNameWindow();
    }

    // MODIFIES: this, Starship
    // EFFECTS: Updates Starship's Captain's name and creates mission creation window
    private void setCaptainNameAction() {
        String fn = textField1.getText();
        String ln = textField2.getText();

        if (!fn.equals("") && !ln.equals("")) {
            starship.setFirstNameOfCaptain(fn);
            starship.setLastNameOfCaptain(ln);
            welcomeCaptain();
            inputNameWindow.dispose();
            createMissionCreationWindow();
        } else {
            JOptionPane.showMessageDialog(desktop, "Please enter a valid name.", "Alert", JOptionPane.WARNING_MESSAGE);
        }
    }

    // MODIFIES: this, Starship, AwayMission
    // EFFECTS: creates a new away mission and creates mission manager and crew manager windows
    private void createMissionAction() {
        starship.createAwayMission();
        JOptionPane.showMessageDialog(desktop, "Created new away mission "
                + starship.getCurrentAwayMission().getAwayMissionID()
                + " on stardate " + starship.stardateToString(starship.getCurrentAwayMission().getStardate())
                + ".");
        missionCreationWindow.dispose();
        createMissionManagerWindow();
        createCrewManagerWindow();
    }

    // MODIFIES: this, Starship, AwayMission, CrewMember
    // EFFECTS: if the current away mission is not active and the away team is not empty, starts away mission
    //          if the current away mission is active creates a pop-up warning
    //          otherwise, creates creates a pop-up warning
    private void startMissionAction() {
        if (!starship.getCurrentAwayMission().getIsActive()
                && !starship.getCurrentAwayMission().getAwayTeam().isEmpty()) {
            starship.startAwayMission();
            JOptionPane.showMessageDialog(desktop, "Make it so.");
        } else if (starship.getCurrentAwayMission().getIsActive()) {
            JOptionPane.showMessageDialog(desktop,
                    "Away Mission has already started.", "Alert", JOptionPane.WARNING_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(desktop, "Please select an away team.", "Alert", JOptionPane.WARNING_MESSAGE);
        }
    }

    // MODIFIES: this, Starship, AwayMission, CrewMember
    // EFFECTS: if the current away mission is active, ends away mission, updates the away team Jlist and creates the
    //          mission creation window
    //          otherwise, creates a pop-up warning
    private void endMissionAction() {
        if (starship.getCurrentAwayMission().getIsActive()) {
            starship.endAwayMission();
            l2.removeAllElements();
            JOptionPane.showMessageDialog(desktop, "Good job number one.");
            missionManagerWindow.dispose();
            crewManagerWindow.dispose();
            createMissionCreationWindow();
        } else {
            JOptionPane.showMessageDialog(desktop, "Away Mission has not started.", "Alert",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    // MODIFIES: this, Starship, AwayMission, CrewMember
    // EFFECTS: if the current away mission is active, emergency beams out the away team, updates the away team Jlist,
    //          and creates the mission creation window
    //          otherwise, creates a pop-up warning
    private void emergencyBeamOutAction() {
        if (starship.getCurrentAwayMission().getIsActive()) {
            starship.emergencyBeamOut();
            l2.removeAllElements();
            JOptionPane.showMessageDialog(desktop, "Beam me up Scottie!");
            missionManagerWindow.dispose();
            crewManagerWindow.dispose();
            createMissionCreationWindow();
        } else {
            JOptionPane.showMessageDialog(desktop, "Away Mission has not started.", "Alert",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    // MODIFIES: this
    // EFFECTS: if the mission log is empty, creates a pop-up warning
    //          otherwise, creates a mission log print out window
    private void printMissionLogAction() {
        if (starship.getMissionLog().isEmpty()) {
            JOptionPane.showMessageDialog(desktop, "No previous missions.", "Alert", JOptionPane.WARNING_MESSAGE);
        } else {
            createAwayMissionLog();
        }
    }

    // MODIFIES: this, Starship, AwayMission, CrewMember
    // EFFECTS: if the crew member is dead, creates a pop-up warning
    //          if the away team does not contain the crew member(s) to add, add the crew member(s) to the away team and
    //          the away team JList
    private void addAwayTeamMemberAction() {
        int[] crewIndices = list1.getSelectedIndices();

        for (int i : crewIndices) {
            CrewMember cm = starship.getCrewMembers().get(i);

            if (cm.getHealthStatus() == HealthStatus.DEAD) {
                JOptionPane.showMessageDialog(desktop, "He's dead Jim.", "Alert", JOptionPane.WARNING_MESSAGE);
            } else {
                if (!starship.getCurrentAwayMission().getAwayTeam().contains(cm)) {
                    l2.addElement(cm.nameToString(true));
                    starship.getCurrentAwayMission().addCrewMemberToAwayTeam(cm);
                }
            }
        }
    }

    // MODIFIES: this, Starship, AwayMission, CrewMember
    // EFFECTS: removes the crew member(s) from the away team and the away team JList
    private void removeAwayTeamMemberAction() {
        int[] awayTeamIndices = list2.getSelectedIndices();

        for (int i = awayTeamIndices.length - 1; i >= 0; i--) {
            int n = awayTeamIndices[i];
            CrewMember cm = starship.getCurrentAwayMission().getAwayTeam().get(n);

            starship.getCurrentAwayMission().removeCrewMemberFromAwayTeam(cm);
            l2.removeElementAt(n);
        }
    }

    private void saveFileAction() {
        saveStarship();
        printLog(EventLog.getInstance());
        System.exit(0);
    }

    private void doNotSaveFileAction() {
        printLog(EventLog.getInstance());
        System.exit(0);
    }

    // MODIFIES: this, Starship, CrewMember
    // EFFECTS: initializes crew
    private void initializeCrew() {  //TODO input from JSON and make it a part of crew initialization
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

    // EFFECTS: returns mission log as a String
    public String awayMissionLogToString() {
        String awayMissionLog = "Mission log:";
        for (AwayMission am : starship.getMissionLog()) {
            String s;
            if (am.getIsObjectiveComplete()) {
                s = "complete";
            } else {
                s = "incomplete";
            }
            awayMissionLog = awayMissionLog + "\nMission ID: " + am.getAwayMissionID() + "\nStardate: "
                    + starship.stardateToString(am.getStardate()) + "\nObjective " + s + "\n";
        }
        return awayMissionLog;
    }

    // EFFECTS: returns stats on given crew member(s) as a String
    public String crewMemberStatsToString() {
        String crewMemberStats = "";
        int[] crewIndices = list1.getSelectedIndices();

        for (int i : crewIndices) {
            CrewMember cm = starship.getCrewMembers().get(i);
            crewMemberStats = crewMemberStats + cm.nameToString(false) + "\nRank: " + cm.getRank().name()
                    + "\nDivision: " + cm.getDivision().name() + "\nHealth Status: " + cm.getHealthStatus().name()
                    + "\n\n";
        }
        return crewMemberStats;
    }

    // EFFECTS: saves the starship data to file
    private void saveStarship() {
        try {
            jsonWriter.open();
            jsonWriter.write(starship);
            jsonWriter.close();
            JOptionPane.showMessageDialog(desktop, "Saved starship data for " + starship.getShipName()
                    + " (" + starship.getShipID() + ").");
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(desktop, "Unable to write to file " + JSON_STARSHIP, "Alert",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    // MODIFIES: this, Starship, AwayMission, CrewMember
    // EFFECTS: loads starship data from file
    private void loadStarship() {
        try {
            this.starship = jsonReader.read();
            if (starship.getCurrentAwayMission() != null) {
                for (CrewMember cm : starship.getCurrentAwayMission().getAwayTeam()) {
                    l2.addElement(cm.nameToString(true));
                }
            }
            welcomeCaptain();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(desktop, "Unable to read from file: " + JSON_STARSHIP, "Alert",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    private void printLog(EventLog eventLog) {
        for (Event next : eventLog) {
            System.out.println(next.toString());
        }
    }

    // EFFECTS: Creates a welcome Caption pop-up window
    private void welcomeCaptain() {
        JOptionPane.showMessageDialog(desktop, "Welcome Captain " + starship.getLastNameOfCaptain());
    }

    // EFFECTS: Centres a given window in the desktop
    private void centreWindow(Container pane) {
        pane.setLocation((DESKTOP_WINDOW_WIDTH - pane.getWidth()) / 2, (DESKTOP_WINDOW_HEIGHT - pane.getHeight()) / 2);
    }
}
