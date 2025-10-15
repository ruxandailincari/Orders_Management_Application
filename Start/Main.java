package org.example.Start;

import org.example.Presentation.View;

import javax.swing.*;

/**
 * Class used to start the application
 * @author Ilincari Ruxanda
 * @since May 2025
 */
public class Main {
    /**
     * Method used to start the application
     * @param args from the command line that aren't used here
     */
    public static void main(String[] args) {
        JFrame frame = new View("Orders Management");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
