package io.stefano.view;

import javax.swing.*;

public class BaseFrame extends JFrame {
    public final static int WIDTH = 800;
    public final static int HEIGHT = 500;

    public final static String TITLE = "Rubrica";

    public BaseFrame() {
        setResizable(false);
        setTitle(TITLE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setLayout(null);
    }
}
