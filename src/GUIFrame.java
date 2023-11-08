import javax.swing.*;
import java.awt.*;

public class GUIFrame extends JFrame {
    public  static final int LOGINPANEL = 0;
    public static final int MAINPANEL = 1;
    private LoginPanel loginPanel;
    private MainPanel mainPanel;
    private CardLayout cardLayout;
    private JPanel cardPanel;

    public GUIFrame(){
        setTitle("Academic Record System");
        //specifies the width and height of the window in pixels
        setSize(800,700);
        setResizable(false); // can't change the size of the window
        //the program terminate when the use press the close button
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initialCardPanel(); //create card Panel and card layout
        changePanel(GUIFrame.LOGINPANEL);
        setVisible(true); // Display frame
    }


    public void changePanel(int state) {
        System.out.println(state);
        switch (state) {
            case 0:
                cardLayout.show(cardPanel, "login");
                break;
            case 1:
                cardLayout.show(cardPanel, "main");
                break;
        }
    }

    private void initialCardPanel(){
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        loginPanel = new LoginPanel();
        mainPanel = new MainPanel();

        // Add the panels to the card panel
        cardPanel.add(loginPanel, "login");
        cardPanel.add(mainPanel, "main");

        // Add the card panel to the frame
        this.add(cardPanel);
    }


    public static void main(String[] args){
        GUIFrame guiFrame = new GUIFrame();

    }
}
