import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class DisplayProfileData extends JFrame {
    private JLabel IDLabel;
    private JLabel studentIDLabel;
    private JLabel firstNameLabel;
    private JLabel lastNameLabel;
    private JLabel emailLabel;
    private JLabel phoneNumberLabel;
    private JLabel addressLabel;
    private JLabel passwordLabel;

    public DisplayProfileData(String user) {
        // Set UI and initialize components
        setTitle("Profile");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(7, 3, 10, 10));

        // Create labels
        IDLabel= new JLabel();
        studentIDLabel = new JLabel();
        firstNameLabel = new JLabel();
        lastNameLabel = new JLabel();
        emailLabel = new JLabel();
        phoneNumberLabel = new JLabel();
        addressLabel = new JLabel();
        passwordLabel = new JLabel();

        // Add labels to the main panel
        mainPanel.add(IDLabel);
        mainPanel.add(studentIDLabel);
        mainPanel.add(new JLabel());

        mainPanel.add(new JLabel("First Name: "));
        mainPanel.add(firstNameLabel);
        JButton firstNameButton = new JButton("Update");
        firstNameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String newFirstName = JOptionPane.showInputDialog(DisplayProfileData.this, "Please enter a new first name:",firstNameLabel.getText());
                if (newFirstName != null) {
                    if(InputValidation.checkFirstName(newFirstName)){
                        updateStudentData(user, "first_name", newFirstName);
                        firstNameLabel.setText(newFirstName);
                    }else {
                        JOptionPane.showMessageDialog(DisplayProfileData.this,"First name only contain 1-20 character","Error",JOptionPane.PLAIN_MESSAGE);
                    }
                }
            }
        });
        mainPanel.add(firstNameButton);

        mainPanel.add(new JLabel("Last Name: "));
        mainPanel.add(lastNameLabel);
        JButton lastNameButton = new JButton("Update");
        lastNameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String newLastName = JOptionPane.showInputDialog(DisplayProfileData.this, "Please enter a new last name:",lastNameLabel.getText());
                if (newLastName != null) {
                    if(InputValidation.checkLastName(newLastName)){
                        updateStudentData(user, "last_name", newLastName);
                        lastNameLabel.setText(newLastName);
                    }else {
                        JOptionPane.showMessageDialog(DisplayProfileData.this,"Last name only contain 1-20 character","Error",JOptionPane.PLAIN_MESSAGE);
                    }

                }
            }
        });
        mainPanel.add(lastNameButton);

        mainPanel.add(new JLabel("Email: "));
        mainPanel.add(emailLabel);
        JButton emailButton = new JButton("Update");
        emailButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String newEmail = JOptionPane.showInputDialog(DisplayProfileData.this, "Please enter a new email:",emailLabel.getText());
                if (newEmail != null) {
                    if(InputValidation.checkEmail(newEmail)){
                        updateStudentData(user, "email", newEmail);
                        emailLabel.setText(newEmail);
                    }else {
                        JOptionPane.showMessageDialog(DisplayProfileData.this,"Email only contain 1-50 character and must include @ character","Error",JOptionPane.PLAIN_MESSAGE);
                    }
                }
            }
        });
        mainPanel.add(emailButton);

        mainPanel.add(new JLabel("Phone number: "));
        mainPanel.add(phoneNumberLabel);
        JButton phoneNumberButton = new JButton("Update");
        phoneNumberButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String newPhoneNumber = JOptionPane.showInputDialog(DisplayProfileData.this, "Please enter a new phone number:",phoneNumberLabel.getText());
                if (newPhoneNumber != null) {
                    if(InputValidation.checkPhoneNumber(newPhoneNumber)){
                        updateStudentData(user, "phone_num", newPhoneNumber);
                        phoneNumberLabel.setText(newPhoneNumber);
                    }else {
                        JOptionPane.showMessageDialog(DisplayProfileData.this,"Phone number need to have exactly 8-10 number","Error",JOptionPane.PLAIN_MESSAGE);
                    }
                }
            }
        });
        mainPanel.add(phoneNumberButton);

        mainPanel.add(new JLabel("address: "));
        mainPanel.add(addressLabel);
        JButton addressButton = new JButton("Update");
        addressButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String newAddress = JOptionPane.showInputDialog(DisplayProfileData.this, "Please enter a new address:",addressLabel.getText());
                if (newAddress != null) {
                    if(InputValidation.checkAddress(newAddress)){
                        updateStudentData(user, "address", newAddress);
                        addressLabel.setText(newAddress);
                    }else {
                        JOptionPane.showMessageDialog(DisplayProfileData.this,"Address need to have exactly 1-50 character","Error",JOptionPane.PLAIN_MESSAGE);
                    }
                }
            }
        });
        mainPanel.add(addressButton);

        mainPanel.add(new JLabel("password: "));
        mainPanel.add(passwordLabel);
        JButton passwordButton = new JButton("Update");
        passwordButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String newPassword = JOptionPane.showInputDialog(DisplayProfileData.this, "Please enter a new address:",passwordLabel.getText());
                if (newPassword != null) {
                    if(InputValidation.checkPassword(newPassword)){
                        updateStudentData(user, "password", newPassword);
                        passwordLabel.setText(newPassword);
                    }else {
                        JOptionPane.showMessageDialog(DisplayProfileData.this, "Password need to have at least one lower case letter\n" +
                                "at least one upper case letter \n at least one number \n at least 8 character", "Error", JOptionPane.PLAIN_MESSAGE);
                    }
                }
            }
        });
        mainPanel.add(passwordButton);

        // Add the main panel to the content pane
        getContentPane().add(mainPanel, BorderLayout.CENTER);
        // Retrieve and display student data
        retrieveStudentData(user);
        // Set window size and center it on the screen
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private void retrieveStudentData(String userID) {
        String url = "jdbc:postgresql://dpg-clg52g7jc5ks73ebv3kg-a.singapore-postgres.render.com:5432/academicsysdb";
        String user = "academicsysdb_user";
        String password = "ZjIvqGcPMEmfe81jZZlNZVamy9XFvlL2";
        try {
            // Create a connection to the database
            Connection connection = DriverManager.getConnection(url, user, password);
            String query = "";
            // Create a statement and execute a query to retrieve student data
            if(userID.charAt(0) == 's'){
                query = "SELECT * FROM student WHERE student_id = ?";
            }else if(userID.charAt(0) == 't'){
                query = "SELECT * FROM teacher WHERE teacher_id = ?";
            }else {
                query = "SELECT * FROM admin WHERE admin_id = ?";
            }

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, userID);
            ResultSet resultSet = statement.executeQuery();

            // Retrieve the student data from the result set and set the labels
            if (resultSet.next()) {
                if(userID.charAt(0) == 's'){
                    IDLabel.setText("StudentID");
                    studentIDLabel.setText(resultSet.getString("student_id"));
                }else if(userID.charAt(0) == 't'){
                    IDLabel.setText("TeacherID");
                    studentIDLabel.setText(resultSet.getString("teacher_id"));
                }else {
                    studentIDLabel.setText(resultSet.getString("admin_id"));
                }
                firstNameLabel.setText(resultSet.getString("first_name"));
                lastNameLabel.setText(resultSet.getString("last_name"));
                emailLabel.setText(resultSet.getString("email"));
                phoneNumberLabel.setText(resultSet.getString("phone_num"));
                addressLabel.setText(resultSet.getString("address"));
                passwordLabel.setText(resultSet.getString("password"));
            }

            // Close the resources
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            LogHandler.error(e);
        }
    }

    private void updateStudentData(String userID, String column, String newValue) {
        String url = "jdbc:postgresql://dpg-clg52g7jc5ks73ebv3kg-a.singapore-postgres.render.com:5432/academicsysdb";
        String user = "academicsysdb_user";
        String password = "ZjIvqGcPMEmfe81jZZlNZVamy9XFvlL2";

        try {
            // Create a connection to the database
            Connection connection = DriverManager.getConnection(url, user, password);
            String query ="";
            // Create a statement and execute an update query to update student data
            if(userID.charAt(0) == 's'){
                query = "UPDATE student SET " + column + " = ? WHERE student_id = ?";
            }else if(userID.charAt(0) == 't'){
                query = "UPDATE teacher SET " + column + " = ? WHERE teacher_id = ?";
            }else {
                query = "UPDATE admin SET " + column + " = ? WHERE admin = ?";
            }

            PreparedStatement statement = connection.prepareStatement(query);
            if (column.equals("phone_num")) {
                statement.setLong(1, Long.parseLong(newValue));
            } else {
                statement.setString(1, newValue);
            }
            statement.setString(2, userID);
            int updateRow = statement.executeUpdate();
            if(updateRow > 0) {
                JOptionPane.showMessageDialog(this, "Update Successful!", "Update", JOptionPane.PLAIN_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(this, "Update error!\nPlease try again", "Update", JOptionPane.PLAIN_MESSAGE);
            }

            // Close the resources
            statement.close();
            connection.close();
        } catch (SQLException e) {
            LogHandler.error(e);
        }
    }
}