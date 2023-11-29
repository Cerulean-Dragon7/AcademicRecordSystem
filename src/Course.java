import java.sql.ResultSet;
import java.sql.SQLException;

public class Course {
    private  String courseID;
    private  String courseTitle;
    private int midTermGrade;
    private int examGrade;

    public Course(ResultSet rs) {
        try {
            this.courseID = rs.getString(2);
            this.courseTitle = rs.getString(6);
            this.midTermGrade = rs.getInt(3);
            this.examGrade = rs.getInt(4);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public int getMidTermGrade() {
        return midTermGrade;
    }

    public void setMidTermGrade(int midTermGrade) {
        this.midTermGrade = midTermGrade;
    }

    public int getExamGrade() {
        return examGrade;
    }

    public void setExamGrade(int examGrade) {
        this.examGrade = examGrade;
    }
}
