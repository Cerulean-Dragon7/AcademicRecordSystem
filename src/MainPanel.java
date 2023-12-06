import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;

public class MainPanel extends JPanel {
    private JLabel userName;
    private String user;
    private JButton profile;
    private JButton logout;
    private JPanel headerPanel;
    private JPanel leftPanel;
    private JPanel rightPanel;
    private JPanel logoutPanel;
    private JPanel coursePanel;

    public MainPanel(int usertype, String user) {
        //check the user access level to show difference panel
        this.user = user;
        initBaseComponent();
        getUserView(usertype);
    }

    public boolean deleteFromStudentTable(String studentId) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://dpg-clg52g7jc5ks73ebv3kg-a.singapore-postgres.render.com:5432/academicsysdb", "academicsysdb_user", "ZjIvqGcPMEmfe81jZZlNZVamy9XFvlL2");

            String sql = "DELETE FROM student WHERE student_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, studentId);

            int rowsDeleted = statement.executeUpdate();
            connection.close();

            return rowsDeleted > 0;
        } catch (SQLException e) {
            LogHandler.error(e);
            JOptionPane.showMessageDialog(null, "Error deleting row from student table: " + e.getMessage());
            return false;
        }
    }

    public boolean deleteFromTeacherTable(String teacherId) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://dpg-clg52g7jc5ks73ebv3kg-a.singapore-postgres.render.com:5432/academicsysdb", "academicsysdb_user", "ZjIvqGcPMEmfe81jZZlNZVamy9XFvlL2");

            String sql = "DELETE FROM teacher WHERE teacher_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, teacherId);

            int rowsDeleted = statement.executeUpdate();
            connection.close();

            return rowsDeleted > 0;
        } catch (SQLException e) {
            LogHandler.error(e);
            JOptionPane.showMessageDialog(null, "Error deleting row from teacher table: " + e.getMessage());
            return false;
        }
    }

    //add component of name profile button and logout button
    private void initBaseComponent() {   //create the header
        this.setLayout(new BorderLayout());

        headerPanel = new JPanel(new BorderLayout());
        headerPanel.setSize(800, 100);   //set the panel size

        //show the username
        userName = new JLabel(user);
        userName.setPreferredSize(new Dimension(100, 30));
        userName.setFont(new Font("Arial", Font.PLAIN, 16));

        leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        leftPanel.add(userName);

        //profile button
        profile = new JButton("Profile");
        profile.setPreferredSize(new Dimension(100, 30));
        profile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DisplayProfileData displayStudentData = new DisplayProfileData(user);
                displayStudentData.setVisible(true);
            }
        });

        rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rightPanel.add(profile);

        //header panel show username and button
        headerPanel.add(leftPanel, BorderLayout.WEST);
        headerPanel.add(rightPanel, BorderLayout.EAST);
        headerPanel.setBorder(new MatteBorder(0, 0, 1, 0, Color.BLACK));

        //logout button
        logout = new JButton("logout");
        logout.setPreferredSize(new Dimension(70, 20));

        logout.addActionListener(new ActionListener() {
            //go to login screen
            //disconnect to db
            @Override
            public void actionPerformed(ActionEvent e) {
                GUIFrame guiFrame = (GUIFrame) SwingUtilities.getWindowAncestor(MainPanel.this);
                guiFrame.changePanel(GUIFrame.LOGINPANEL);
            }
        });

        logoutPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        logoutPanel.setSize(800, 50);
        logoutPanel.setBorder(new MatteBorder(1, 0, 0, 0, Color.BLACK));
        logoutPanel.add(logout);


        this.add(headerPanel, BorderLayout.NORTH);   //add header panel to the bottom panel
        this.add(logoutPanel, BorderLayout.SOUTH);
        this.setVisible(true);
    }

    //identified witch user is
    public void getUserView(int usertype) {
        if (usertype == 0) {
            studentPanel();
        }
        if (usertype == 1) {
            teacherPanel();
        }
        if (usertype == 2) {
            adminPanel();
        }
    }

    private void teacherPanel() {
        // Show the course title
        JLabel courseTitle = new JLabel("My course");
        courseTitle.setHorizontalAlignment(SwingConstants.CENTER);
        courseTitle.setFont(new Font("Arial", Font.PLAIN, 16));

        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.add(courseTitle, BorderLayout.NORTH);

        // Show the student title
        JLabel studentTitle = new JLabel("Student");
        studentTitle.setHorizontalAlignment(SwingConstants.CENTER);
        studentTitle.setFont(new Font("Arial", Font.PLAIN, 16));

        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.add(studentTitle, BorderLayout.NORTH);

        // Create panel of the course
        JPanel bottomPanel = new JPanel(new GridLayout(1, 3));

        JPanel coursePanel = new JPanel(); // Show course as vertical
        coursePanel.setLayout(new BoxLayout(coursePanel, BoxLayout.Y_AXIS));
        ArrayList<String> courseIDs = DatabaseHelper.getCoursesByTeacherID(user);

        JPanel studentListPanel = new JPanel(); // Show student in course as vertical
        studentListPanel.setLayout(new BoxLayout(studentListPanel, BoxLayout.Y_AXIS));
        JLabel adviceLabel = new JLabel("Please choose a course to see students");
        adviceLabel.setHorizontalAlignment(SwingConstants.CENTER);
        adviceLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        studentListPanel.add(adviceLabel);

        // Add the student all course panel
        // Add a variable to keep track of the previously clicked course
// Create an array to store the reference to the previous course label
        final JLabel[] previousCourse = {null};

// Add the student all course panel
        for (String courseID : courseIDs) {
            JLabel course = new JLabel();
            String courseTextFormat = "<html><font size ='4'> Course " + courseID + "</font></html>";
            coursePanel.add(Box.createRigidArea(new Dimension(0, 1)));

            course.setText(courseTextFormat);
            course.setMinimumSize(new Dimension(200, 300));
            course.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

            course.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    // Set the background color of the previously clicked course to null
                    if (previousCourse[0] != null) {
                        previousCourse[0].setOpaque(false);
                        previousCourse[0].setBackground(null);
                    }

                    // Show the loading indication
                    course.setOpaque(true);
                    course.setBackground(Color.GRAY);

                    // Update the previously clicked course
                    previousCourse[0] = course;

                    JLabel loadingLabel = new JLabel("Loading...");
                    loadingLabel.setHorizontalAlignment(SwingConstants.CENTER);
                    loadingLabel.setFont(new Font("Arial", Font.PLAIN, 16));
                    studentListPanel.removeAll();
                    studentListPanel.add(loadingLabel);
                    studentListPanel.revalidate();
                    studentListPanel.repaint();

                    // Retrieve the student IDs for the clicked course in a separate thread
                    Thread fetchThread = new Thread(() -> {
                        ArrayList<String> studentIDs = DatabaseHelper.getStudentsByCourseID(courseID);

                        // Clear the student list panel
                        studentListPanel.removeAll();

                        // Add the student IDs to the student list panel
                        for (String studentID : studentIDs) {
                            JLabel student = new JLabel();
                            String studentTextFormat = "<html><font size ='4'> Student " + studentID + "</font></html>";
                            studentListPanel.add(Box.createRigidArea(new Dimension(0, 1)));

                            student.setText(studentTextFormat);
                            student.setMinimumSize(new Dimension(200, 300));
                            student.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
                            studentListPanel.add(student);
                            student.addMouseListener(new MouseAdapter() { //click the course to see more detail
                                @Override
                                public void mouseClicked(MouseEvent e) {
                                    UpdateStudentScoreData UpdateStudentScoreData = new UpdateStudentScoreData(studentID);
                                    UpdateStudentScoreData.setVisible(true);
                                }
                            });

                        }

                        // Repaint the student list panel to reflect the changes
                        studentListPanel.revalidate();
                        studentListPanel.repaint();
                    });

                    fetchThread.start(); // Start the data fetching thread
                }
            });
            coursePanel.add(course);
        }
        JScrollPane courseScrollPanel = new JScrollPane(coursePanel); // Let the panel can scroll
        courseScrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        courseScrollPanel.setPreferredSize(new Dimension(300, 570));

        JScrollPane studentScrollPanel = new JScrollPane(studentListPanel); // Let the panel can scroll
        studentScrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        studentScrollPanel.setPreferredSize(new Dimension(300, 570));

        leftPanel.add(courseScrollPanel);
        rightPanel.add(studentScrollPanel);

        bottomPanel.add(leftPanel);
        bottomPanel.add(rightPanel);

        this.add(bottomPanel, BorderLayout.CENTER);
    }

    private void studentPanel() {
        JPanel bottomPanel = new JPanel(new GridLayout(1, 3));

        JLabel courseTitle = new JLabel("My Course");
        courseTitle.setFont(new Font("Arial", Font.PLAIN, 20));
        courseTitle.setBackground(Color.GREEN);
        courseTitle.setPreferredSize(new Dimension(200, 20));

        JPanel coursePanel = new JPanel(); // show course as vertical
        coursePanel.setLayout(new BoxLayout(coursePanel, BoxLayout.Y_AXIS));

        // Get the course IDs for the student
        ArrayList<String> courseIDs = DatabaseHelper.getCoursesByStudentID(user);


        //add the course all course panel
        for (String courseID : courseIDs) {
            JLabel course = new JLabel();
            String courseTextFormat = "<html><font size ='4'> Course " + courseID + "</font></html>";

            course.setText(courseTextFormat);
            course.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
            coursePanel.add(Box.createRigidArea(new Dimension(0, 5)));


            course.addMouseListener(new MouseAdapter() { //click the course to see more detail
                @Override
                public void mouseClicked(MouseEvent e) {
                    String courseName = "Course: " + courseID;
                    String courseGrade = DatabaseHelper.getCourseGrades(user, courseID);
                    JOptionPane.showMessageDialog(MainPanel.this, courseGrade, courseName, JOptionPane.PLAIN_MESSAGE);
                }
            });
            coursePanel.add(course);
        }


        JScrollPane courseScrollPanel = new JScrollPane(coursePanel);
        courseScrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        courseScrollPanel.setPreferredSize(new Dimension(300, 570));

        bottomPanel.add(courseTitle, BorderLayout.NORTH);
        bottomPanel.add(courseScrollPanel, BorderLayout.CENTER);

        this.add(bottomPanel, BorderLayout.CENTER);
    }

    private void adminPanel() {
        //course title
        JPanel coursePanel = new JPanel(); //show course panel
        JPanel studentListPanel = new JPanel(); //show student panel
        JPanel teacherPanel = new JPanel(); //show student panel

        JButton addButton = new JButton("Add Teacher");
        rightPanel.add(addButton);
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame addTeacherFrame = new JFrame("Add Teacher");
                addTeacherFrame.setSize(400, 300);

                // Create labels and text fields for teacher details
                JLabel idLabel = new JLabel("Teacher ID:");
                JTextField idField = new JTextField(20);

                JLabel firstNameLabel = new JLabel("First Name:");
                JTextField firstNameField = new JTextField(20);

                JLabel lastNameLabel = new JLabel("Last Name:");
                JTextField lastNameField = new JTextField(20);

                JLabel addressLabel = new JLabel("Address:");
                JTextField addressField = new JTextField(20);

                JLabel phoneNumLabel = new JLabel("Phone Number:");
                JTextField phoneNumField = new JTextField(20);

                JLabel emailLabel = new JLabel("Email:");
                JTextField emailField = new JTextField(20);

                JLabel passwordLabel = new JLabel("Password:");
                JPasswordField passwordField = new JPasswordField(20);

                // Create a button for adding the teacher
                JButton addTeacherButton = new JButton("Add Teacher");

                // Add action listener to the addTeacherButton to handle the addition of the new teacher
                addTeacherButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        // Get the values entered by the user
                        String teacherID = idField.getText();
                        String firstName = firstNameField.getText();
                        String lastName = lastNameField.getText();
                        String address = addressField.getText();
                        String phoneNumber = phoneNumField.getText();
                        String email = emailField.getText();
                        String password = new String(passwordField.getPassword());
                        String errorMessage = null;

                        // Perform validation if needed

                        // Add the teacher to the teacher table using the entered values
                        try {
                            // Establish a connection to the PostgreSQL database
                            Connection connection = DriverManager.getConnection("jdbc:postgresql://dpg-clg52g7jc5ks73ebv3kg-a.singapore-postgres.render.com:5432/academicsysdb", "academicsysdb_user", "ZjIvqGcPMEmfe81jZZlNZVamy9XFvlL2");

                            // Create a prepared statement for executing the SQL query
                            String query = "INSERT INTO teacher (teacher_id, first_name, last_name, address, phone_num, email, password) " +
                                    "VALUES (?, ?, ?, ?, ?, ?, ?)";
                            PreparedStatement preparedStatement = connection.prepareStatement(query);
                            if(teacherID != null && InputValidation.checkTeacherID(teacherID)){
                                preparedStatement.setString(1, teacherID);
                            }else{
                                errorMessage = "Teacher ID must start with \"t\", followed by exactly 7 digits";
                            }
                            if (firstName != null && InputValidation.checkFirstName(firstName)) {
                                preparedStatement.setString(2, firstName);
                            }else{
                                errorMessage = "First name only contain 1-20 character";
                            }
                            if (lastName != null && InputValidation.checkLastName(lastName)) {
                                preparedStatement.setString(3, lastName);
                            }else{
                                errorMessage = "Last name only contain 1-20 character";
                            }
                            if (address != null && InputValidation.checkAddress(address)) {
                                preparedStatement.setString(4, address);
                            }else {
                                errorMessage = "Address need to have exactly 1-50 number";
                            }
                            if (phoneNumber != null && InputValidation.checkPhoneNumber(phoneNumber)) {
                                preparedStatement.setString(5, phoneNumber);
                                preparedStatement.setBigDecimal(5, new BigDecimal(phoneNumber));
                            }else {
                                errorMessage = "Phone number need to have exactly 10 number";
                            }
                            if (email != null && InputValidation.checkEmail(email)) {
                                preparedStatement.setString(6, email);
                            }else {
                                errorMessage = "Email only contain 1-50 character";
                            }
                            if (InputValidation.checkPassword(password)) {
                                preparedStatement.setString(7, password);
                            }else {
                                errorMessage  = "Password need to have at least one lower case letter\n" +
                                        "at least one upper case letter \n at least one number \n at least 8 character";
                            }

                            if(errorMessage == null) {
                                // Execute the SQL query
                                preparedStatement.executeUpdate();

                                // Close the prepared statement and the database connection
                                preparedStatement.close();
                                connection.close();

                                ArrayList<String> teacher_id = DatabaseHelper.getTeacherIDsFromDatabase();
                                teacherPanel.removeAll();
                                for (String tid : teacher_id) {
                                    JLabel teacher = new JLabel();
                                    String teacherTextFormat = "<html><font size ='4'> Student " + tid + "</font></html>";
                                    teacherPanel.add(Box.createRigidArea(new Dimension(0, 1)));

                                    teacher.setText(teacherTextFormat);
                                    teacher.setMinimumSize(new Dimension(200, 300));
                                    teacher.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
                                    teacherPanel.add(teacher);
                                    teacher.addMouseListener(new MouseAdapter() { //click the course to see more detail
                                        @Override
                                        public void mouseClicked(MouseEvent e) {
                                            DisplayProfileData displayStudentData = new DisplayProfileData(tid);
                                            displayStudentData.setVisible(true);
                                        }
                                    });
                                }
                                teacherPanel.revalidate();
                                teacherPanel.repaint();
                                // Show a success message or perform any other necessary actions
                                JOptionPane.showMessageDialog(addTeacherFrame, "Teacher added successfully.");
                            }else{
                                JOptionPane.showMessageDialog(MainPanel.this, errorMessage, "Error", JOptionPane.PLAIN_MESSAGE);
                            }
                        } catch (SQLException ex) {
                            // Handle any errors that may occur during the database operation
                            LogHandler.error(ex);
                            JOptionPane.showMessageDialog(addTeacherFrame, "Error: Failed to add teacher.");
                        }
                    }
                });

                // Create a panel to hold the components
                JPanel addTeacherPanel = new JPanel(new GridLayout(8, 2));
                addTeacherPanel.add(idLabel);
                addTeacherPanel.add(idField);
                addTeacherPanel.add(firstNameLabel);
                addTeacherPanel.add(firstNameField);
                addTeacherPanel.add(lastNameLabel);
                addTeacherPanel.add(lastNameField);
                addTeacherPanel.add(addressLabel);
                addTeacherPanel.add(addressField);
                addTeacherPanel.add(phoneNumLabel);
                addTeacherPanel.add(phoneNumField);
                addTeacherPanel.add(emailLabel);
                addTeacherPanel.add(emailField);
                addTeacherPanel.add(passwordLabel);
                addTeacherPanel.add(passwordField);
                addTeacherPanel.add(addTeacherButton);

                // Add the addTeacherPanel to the addTeacherFrame
                addTeacherFrame.add(addTeacherPanel);
                addTeacherFrame.setVisible(true);
            }
        });

        JButton addStudentButton = new JButton("Add Student");
        rightPanel.add(addStudentButton);
        addStudentButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame addStudentFrame = new JFrame("Add Student");
                addStudentFrame.setSize(400, 300);

                // Create labels and text fields for teacher details
                JLabel idLabel = new JLabel("Student ID:");
                JTextField idField = new JTextField(20);

                JLabel firstNameLabel = new JLabel("First Name:");
                JTextField firstNameField = new JTextField(20);

                JLabel lastNameLabel = new JLabel("Last Name:");
                JTextField lastNameField = new JTextField(20);

                JLabel addressLabel = new JLabel("Address:");
                JTextField addressField = new JTextField(20);

                JLabel phoneNumLabel = new JLabel("Phone Number:");
                JTextField phoneNumField = new JTextField(20);

                JLabel emailLabel = new JLabel("Email:");
                JTextField emailField = new JTextField(20);

                JLabel passwordLabel = new JLabel("Password:");
                JPasswordField passwordField = new JPasswordField(20);

                // Create a button for adding the teacher
                JButton addStudentButton = new JButton("Add Student");

                // Add action listener to the addTeacherButton to handle the addition of the new teacher
                addStudentButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        // Get the values entered by the user
                        String StudentID = idField.getText();
                        String newFirstName = firstNameField.getText();
                        String lastName = lastNameField.getText();
                        String address = addressField.getText();
                        String phoneNumber = phoneNumField.getText();
                        String email = emailField.getText();
                        String password = new String(passwordField.getPassword());
                        String errorMessage = null;

                        // Perform validation if needed

                        // Add the teacher to the teacher table using the entered values
                        try {
                            // Establish a connection to the PostgreSQL database
                            Connection connection = DriverManager.getConnection("jdbc:postgresql://dpg-clg52g7jc5ks73ebv3kg-a.singapore-postgres.render.com:5432/academicsysdb", "academicsysdb_user", "ZjIvqGcPMEmfe81jZZlNZVamy9XFvlL2");

                            // Create a prepared statement for executing the SQL query
                            String query = "INSERT INTO student (student_id, first_name, last_name, address, phone_num, email, password) " +
                                    "VALUES (?, ?, ?, ?, ?, ?, ?)";
                            PreparedStatement preparedStatement = connection.prepareStatement(query);
                            if(StudentID != null && InputValidation.checkStudentID(StudentID)){
                                preparedStatement.setString(1, StudentID);
                            }else{
                                errorMessage = "Student ID must start with \"s\", followed by exactly 7 digits";
                            }
                            if (newFirstName != null && InputValidation.checkFirstName(newFirstName)) {
                                preparedStatement.setString(2, newFirstName);
                            }else{
                                errorMessage = "First name only contain 1-20 character";
                            }
                            if (lastName != null && InputValidation.checkLastName(lastName)) {
                                preparedStatement.setString(3, lastName);
                            }else{
                                errorMessage = "Last name only contain 1-20 character";
                            }
                            if (address != null && InputValidation.checkAddress(address)) {
                                preparedStatement.setString(4, address);
                            }else {
                                errorMessage = "Address need to have exactly 1-50 number";
                            }
                            if (phoneNumber != null && InputValidation.checkPhoneNumber(phoneNumber)) {
                                preparedStatement.setString(5, phoneNumber);
                                preparedStatement.setBigDecimal(5, new BigDecimal(phoneNumber));
                            }else {
                                errorMessage = "Phone number need to have exactly 10 number";
                            }
                            if (email != null && InputValidation.checkEmail(email)) {
                                preparedStatement.setString(6, email);
                            }else {
                                errorMessage = "Email only contain 1-50 character";
                            }
                            if (InputValidation.checkPassword(password)) {
                                preparedStatement.setString(7, password);
                            }else {
                                errorMessage  = "Password need to have at least one lower case letter\n" +
                                        "at least one upper case letter \n at least one number \n at least 8 character";
                            }
                            if(errorMessage == null){

                                // Execute the SQL query
                                preparedStatement.executeUpdate();

                                // Close the prepared statement and the database connection
                                preparedStatement.close();
                                connection.close();

                                ArrayList<String> student_id = DatabaseHelper.getStudentIDsFromDatabase();
                                studentListPanel.removeAll();
                                for(String sid : student_id){
                                    JLabel student = new JLabel();
                                    String studentTextFormat = "<html><font size ='4'> Student " + sid + "</font></html>";
                                    studentListPanel.add(Box.createRigidArea(new Dimension(0, 1)));

                                    student.setText(studentTextFormat);
                                    student.setMinimumSize(new Dimension(200, 300));
                                    student.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
                                    studentListPanel.add(student);
                                    student.addMouseListener(new MouseAdapter() { //click the course to see more detail
                                        @Override
                                        public void mouseClicked(MouseEvent e) {
                                            DisplayProfileData displayStudentData = new DisplayProfileData(sid);
                                            displayStudentData.setVisible(true);
                                        }
                                    });
                                }
                                studentListPanel.revalidate();
                                studentListPanel.repaint();

                                // Show a success message or perform any other necessary actions
                                JOptionPane.showMessageDialog(addStudentFrame, "Student added successfully.");

                            }else{
                                JOptionPane.showMessageDialog(MainPanel.this, errorMessage, "Error", JOptionPane.PLAIN_MESSAGE);
                            }
                        } catch (SQLException ex) {
                            // Handle any errors that may occur during the database operation
                            LogHandler.error(ex);
                            JOptionPane.showMessageDialog(addStudentFrame, "Error: Failed to add Ss.");
                        }


                    }
                });

                // Create a panel to hold the components
                JPanel addStudentPanel = new JPanel(new GridLayout(8, 2));
                addStudentPanel.add(idLabel);
                addStudentPanel.add(idField);
                addStudentPanel.add(firstNameLabel);
                addStudentPanel.add(firstNameField);
                addStudentPanel.add(lastNameLabel);
                addStudentPanel.add(lastNameField);
                addStudentPanel.add(addressLabel);
                addStudentPanel.add(addressField);
                addStudentPanel.add(phoneNumLabel);
                addStudentPanel.add(phoneNumField);
                addStudentPanel.add(emailLabel);
                addStudentPanel.add(emailField);
                addStudentPanel.add(passwordLabel);
                addStudentPanel.add(passwordField);
                addStudentPanel.add(addStudentButton);

                // Add the addTeacherPanel to the addTeacherFrame
                addStudentFrame.add(addStudentPanel);
                addStudentFrame.setVisible(true);
            }
        });

        JButton deleteButton = new JButton("Delete by ID");
        rightPanel.add(deleteButton);
        deleteButton.addActionListener(e -> {
            JFrame idFrame = new JFrame("Enter ID");
            idFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            idFrame.setSize(300, 100);

            JTextField idField = new JTextField();
            idField.setPreferredSize(new Dimension(100, 25));

            JButton confirmButton = new JButton("Confirm");
            confirmButton.addActionListener(event -> {
                String id = idField.getText();
                idFrame.dispose(); // Close the ID entry frame

                if (id.startsWith("s")) {
                    // Delete row from the student table
                    boolean deleted = deleteFromStudentTable(id);
                    if (deleted) {
                        ArrayList<String> studentID = DatabaseHelper.getStudentIDsFromDatabase();
                        studentListPanel.removeAll();
                        for(String sid : studentID){
                            JLabel student = new JLabel();
                            String studentTextFormat = "<html><font size ='4'> Student " + sid + "</font></html>";
                            studentListPanel.add(Box.createRigidArea(new Dimension(0, 1)));

                            student.setText(studentTextFormat);
                            student.setMinimumSize(new Dimension(200, 300));
                            student.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
                            studentListPanel.add(student);
                            student.addMouseListener(new MouseAdapter() { //click the course to see more detail
                                @Override
                                public void mouseClicked(MouseEvent e) {
                                    DisplayProfileData displayStudentData = new DisplayProfileData(sid);
                                    displayStudentData.setVisible(true);
                                }
                            });
                        }
                        studentListPanel.revalidate();
                        studentListPanel.repaint();
                        JOptionPane.showMessageDialog(null, "Row deleted successfully from student table!");
                    } else {
                        JOptionPane.showMessageDialog(null, "No matching row found in student table!");
                    }
                } else if (id.startsWith("t")) {
                    // Delete row from the teacher table
                    boolean deleted = deleteFromTeacherTable(id);
                    if (deleted) {
                        ArrayList<String> teacherID = DatabaseHelper.getTeacherIDsFromDatabase();
                        teacherPanel.removeAll();
                        for(String tid : teacherID){
                            JLabel teacher = new JLabel();
                            String teacherTextFormat = "<html><font size ='4'> Student " + tid + "</font></html>";
                            teacherPanel.add(Box.createRigidArea(new Dimension(0, 1)));

                            teacher.setText(teacherTextFormat);
                            teacher.setMinimumSize(new Dimension(200, 300));
                            teacher.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
                            teacherPanel.add(teacher);
                            teacher.addMouseListener(new MouseAdapter() { //click the course to see more detail
                                @Override
                                public void mouseClicked(MouseEvent e) {
                                    DisplayProfileData displayStudentData = new DisplayProfileData(tid);
                                    displayStudentData.setVisible(true);
                                }
                            });
                        }
                        teacherPanel.revalidate();
                        teacherPanel.repaint();
                        JOptionPane.showMessageDialog(null, "Row deleted successfully from teacher table!");
                    } else {
                        JOptionPane.showMessageDialog(null, "No matching row found in teacher table!");
                    }
                } else {
                    // Invalid ID format
                    JOptionPane.showMessageDialog(null, "Invalid ID format!");
                }
            });

            idFrame.getContentPane().setLayout(new FlowLayout());
            idFrame.getContentPane().add(new JLabel("Enter ID:"));
            idFrame.getContentPane().add(idField);
            idFrame.getContentPane().add(confirmButton);

            idFrame.setVisible(true);
        });



        JLabel courseTitle = new JLabel("My course");
        courseTitle.setHorizontalAlignment(SwingConstants.CENTER);
        courseTitle.setFont(new Font("Arial", Font.PLAIN, 16));

        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.add(courseTitle, BorderLayout.NORTH);
        leftPanel.setMinimumSize(new Dimension(300, 500));


        //teacher title
        JLabel teacherTitle = new JLabel("Teacher");
        teacherTitle.setHorizontalAlignment(SwingConstants.CENTER);
        teacherTitle.setFont(new Font("Arial", Font.PLAIN, 16));

        JPanel middlePanel = new JPanel(new BorderLayout());
        middlePanel.add(teacherTitle, BorderLayout.NORTH);

        //student title
        JLabel studentTitle = new JLabel("Student");
        studentTitle.setHorizontalAlignment(SwingConstants.CENTER);
        studentTitle.setFont(new Font("Arial", Font.PLAIN, 16));

        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.add(studentTitle, BorderLayout.NORTH);

        //panel contain course, teacher, student
        JPanel bottomPanel = new JPanel(new GridLayout(1, 3));



        coursePanel.setLayout(new BoxLayout(coursePanel, BoxLayout.Y_AXIS));

        ArrayList<String> courseIDs = DatabaseHelper.getCourseIDsFromDatabase();

        //add the student all course panel
        for (int i = 0;i < courseIDs.size();i++) {
            int j = i;
            JLabel course = new JLabel();
            String courseTextFormat = "<html><font size ='4'> Course " + courseIDs.get(i) + "</font></html>";
            coursePanel.add(Box.createRigidArea(new Dimension(0, 1)));

            course.setText(courseTextFormat);
            course.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
            course.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    ArrayList<String> courseTitles = DatabaseHelper.getCourseTitleFromDatabase();

                    JOptionPane.showMessageDialog(MainPanel.this,"course ID: "+ courseIDs.get(j) +"\nTitle: "+ courseTitles.get(j));

                }
            });
            coursePanel.add(course);
            coursePanel.setMinimumSize(new Dimension(300, 500));
        }

        JScrollPane courseScrollPanel = new JScrollPane(coursePanel); //let the panel can scroll
        courseScrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        //crate all student panel
        //show to list of student witch in the course

        studentListPanel.setLayout(new BoxLayout(studentListPanel, BoxLayout.Y_AXIS));
        ArrayList<String> studentIDs = DatabaseHelper.getStudentIDsFromDatabase();

        //add the student all course panel
        for (String studentID : studentIDs) {
            JLabel student = new JLabel();
            String studentTextFormat = "<html><font size ='4'> Student " + studentID + "</font></html>";
            studentListPanel.add(Box.createRigidArea(new Dimension(0, 1)));

            student.setText(studentTextFormat);
            student.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
            student.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    //teacher can modify student grade in the dialog
                    DisplayProfileData displayStudentData = new DisplayProfileData(studentID);
                    displayStudentData.setVisible(true);

                }
            });
            studentListPanel.add(student);
        }

        JScrollPane studentScrollPanel = new JScrollPane(studentListPanel); //let the panel can scroll
        studentScrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);


        teacherPanel.setLayout(new BoxLayout(teacherPanel, BoxLayout.Y_AXIS));
        ArrayList<String> teacherIDs = DatabaseHelper.getTeacherIDsFromDatabase();

        //add the student all course panel
        for (String teacherID : teacherIDs) {
            JLabel student = new JLabel();
            String studentTextFormat = "<html><font size ='4'> Teacher " + teacherID + "</font></html>";
            teacherPanel.add(Box.createRigidArea(new Dimension(0, 1)));

            student.setText(studentTextFormat);
            student.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
            student.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    //teacher can modify student grade in the dialog
                    DisplayProfileData displayStudentData = new DisplayProfileData(teacherID);
                    displayStudentData.setVisible(true);

                }
            });
            teacherPanel.add(student);
        }

        JScrollPane teacherScrollPanel = new JScrollPane(teacherPanel); //let the panel can scroll
        teacherScrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        leftPanel.add(courseScrollPanel);
        middlePanel.add(teacherScrollPanel);
        rightPanel.add(studentScrollPanel);

        leftPanel.setPreferredSize(new Dimension(250, 620));
        middlePanel.setPreferredSize(new Dimension(250, 620));
        rightPanel.setPreferredSize(new Dimension(250, 620));

        bottomPanel.add(leftPanel);
        bottomPanel.add(middlePanel);
        bottomPanel.add(rightPanel);


        this.add(bottomPanel, BorderLayout.CENTER);
    }
}
