package ui;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
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

    private JPanel jpanel;
    private JButton button;
    private JLabel label;
    private JTextField textField1;
    private JTextField textField2;
    private JList<String> list1;
    private JList<String> list2;

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

//        addWindowListener (new WindowAdapter() {
//            public void windowClosing (WindowEvent e) {
//                dispose();
//            }
//        });

        setLocationRelativeTo(null);
        setVisible(true);
    }

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

    public void createMissionManagerWindow() { //TODO set size and location of crew list man, mission man, mission log (accounting for its apprerance in both cases0), and stats
        missionManagerWindow = new JInternalFrame("Mission Manager", false, false, false, false);

        Container pane = missionManagerWindow.getContentPane();
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));

        addButton("start mission", "start mission", true, pane);
        addButton("end mission", "end mission", true, pane);
        addButton("emergency beam out", "emergency beam out", true, pane);
        addButton("print mission log", "print mission log", true, pane);

        missionManagerWindow.setSize(225, 165);
        missionManagerWindow.setLocation((DESKTOP_WINDOW_WIDTH - missionManagerWindow.getWidth()) / 2, (desktop.getHeight() - missionManagerWindow.getHeight()) / 2);
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
        addLabel("Crew Members", jpanel1);
        pane.add(Box.createRigidArea(new Dimension(0, VGAP)));
        createCrewMemberList(jpanel1);
        pane.add(Box.createRigidArea(new Dimension(0, VGAP)));
        addButton("add", "addAwayTeamMember", true, jpanel1);
        pane.add(Box.createRigidArea(new Dimension(0, VGAP)));
        addButton("stats", "statsCrewMember", true, jpanel1);

        JPanel jpanel2 = addPanel(pane);
        jpanel2.setLayout(new BoxLayout(jpanel2, BoxLayout.Y_AXIS));
        addLabel("Away Team", jpanel2);
        pane.add(Box.createRigidArea(new Dimension(0, VGAP)));
        createAwayTeamList(jpanel2);
        pane.add(Box.createRigidArea(new Dimension(0, VGAP)));
        addButton("remove", "removeAwayTeamMember", true, jpanel2);
    }

    public void createAwayMissionLog() {
        JInternalFrame awayMissionLog = new JInternalFrame("Away Mission Log", false, true, false, false);
        JTextArea missionLog = new JTextArea(awayMissionLogToString());
        JScrollPane scroll = new JScrollPane(missionLog);
        scroll.setPreferredSize(new Dimension(150, 150));

        awayMissionLog.add(scroll);
        awayMissionLog.pack();
        awayMissionLog.setVisible(true);
        desktop.add(awayMissionLog);
    }

    public void createCrewMemberStats() {
        JInternalFrame crewMemberStats = new JInternalFrame("Crew Member Stats", false, true, false, false);
        JTextArea crewStats = new JTextArea(crewMemberStatsToString());
        JScrollPane scroll = new JScrollPane(crewStats);
        scroll.setPreferredSize(new Dimension(240, 300));

        crewMemberStats.add(scroll);
        crewMemberStats.pack();
        crewMemberStats.setVisible(true);
        desktop.add(crewMemberStats);
    }

    private void createCrewMemberList(Container pane) {
        for (CrewMember cm : starship.getCrewMembers()) {
            l1.addElement(cm.nameToString(true));
        }

        list1 = new JList<>(l1);
        list1.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JScrollPane listScroller = new JScrollPane(list1);
        listScroller.setPreferredSize(new Dimension(125, 200));

        pane.add(listScroller);
    }

    private void createAwayTeamList(Container pane) {
        list2 = new JList<>(l2);
        list2.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JScrollPane listScroller = new JScrollPane(list2);
        listScroller.setPreferredSize(new Dimension(125, 200));

        pane.add(listScroller);
    }

    public void createSaveWindow() {

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
        ImageIcon image = new ImageIcon("images/Starship.png");
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

            if (starship.getCurrentAwayMission() == null) {
                createMissionCreationWindow();
            } else {
                createMissionManagerWindow();
                createCrewManagerWindow();
            }
        }

        if (e.getActionCommand().equals("doNotLoadFile")) {
            initializeCrew();
            loadWindow.dispose();
            createInputNameWindow();
        }

        if (e.getActionCommand().equals("setCaptainName")) {
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

        if (e.getActionCommand().equals("create mission")) {
            starship.createAwayMission();
            JOptionPane.showMessageDialog(desktop, "Created new away mission " + starship.getCurrentAwayMission().getAwayMissionID()
                    + " on stardate " + starship.stardateToString(starship.getCurrentAwayMission().getStardate())
                    + ".");
            missionCreationWindow.dispose();
            createMissionManagerWindow();
            createCrewManagerWindow();
        }

        if (e.getActionCommand().equals("start mission")) {
            if (!starship.getCurrentAwayMission().getIsActive()
                    && !starship.getCurrentAwayMission().getAwayTeam().isEmpty()) {
                starship.startAwayMission();
                JOptionPane.showMessageDialog(desktop, "Make it so.");
            } else if (starship.getCurrentAwayMission().getIsActive()) {
                JOptionPane.showMessageDialog(desktop, "Away Mission has already started.", "Alert", JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(desktop, "Please select an away team.", "Alert", JOptionPane.WARNING_MESSAGE);
            }
        }

        if (e.getActionCommand().equals("end mission")) {
            if (starship.getCurrentAwayMission().getIsActive()) {
                starship.endAwayMission();
                JOptionPane.showMessageDialog(desktop, "Good job number one.");
                missionManagerWindow.dispose();
                crewManagerWindow.dispose();
                createMissionCreationWindow();
            } else {
                JOptionPane.showMessageDialog(desktop, "Away Mission has not started.", "Alert", JOptionPane.WARNING_MESSAGE);
            }
        }

        if (e.getActionCommand().equals("emergency beam out")) {
            if (starship.getCurrentAwayMission().getIsActive()) {
                starship.emergencyBeamOut();
                JOptionPane.showMessageDialog(desktop, "Beam me up Scottie!");
                missionManagerWindow.dispose();
                crewManagerWindow.dispose();
                createMissionCreationWindow();
            } else {
                JOptionPane.showMessageDialog(desktop, "Away Mission has not started.", "Alert", JOptionPane.WARNING_MESSAGE);
            }
        }

        if (e.getActionCommand().equals("print mission log")) {
            if (starship.getMissionLog().isEmpty()) {
                JOptionPane.showMessageDialog(desktop, "No previous missions.", "Alert", JOptionPane.WARNING_MESSAGE);
            } else {
                createAwayMissionLog();
            }
        }

        if (e.getActionCommand().equals("addAwayTeamMember")) {
            int[] crewIndices = list1.getSelectedIndices();
            String popupMessage = "";

            for (int i : crewIndices) {
                CrewMember cm = starship.getCrewMembers().get(i);

                if (cm.getHealthStatus() == HealthStatus.DEAD) {
                    JOptionPane.showMessageDialog(desktop, "He's dead Jim.", "Alert", JOptionPane.WARNING_MESSAGE);
                } else {
                    if (!starship.getCurrentAwayMission().getAwayTeam().contains(cm)) {
                        l2.addElement(cm.nameToString(true));
                        starship.getCurrentAwayMission().addCrewMemberToAwayTeam(cm);
                    }

                    if (starship.getCurrentAwayMission().getIsActive()) { //TODO potnetially make these messages shorter/combined
                        popupMessage = popupMessage + "\n" + cm.nameToString(true) + " has been transported off of the starship.";
                    } else {
                        popupMessage = popupMessage + "\n" + cm.nameToString(true) + " has been added to the away team.";
                    }
                }
            }

            JOptionPane.showMessageDialog(desktop, popupMessage);
        }

        if (e.getActionCommand().equals("statsCrewMember")) {
            createCrewMemberStats();
        }

        if (e.getActionCommand().equals("removeAwayTeamMember")) { //TODO fix message
            int[] awayTeamIndices = list2.getSelectedIndices();
            String popupMessage = "";

            for (int i = awayTeamIndices.length - 1; i >= 0; i--) {
                int n = awayTeamIndices[i];
                CrewMember cm = starship.getCurrentAwayMission().getAwayTeam().get(n);
                starship.getCurrentAwayMission().removeCrewMemberFromAwayTeam(cm);
                l2.removeElementAt(n);

                if (starship.getCurrentAwayMission().getIsActive()) {
                    popupMessage = popupMessage + "\n" + cm.nameToString(true) + " has been transported back to the starship.";
                } else {
                    popupMessage = popupMessage + "\n" + cm.nameToString(true) +  " has been removed from the away team.";
                }
            }

            if (awayTeamIndices.length > 0) {
                JOptionPane.showMessageDialog(desktop, popupMessage);
            }
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

    // EFFECTS: prints mission log
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

    // EFFECTS: prints stats on given crew member
    public String crewMemberStatsToString() {
        String crewMemberStats = "";
        int[] crewIndices = list1.getSelectedIndices();

        for (int i : crewIndices) {
            CrewMember cm = starship.getCrewMembers().get(i);
            crewMemberStats = crewMemberStats + cm.nameToString(false) + "\nRank: " + cm.getRank().name() + "\nDivision: " + cm.getDivision().name() + "\nHealth Status: " + cm.getHealthStatus().name() + "\n\n";
        }
        return crewMemberStats;
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
            for (CrewMember cm : starship.getCurrentAwayMission().getAwayTeam()) {
                l2.addElement(cm.nameToString(true));
            }
            welcomeCaptain();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(desktop, "Unable to read from file: " + JSON_STARSHIP, "Alert", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void welcomeCaptain() {
        JOptionPane.showMessageDialog(desktop, "Welcome Captain " + starship.getLastNameOfCaptain());
    }

    private void centreWindow(Container pane) {
        pane.setLocation((DESKTOP_WINDOW_WIDTH - pane.getWidth()) / 2, (DESKTOP_WINDOW_HEIGHT - pane.getHeight()) / 2);
    }
}
