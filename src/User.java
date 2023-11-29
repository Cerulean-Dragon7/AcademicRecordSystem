import java.sql.ResultSet;
import java.sql.SQLException;

public class User {
    private String id;
    private String firstName;
    private String lastName;
    private String address;
    private String phoneNum;
    private String email;
    private String password;

    public User(ResultSet rs){
        try{
            this.id = rs.getString(1);
            this.firstName = rs.getString(2);
            this.lastName = rs.getString(3);
            this.address = rs.getString(4);
            this.phoneNum = rs.getString(5);
            this.email = rs.getString(6);
            this.password = rs.getString(7);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public User(){

    }

    public String getId() {
        return id;
    }

    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public String getPhoneNum(){
        return phoneNum;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail(){
        return email;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
