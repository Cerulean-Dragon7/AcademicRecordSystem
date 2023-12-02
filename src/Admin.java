import java.sql.ResultSet;

public class Admin extends User{
    public Admin(String id, String firstName, String lastName, String address, String phoneNum, String email, String password){
        super(id, firstName, lastName, address, phoneNum, email, password);
    }
}
