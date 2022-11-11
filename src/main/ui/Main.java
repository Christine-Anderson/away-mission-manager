package ui;

import java.io.FileNotFoundException;

/**
 * Runs the Away Mission Manager App
 */
public class Main {
    public static void main(String[] args) {
        try {
            new AwayMissionManagerGUI();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }
    }
}
