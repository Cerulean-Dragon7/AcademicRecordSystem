import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;

public class MainPanel extends JPanel {
    private JLabel userName;
    private JButton profile;
    private JButton logout;
    private JPanel headerPanel;
    private JPanel leftPanel;
    private JPanel rightPanel;
    private JPanel logoutPanel;
    private JPanel coursePanel;
    public MainPanel(){
        initBaseComponent();
        studentView();
    }

    private void initBaseComponent(){   //create the header
        this.setLayout(new BorderLayout());

        headerPanel = new JPanel(new BorderLayout());
        headerPanel.setSize(800,100);   //set the panel size

        //show the username
        userName = new JLabel("Test");
        userName.setPreferredSize(new Dimension(100,30));
        userName.setFont(new Font("Arial", Font.PLAIN, 16));

        leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        leftPanel.add(userName);

        //profile button
        profile = new JButton("Profile");
        profile.setPreferredSize(new Dimension(100,30));

        rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rightPanel.add(profile);

        //header panel show username and button
        headerPanel.add(leftPanel,BorderLayout.WEST);
        headerPanel.add(rightPanel,BorderLayout.EAST);
        headerPanel.setBorder(new MatteBorder(0,0,1,0,Color.BLACK));

        //logout button
        logout = new JButton("logout");
        logout.setPreferredSize(new Dimension(70,20));

        logoutPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        logoutPanel.setSize(800,50);
        logoutPanel.add(logout);


        this.add(headerPanel,BorderLayout.NORTH);   //add header panel to the bottom panel
        this.add(logoutPanel,BorderLayout.SOUTH);
        this.setVisible(true);
    }

    private void studentView(){
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(Color.BLUE);

        JLabel courseTitle = new JLabel("My Course");
        courseTitle.setBackground(Color.GREEN);
        courseTitle.setPreferredSize(new Dimension(200,50));

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        for(int i = 0;i < 100;i++){
            contentPanel.add(new JLabel("label"+ i));
        }

        JScrollPane coursePanel = new JScrollPane(contentPanel);
        coursePanel.setBackground(Color.red);
        coursePanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        coursePanel.setPreferredSize(new Dimension(300,500));


        bottomPanel.add(courseTitle,BorderLayout.NORTH);
        bottomPanel.add(coursePanel,BorderLayout.SOUTH);

        this.add(bottomPanel,BorderLayout.CENTER);
    }
}
