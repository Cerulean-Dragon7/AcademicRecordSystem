import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class UpdateStudentScoreData extends JFrame {

    private JLabel studentIDLabel;
    private JLabel midTermGradeLabel;
    private JLabel examGradeLabel;

    public UpdateStudentScoreData(String studentID) {
        // Set UI and initialize components
        setTitle("Student Scores");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());


        // Create main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);

        // Create labels
        studentIDLabel = new JLabel();
        midTermGradeLabel = new JLabel();
        examGradeLabel = new JLabel();

        // Add labels to the main panel
        constraints.gridx = 0;
        constraints.gridy = 0;
        mainPanel.add(new JLabel("Student ID: "), constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        mainPanel.add(studentIDLabel, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        mainPanel.add(new JLabel("Mid-term Grade: "), constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        mainPanel.add(midTermGradeLabel, constraints);

        JButton updateMidTermGradeButton = new JButton("Update");
        constraints.gridx = 2;
        constraints.gridy = 1;
        mainPanel.add(updateMidTermGradeButton, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        mainPanel.add(new JLabel("Exam Grade: "), constraints);

        constraints.gridx = 1;
        constraints.gridy = 2;
        mainPanel.add(examGradeLabel, constraints);

        JButton updateExamGradeButton = new JButton("Update");
        constraints.gridx = 2;
        constraints.gridy = 2;
        mainPanel.add(updateExamGradeButton, constraints);


        // Add buttons to the main panel


        // Add the main panel to the content pane
        getContentPane().add(mainPanel, BorderLayout.CENTER);

        // Retrieve and display student scores
        retrieveStudentScores(studentID);

        // Add action listeners to the buttons
        updateMidTermGradeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String newMidTermGrade = JOptionPane.showInputDialog(null, "Enter new Mid-term Grade:");
                if (newMidTermGrade != null) {
                    if(InputValidation.checkScore(newMidTermGrade)){
                        updateStudentScore(studentID, "mid_term_grade", newMidTermGrade);
                        retrieveStudentScores(studentID);
                    }else{
                        JOptionPane.showMessageDialog(UpdateStudentScoreData.this,"The Score only between 0 to 100","Error",JOptionPane.PLAIN_MESSAGE);
                    }
                }
            }
        });

        updateExamGradeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String newExamGrade = JOptionPane.showInputDialog(null, "Enter new Exam Grade:");
                if (newExamGrade != null) {
                    if(InputValidation.checkScore(newExamGrade)){
                        updateStudentScore(studentID, "exam_grade", newExamGrade);
                        retrieveStudentScores(studentID);
                    }else {
                        JOptionPane.showMessageDialog(UpdateStudentScoreData.this,"The Score only between 0 to 100","Error",JOptionPane.PLAIN_MESSAGE);
                    }

                }
            }
        });

        // Set window size and center it on the screen
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private void retrieveStudentScores(String studentID) {
        // Database connection details
        String url = "jdbc:postgresql://dpg-clg52g7jc5ks73ebv3kg-a.singapore-postgres.render.com:5432/academicsysdb";
        String user = "academicsysdb_user";
        String password = "ZjIvqGcPMEmfe81jZZlNZVamy9XFvlL2";

        try {
            // Create a connection to the database
            Connection connection = DriverManager.getConnection(url, user, password);

            // Create a statement and execute a query to retrieve the mid_term_grade and exam_grade for the student_id
            String query = "SELECT mid_term_grade, exam_grade FROM enrollment WHERE student_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, studentID);
            ResultSet resultSet = statement.executeQuery();

            // Retrieve the grades from the result set and set the labels
            if (resultSet.next()) {
                studentIDLabel.setText(studentID);
                midTermGradeLabel.setText(resultSet.getString("mid_term_grade"));
                examGradeLabel.setText(resultSet.getString("exam_grade"));
            }

            // Close the resources
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            LogHandler.error(e);
        }
    }

    private void updateStudentScore(String studentID, String column, String newValue) {
        // Database connection details
        String url = "jdbc:postgresql://dpg-clg52g7jc5ks73ebv3kg-a.singapore-postgres.render.com:5432/academicsysdb";
        String user = "academicsysdb_user";
        String password = "ZjIvqGcPMEmfe81jZZlNZVamy9XFvlL2";

        try {
            // Create a connection to the database
            Connection connection = DriverManager.getConnection(url, user, password);

            // Create a statement and execute an update query to update the mid_term_grade or exam_grade
            String query = "UPDATE enrollment SET " + column + " = ? WHERE student_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);

            // Convert the new value to the appropriate type based on the column
            if (column.equals("mid_term_grade") || column.equals("exam_grade")) {
                int newGrade = Integer.parseInt(newValue);
                statement.setInt(1, newGrade);
            } else {
                statement.setString(1, newValue);
            }

            statement.setString(2, studentID);
            statement.executeUpdate();

            // Close the resources
            statement.close();
            connection.close();
        } catch (SQLException e) {
            LogHandler.error(e);
        }
    }
}