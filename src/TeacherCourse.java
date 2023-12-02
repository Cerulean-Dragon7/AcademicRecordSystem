import java.sql.ResultSet;
import java.util.ArrayList;

public class TeacherCourse extends Course{
    private ArrayList<CourseStudent> students= new ArrayList<>();
    public TeacherCourse(String courseID, String courseTitle,String teacherID) {
        super(courseID, courseTitle);
        getStudentToCourse(courseID,teacherID);
    }

    private void getStudentToCourse(String courseID, String teacherID){
        try{
            ResultSet rs = DBConnection.getStudentToTeacherCourse(teacherID, courseID);

            while(rs.next()){
                students.add(new CourseStudent(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),
                        rs.getString(5),rs.getString(6),rs.getString(7),rs.getInt(8),rs.getInt(9)));
            }
        }catch (Exception e){
            e.printStackTrace();
        }



    }

    public ArrayList<CourseStudent> getStudentsList(){
        return students;
    }
}
