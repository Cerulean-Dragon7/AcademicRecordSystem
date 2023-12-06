import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class LoginPanel extends JPanel {

    private JButton loginButton;
    private JButton forgetPassword;
    private JTextField userText;
    private JPasswordField passwordText;
    private JLabel icon;
    private JPanel componentsPanel;

    public LoginPanel() {
        placeComponents();
        addComponents();
        setVisible(true);
    }

    private void addComponents() {   //for add title name
        this.setLayout(new BorderLayout());
        this.add(componentsPanel, BorderLayout.CENTER);

        icon = new JLabel("Academic Record System", SwingConstants.CENTER);
        icon.setFont(new Font("Arial", Font.BOLD, 30));
        this.add(icon, BorderLayout.NORTH);
    }

    private void placeComponents() {    //add center components
        componentsPanel = new JPanel(new GridBagLayout());

        JLabel userLabel = new JLabel("User ID");
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 10, 10, 10);
        componentsPanel.add(userLabel, gbc);

        userText = new JTextField(20);
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);
        componentsPanel.add(userText, gbc);

        JLabel passwordLabel = new JLabel("Password");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 10, 10, 10);
        componentsPanel.add(passwordLabel, gbc);

        passwordText = new JPasswordField(20);
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);
        componentsPanel.add(passwordText, gbc);

        loginButton = new JButton("Login");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 10, 10, 10);
        componentsPanel.add(loginButton, gbc);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String user = userText.getText();
                String password = new String(passwordText.getPassword());
                System.out.println("Username: " + user + " Password: " + password);
                String errorMessage = null;

                // Check login and password against the database
                try {
                    Connection connection = DriverManager.getConnection("jdbc:postgresql://dpg-clg52g7jc5ks73ebv3kg-a.singapore-postgres.render.com:5432/academicsysdb", "academicsysdb_user", "ZjIvqGcPMEmfe81jZZlNZVamy9XFvlL2");

                    // Check admin table
                    // Check admin table
                    String adminQuery = "SELECT * FROM admin WHERE admin_id = ? AND password = ?";
                    PreparedStatement adminStatement = connection.prepareStatement(adminQuery);
                    adminStatement.setString(1, user);
                    adminStatement.setString(2, password);
                    ResultSet adminResultSet = adminStatement.executeQuery();
                    String usertype = "";

                    if (adminResultSet.next()) {
                        System.out.println("Logged in as admin");
                        // Code for admin login
                        // ...
                        usertype = "2";
                        GUIFrame guiFrame = (GUIFrame) SwingUtilities.getWindowAncestor(LoginPanel.this);
                        MainPanel mainPanel = new MainPanel(Integer.parseInt(usertype), user);
                        guiFrame.addCard(mainPanel, GUIFrame.MAINPANEL);
                        guiFrame.changePanel(GUIFrame.MAINPANEL);
                        adminResultSet.close();
                        adminStatement.close();
                        connection.close();
                        userText.setText("");
                        passwordText.setText("");
                        return;
                    }else{
                        errorMessage = "wrong User ID or Password";
                    }

                    // Check teacher table
                    String teacherQuery = "SELECT * FROM teacher WHERE teacher_id = ? AND password = ?";
                    PreparedStatement teacherStatement = connection.prepareStatement(teacherQuery);
                    teacherStatement.setString(1, user);
                    teacherStatement.setString(2, password);
                    ResultSet teacherResultSet = teacherStatement.executeQuery();

                    if (teacherResultSet.next()) {
                        System.out.println("Logged in as teacher");
                        // Code for teacher login
                        // ...
                        usertype = "1";
                        GUIFrame guiFrame = (GUIFrame) SwingUtilities.getWindowAncestor(LoginPanel.this);
                        MainPanel mainPanel = new MainPanel(Integer.parseInt(usertype), user);
                        guiFrame.addCard(mainPanel, GUIFrame.MAINPANEL);
                        guiFrame.changePanel(GUIFrame.MAINPANEL);
                        teacherResultSet.close();
                        teacherStatement.close();
                        connection.close();
                        userText.setText("");
                        passwordText.setText("");
                        return;
                    }else{
                        errorMessage = "wrong User ID or Password";
                    }

                    // Check student table
                    String studentQuery = "SELECT * FROM student WHERE student_id = ? AND password = ?";
                    PreparedStatement studentStatement = connection.prepareStatement(studentQuery);
                    studentStatement.setString(1, user);
                    studentStatement.setString(2, password);
                    ResultSet studentResultSet = studentStatement.executeQuery();

                    if (studentResultSet.next()) {
                        System.out.println("Logged in as student");
                        // Code for student login
                        // ...
                        usertype = "0";
                        GUIFrame guiFrame = (GUIFrame) SwingUtilities.getWindowAncestor(LoginPanel.this);
                        MainPanel mainPanel = new MainPanel(Integer.parseInt(usertype), user);
                        guiFrame.addCard(mainPanel, GUIFrame.MAINPANEL);
                        guiFrame.changePanel(GUIFrame.MAINPANEL);
                        studentResultSet.close();
                        studentStatement.close();
                        connection.close();
                        userText.setText("");
                        passwordText.setText("");
                        return;
                    }else{
                        errorMessage = "wrong User ID or Password";
                    }

                    if(errorMessage != null){
                        JOptionPane.showMessageDialog(LoginPanel.this, errorMessage, "Error", JOptionPane.PLAIN_MESSAGE);
                    }

                    System.out.println("Invalid credentials!");
                    adminResultSet.close();
                    adminStatement.close();
                    teacherResultSet.close();
                    teacherStatement.close();
                    studentResultSet.close();
                    studentStatement.close();
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        forgetPassword = new JButton("Forget Password");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(1, 1, 1, 1);
        componentsPanel.add(forgetPassword, gbc);

        forgetPassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Forget Password button clicked");
            }
        });
    }
}