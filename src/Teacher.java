import java.sql.ResultSet;
import java.util.ArrayList;

public class Teacher extends User{
    private ArrayList<Course> courses;
    public Teacher(ResultSet rs) {
        super(rs);

    }

}
