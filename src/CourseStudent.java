public class CourseStudent extends User{
    private int midTermGrade;
    private int examGrade;
    public CourseStudent(String id, String firstName, String lastName, String address, String phoneNum, String email, String password, int midTermGrade, int examGrade) {
        super(id, firstName, lastName, address, phoneNum, email, password);
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
