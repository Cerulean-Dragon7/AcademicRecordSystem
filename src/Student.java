import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Student extends User{
    private ArrayList<StudentCourse> courses = new ArrayList<>();
    public Student(String id, String firstName, String lastName, String address, String phoneNum, String email, String password){
        super(id, firstName, lastName, address, phoneNum, email, password);
        getCourseFromDB(id);
    }

    public ArrayList<StudentCourse> getCourses(){
        return courses;
    }

    public ArrayList<String> getCoursesID(){
        ArrayList<String> coursesID = new ArrayList<>();
        for (StudentCourse course : courses) {
            coursesID.add(course.getCourseID());
        }
        return coursesID;
    }
    public ArrayList<String> getCoursesName(){
        ArrayList<String> coursesName = new ArrayList<>();
        for (StudentCourse course : courses) {
            coursesName.add(course.getCourseTitle());
        }
        return coursesName;
    }
    public ArrayList<Integer> getCoursesMidTermGrade(){
        ArrayList<Integer> coursesMidTermGrade = new ArrayList<>();
        for (StudentCourse course : courses) {
            coursesMidTermGrade.add(course.getMidTermGrade());
        }
        return coursesMidTermGrade;
    }
    public ArrayList<Integer> getCoursesExamGrade(){
        ArrayList<Integer> coursesExamGrade = new ArrayList<>();
        for (StudentCourse course : courses) {
            coursesExamGrade.add(course.getExamGrade());
        }
        return coursesExamGrade;
    }

    private void getCourseFromDB(String studentID){
        try {

            ResultSet coursesRS = DBConnection.getCoursesByStudentID(studentID);

            while(coursesRS.next()){
                courses.add(new StudentCourse(coursesRS.getString(2),coursesRS.getString(6),
                        coursesRS.getInt(3),coursesRS.getInt(4)));
            }
            coursesRS.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
