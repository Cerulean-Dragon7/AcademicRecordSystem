import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ProfilePanel extends JPanel {
    private JLabel userName;
    private JButton back;
    private JButton logout;
    private JButton summit;
    private JPanel headerPanel;
    private JPanel leftPanel;
    private JPanel rightPanel;
    private JPanel logoutPanel;
    private JPanel logoutRightPanel;
    private JPanel logoutLeftPanel;
    private JPanel contentPanel;
    private String[] nameArray = {"First Name", "Last Name", "Address", "Phone Number", "email"};
    public ProfilePanel(){
        initComponent();

    }
    private void initComponent(){
        headerPanel = new JPanel(new BorderLayout());

        //show the username
        userName = new JLabel("User Name");

        leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        //profile button
        back = new JButton("back");

        rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        //logout button
        logout = new JButton("logout");
        logoutRightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        summit = new JButton("summit");
        logoutLeftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        logoutPanel = new JPanel(new BorderLayout());

        contentPanel = new JPanel(new GridLayout(5,1,0,5));

        setLayout();
        addComponent();
    }

    private void setLayout(){
        this.setLayout(new BorderLayout());

        headerPanel.setSize(800,100);   //set the panel size

        userName.setPreferredSize(new Dimension(100,30));
        userName.setFont(new Font("Arial", Font.PLAIN, 16));

        back.setPreferredSize(new Dimension(100,30));

        headerPanel.setBorder(new MatteBorder(0,0,1,0,Color.BLACK));

        summit.setPreferredSize(new Dimension(100,20));

        logout.setPreferredSize(new Dimension(70,20));

        logoutPanel.setSize(800,50);
        logoutPanel.setBorder(new MatteBorder(1,0,0,0,Color.BLACK));

        this.setVisible(true);
    }

    private void addComponent(){
        leftPanel.add(userName);
        rightPanel.add(back);
        headerPanel.add(leftPanel,BorderLayout.WEST);
        headerPanel.add(rightPanel,BorderLayout.EAST);

        logoutRightPanel.add(logout);
        logoutLeftPanel.add(summit);
        logoutPanel.add(logoutLeftPanel,  BorderLayout.WEST);
        logoutPanel.add(logoutRightPanel, BorderLayout.EAST);

        for(int i = 0; i < nameArray.length; i++){
            JPanel basePanel = new JPanel(new BorderLayout());
            JPanel nameLabelPanel = new JPanel();
            JPanel textPanel = new JPanel();
            SpringLayout springLayout = new SpringLayout();

            JLabel nameLabel = new JLabel(nameArray[i]+":");
            nameLabel.setPreferredSize(new Dimension(100,100));
            nameLabel.setFont(new Font("Arial", Font.PLAIN, 16));

            nameLabelPanel.add(nameLabel);

            JTextField editText = new JTextField();
            editText.setPreferredSize(new Dimension(200,20));


            textPanel.setLayout(springLayout);
            textPanel.add(editText);

            SpringLayout.Constraints constraints = springLayout.getConstraints(editText);
            constraints.setX(Spring.constant(5));
            constraints.setY(Spring.constant(49));

            basePanel.setBorder(BorderFactory.createLineBorder(Color.black));
            basePanel.add(nameLabelPanel,BorderLayout.WEST);
            basePanel.add(textPanel,BorderLayout.CENTER);

            setEventListener();

            contentPanel.add(basePanel);
        }

        this.add(headerPanel,BorderLayout.NORTH);   //add header panel to the bottom panel
        this.add(logoutPanel,BorderLayout.SOUTH);
        this.add(contentPanel, BorderLayout.CENTER);
    }

    private void setEventListener(){
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GUIFrame guiFrame = (GUIFrame) SwingUtilities.getWindowAncestor(ProfilePanel.this);
                guiFrame.changePanel(GUIFrame.MAINPANEL);
            }
        });

        logout.addActionListener(new ActionListener() {
            //go to login screen
            //disconnect to db
            @Override
            public void actionPerformed(ActionEvent e) {
                GUIFrame guiFrame = (GUIFrame) SwingUtilities.getWindowAncestor(ProfilePanel.this);
                guiFrame.changePanel(GUIFrame.LOGINPANEL);
            }
        });
    }
}
