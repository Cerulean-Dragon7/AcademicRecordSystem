import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Student extends User{
    private ArrayList<Course> courses = new ArrayList<>();
    public Student(ResultSet rs){
        super(rs);
        getCourseFromDB(rs);
    }

    public String getCourseName(){
        Course course = courses.get(0);
        return course.getCourseTitle();
    }

    private void getCourseFromDB(ResultSet rs){
        try {
            String studentID = rs.getString(1);
            ResultSet coursesRS = DBConnection.getCoursesByStudentID(studentID);

            while(coursesRS.next()){
                courses.add(new Course(coursesRS));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
