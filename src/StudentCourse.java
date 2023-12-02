import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentCourse extends Course{

    private int midTermGrade;
    private int examGrade;

    public StudentCourse(String courseID,String courseTitle,int midTermGrade, int examGrade) {
        super(courseID,courseTitle);
        this.midTermGrade = midTermGrade;
        this.examGrade = examGrade;
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
