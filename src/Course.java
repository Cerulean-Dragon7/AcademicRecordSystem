public class Course {
    private  String courseID;
    private  String courseTitle;

    public Course(String courseID,String courseTitle){
        this.courseID = courseID;
        this.courseTitle = courseTitle;
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
}
