import javax.swing.*;
import java.sql.*;

public class DBConnection {
    private static final String DB_URL = "jdbc:postgresql://dpg-clg52g7jc5ks73ebv3kg-a.singapore-postgres.render.com:5432/academicsysdb";
    private static final String DB_USER = "academicsysdb_user";
    private static final String DB_PASSWORD = "ZjIvqGcPMEmfe81jZZlNZVamy9XFvlL2";


    public static ResultSet getUserByID( String id, String password){
        ResultSet rs = null;
        String query = null;

        try{
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            switch (id.charAt(0)) {
                case 'a' ->
                        query = String.format("select * from admin where admin_id = '%s' and password = '%s'", id, password);
                case 's' ->
                        query = String.format("select * from student where student_id = '%s' and password = '%s' ", id, password);
                case 't' ->
                        query = String.format("select * from teacher where teacher_id = '%s' and password = '%s' ", id, password);
            }

            System.out.println("query string: "+query);
            Statement statement = connection.createStatement();
            rs = statement.executeQuery(query);

            connection.close();

        }catch(Exception e){
            e.printStackTrace();
        }
        return rs;
    }

    public static ResultSet getCoursesByStudentID(String studentID){
        ResultSet rs = null;
        String query;

        try{
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            query = String.format("SELECT  * FROM enrollment e , course c where e.course_id = c.course_id and e.student_id= '%s';", studentID);
            System.out.println("query string: "+query);
            Statement statement = connection.createStatement();
            rs = statement.executeQuery(query);

            connection.close();

        }catch(Exception e){
            e.printStackTrace();
        }
        return rs;
    }
    public static ResultSet getCoursesByTeacherID(String teacherID){
        ResultSet rs = null;
        String query;

        try{
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            query = String.format("SELECT c.course_id ,c.course_name FROM instruction i , course c WHERE i.course_id = c.course_id AND i.teacher_id='%s';", teacherID);
            System.out.println("query string: "+query);
            Statement statement = connection.createStatement();
            rs = statement.executeQuery(query);

            connection.close();

        }catch(Exception e){
            e.printStackTrace();
        }
        return rs;
    }
    public static void updatePersonalInformation(User user){
        try{
            String query = null;

            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            switch (user.getId().charAt(0)) {
                case 'a' -> query = String.format("UPDATE admin SET first_name = '%s',last_name = '%s',address = '%s',phone_num = '%d',email ='%s',PASSWORD = '%s' WHERE admin_id = '%s'",
                        user.getFirstName(),user.getLastName(),user.getAddress(),Long.parseLong(user.getPhoneNum()),user.getEmail(),user.getPassword(),user.getId());
                case 's' ->  query = String.format("UPDATE student SET first_name = '%s',last_name = '%s',address = '%s',phone_num = '%d',email ='%s',PASSWORD = '%s' WHERE student_id = '%s'",
                        user.getFirstName(),user.getLastName(),user.getAddress(),Long.parseLong(user.getPhoneNum()),user.getEmail(),user.getPassword(),user.getId());
                case 't' ->  query = String.format("UPDATE teacher SET first_name = '%s',last_name = '%s',address = '%s',phone_num = '%d',email ='%s',PASSWORD = '%s' WHERE teacher_id = '%s'",
                        user.getFirstName(),user.getLastName(),user.getAddress(),Long.parseLong(user.getPhoneNum()),user.getEmail(),user.getPassword(),user.getId());
            }

            System.out.println("query string: "+query);
            Statement statement = connection.createStatement();

            int rowsAffected = statement.executeUpdate(query);
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null,"update information successfully","update",JOptionPane.PLAIN_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null,"update incomplete","update",JOptionPane.PLAIN_MESSAGE);
            }


        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static ResultSet getStudentToTeacherCourse(String teacherID, String courseID){
        ResultSet rs = null;
        try{
            String query;

            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            query = String.format("SELECT s.*, e.mid_term_grade, e.exam_grade FROM instruction i, enrollment e, student s WHERE i.course_id = e.course_id AND e.student_id = s.student_id AND i.teacher_id = '%s' AND i.course_id = '%s';",
                    teacherID,courseID);

            System.out.println("query string: "+query);
            Statement statement = connection.createStatement();
            rs = statement.executeQuery(query);

            connection.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return rs;
    }

}
