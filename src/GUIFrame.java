import javax.swing.*;
import java.awt.*;

public class GUIFrame extends JFrame {
    public static final String LOGINPANEL = "login";
    public static final String MAINPANEL = "main";
    private CardLayout cardLayout;
    private JPanel cardPanel;

    //hi
    public GUIFrame() {
        setTitle("Academic Record System");
        //specifies the width and height of the window in pixels
        setSize(800, 700);
        setResizable(false); // can't change the size of the window
        //the program terminate when the use press the close button
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        LoginPanel loginPanel = new LoginPanel();
        this.add(loginPanel);

        initialCardPanel(); //create card Panel and card layout
        changePanel(LOGINPANEL);
        setVisible(true); // Display frame
    }

    public static void main(String[] args) {
        new GUIFrame();
    }

    public void changePanel(String name) {
        cardLayout.show(cardPanel, name);
    }

    private void initialCardPanel() {
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        LoginPanel loginPanel = new LoginPanel();
        // Add the panels to the card panel
        addCard(loginPanel, LOGINPANEL);
        // Add the card panel to the frame
        this.add(cardPanel);
    }

    public void addCard(JPanel jPanel, String name) {
        cardPanel.add(jPanel, name);
    }
}
