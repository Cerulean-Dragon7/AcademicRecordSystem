import com.sun.tools.javac.Main;

import javax.swing.*;
import javax.swing.plaf.BorderUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LoginPanel extends JPanel {

    private JButton loginButton;
    private JButton forgetPassword;
    private JTextField userText;
    private JPasswordField passwordText;
    private JLabel icon;
    private JPanel componentsPanel;
    private JLabel userLabel;
    private JLabel passwordLabel;

    public LoginPanel() {
        initComponent();
        setVisible(true);
    }
    private void initComponent(){
        componentsPanel = new JPanel(new GridBagLayout());
        icon = new JLabel("Academic Record System",SwingConstants.CENTER);
        userLabel = new JLabel("User ID");
        userText = new JTextField("t4282311",20);
        passwordLabel = new JLabel("Password");
        passwordText = new JPasswordField("Skk054884",20);
        loginButton = new JButton("Login");
        forgetPassword = new JButton("forget password");

        setLayout();
        addComponent();
    }
    private void addEventListener(){
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userID = userText.getText();
                String password = new String(passwordText.getPassword());
                System.out.println("Username: " + userID + " Password: " + password);
                User user = userVerify(userID,password);

                if(user == null){
                    System.out.println("user is null");
                }else {
                    //change the screen to main screen
                    userText.setText("");
                    passwordText.setText("");
                    GUIFrame guiFrame = (GUIFrame) SwingUtilities.getWindowAncestor(LoginPanel.this);
                    MainPanel mainPanel = new MainPanel(user);
                    guiFrame.addCard(mainPanel,GUIFrame.MAINPANEL);
                    guiFrame.changePanel(GUIFrame.MAINPANEL);
                }



            }
        });

        forgetPassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("forgetPassword button click");
            }
        });
    }
    private void setLayout(){
        this.setLayout(new BorderLayout());

        icon.setFont(new Font("Arial", Font.BOLD, 30));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 10, 10, 10);
        componentsPanel.add(userLabel, gbc);


        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);
        componentsPanel.add(userText, gbc);


        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 10, 10, 10);
        componentsPanel.add(passwordLabel, gbc);


        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);
        componentsPanel.add(passwordText, gbc);


        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 10, 10, 10);
        componentsPanel.add(loginButton, gbc);


        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(1, 1, 1, 1);
        componentsPanel.add(forgetPassword, gbc);
    }
    private void addComponent(){
        this.add(componentsPanel,BorderLayout.CENTER);
        this.add(icon, BorderLayout.NORTH);

        addEventListener();
    }
    private User userVerify(String userid, String password){
        ResultSet userRS = DBConnection.getUserByID(userid,password);

        try{

            if(userRS.next()) {
                switch (userid.charAt(0)) {
                    case 'a':
                        return new Admin(userRS.getString(1),userRS.getString(2),userRS.getString(3),userRS.getString(4),
                                userRS.getString(5),userRS.getString(6),userRS.getString(7));
                    case 's' :
                        return new Student(userRS.getString(1),userRS.getString(2),userRS.getString(3),userRS.getString(4),
                                userRS.getString(5),userRS.getString(6),userRS.getString(7));
                    case 't' :
                        return new Teacher(userRS.getString(1),userRS.getString(2),userRS.getString(3),userRS.getString(4),
                                userRS.getString(5),userRS.getString(6),userRS.getString(7));
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
