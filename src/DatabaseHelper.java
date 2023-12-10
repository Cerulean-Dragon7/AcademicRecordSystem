import java.sql.*;
import java.util.ArrayList;

public class DatabaseHelper {
    private static final String DB_URL = "jdbc:postgresql://dpg-clg52g7jc5ks73ebv3kg-a.singapore-postgres.render.com:5432/academicsysdb";
    private static final String DB_USER = "academicsysdb_user";
    private static final String DB_PASSWORD = "ZjIvqGcPMEmfe81jZZlNZVamy9XFvlL2";

    public static ArrayList<String> getCoursesByStudentID(String studentID) {
        ArrayList<String> courseIDs = new ArrayList<>();

        try {
            // Establish the database connection
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // Create a prepared statement with a parameter for studentID
            String query = "SELECT course_id FROM enrollment WHERE student_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, studentID);

            // Execute the query
            ResultSet resultSet = statement.executeQuery();

            // Iterate over the result set and add the courseIDs to the list
            while (resultSet.next()) {
                String courseID = resultSet.getString("course_id");
                courseIDs.add(courseID);
            }

            // Close the resources
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            LogHandler.error(e);
        }

        return courseIDs;
    }

    public static ArrayList<String> getCoursesByTeacherID(String teacherID) {
        ArrayList<String> courseIDs = new ArrayList<>();

        try {
            // Establish the database connection
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // Create a prepared statement with a parameter for studentID
            String query = "SELECT course_id FROM instruction WHERE teacher_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, teacherID);

            // Execute the query
            ResultSet resultSet = statement.executeQuery();

            // Iterate over the result set and add the courseIDs to the list
            while (resultSet.next()) {
                String courseID = resultSet.getString("course_id");
                courseIDs.add(courseID);
            }

            // Close the resources
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            LogHandler.error(e);
        }

        return courseIDs;
    }

    public static ArrayList<String> getCourseIDsFromDatabase() {
        ArrayList<String> courseIDs = new ArrayList<>();

        try {
            // Establish the database connection
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // Create a statement and execute the query
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT course_id FROM course ORDER BY course_id");

            // Iterate over the result set and add the courseIDs to the list
            while (resultSet.next()) {
                String courseID = resultSet.getString("course_id");
                courseIDs.add(courseID);
            }

            // Close the resources
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            LogHandler.error(e);
        }

        return courseIDs;
    }

    public static ArrayList<String> getTeacherIDsFromDatabase() {
        ArrayList<String> teacherIDs = new ArrayList<>();

        try {
            // Establish the database connection
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // Create a statement and execute the query
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT teacher_id FROM teacher ORDER BY teacher_id");

            // Iterate over the result set and add the teacherIDs to the list
            while (resultSet.next()) {
                String teacherID = resultSet.getString("teacher_id");
                teacherIDs.add(teacherID);
            }

            // Close the resources
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            LogHandler.error(e);
        }

        return teacherIDs;
    }

    public static ArrayList<String> getStudentIDsFromDatabase() {
        ArrayList<String> studentIDs = new ArrayList<>();

        try {
            // Establish the database connection
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // Create a statement and execute the query
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT student_id FROM student ORDER BY student_id");

            // Iterate over the result set and add the studentIDs to the list
            while (resultSet.next()) {
                String studentID = resultSet.getString("student_id");
                studentIDs.add(studentID);
            }

            // Close the resources
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            LogHandler.error(e);
        }

        return studentIDs;
    }

    public static ArrayList<String> getStudentsByCourseID(String courseID) {
        ArrayList<String> studentIDs = new ArrayList<>();

        try {
            // Establish the database connection
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // Create a prepared statement with a parameter for courseID
            String query = "SELECT student_id FROM enrollment WHERE course_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, courseID);

            // Execute the query
            ResultSet resultSet = statement.executeQuery();

            // Iterate over the result set and add the studentIDs to the list
            while (resultSet.next()) {
                String studentID = resultSet.getString("student_id");
                studentIDs.add(studentID);
            }

            // Close the resources
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return studentIDs;
    }

    public static String getCourseGrades(String studentID, String courseID) {
        String midTermGrade = "";
        String examGrade = "";

        try {
            // Establish the database connection
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // Create a prepared statement with parameters for studentID and courseID
            String query = "SELECT mid_term_grade, exam_grade FROM enrollment WHERE student_id = ? AND course_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, studentID);
            statement.setString(2, courseID);

            // Execute the query
            ResultSet resultSet = statement.executeQuery();

            // Retrieve the grades
            if (resultSet.next()) {
                midTermGrade = resultSet.getString("mid_term_grade");
                examGrade = resultSet.getString("exam_grade");
            }

            // Close the resources
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "Mid-Term Grade: " + midTermGrade + "\nExam Grade: " + examGrade;

    }
    public static ArrayList<String> getCourseTitleFromDatabase() {
        ArrayList<String> courseTitles = new ArrayList<>();

        try {
            // Establish the database connection
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // Create a prepared statement with a parameter for studentID
            String query = "SELECT course_name FROM course";
            PreparedStatement statement = connection.prepareStatement(query);

            // Execute the query
            ResultSet resultSet = statement.executeQuery();

            // Iterate over the result set and add the courseIDs to the list
            while (resultSet.next()) {
                String course_id = resultSet.getString(1);
                courseTitles.add(course_id);
            }

            // Close the resources
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            LogHandler.error(e);
        }

        return courseTitles;
    }
}