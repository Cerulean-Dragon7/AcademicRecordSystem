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
                        query = String.format("select * from teacher where student_id = '%s' and password = '%s' ", id, password);
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
}
