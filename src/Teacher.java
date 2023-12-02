import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Teacher extends User{
    private ArrayList<TeacherCourse> teacherCourses = new ArrayList<>();
    public Teacher(String id, String firstName, String lastName, String address, String phoneNum, String email, String password) {
        super(id, firstName, lastName, address, phoneNum, email, password);
        getCourseFromDB(id);
    }
    private void getCourseFromDB(String teacherID){
        try {
            ResultSet coursesRS = DBConnection.getCoursesByTeacherID(teacherID);

            while(coursesRS.next()){
                teacherCourses.add(new TeacherCourse(coursesRS.getString(1),coursesRS.getString(2),teacherID));
            }

            coursesRS.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public TeacherCourse getTeacherCourse(int i){
        return teacherCourses.get(i);
    }

    public ArrayList<TeacherCourse> getTeacherCoursesList(){
        return teacherCourses;
    }
}
