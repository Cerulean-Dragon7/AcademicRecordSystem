import java.util.ArrayList;

public class User {
    private String id;
    private String firstName;
    private String lastName;
    private String address;
    private String phoneNum;
    private String email;
    private String password;

    public User(String id, String firstName, String lastName, String address, String phoneNum, String email, String password){

        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNum = phoneNum;
        this.email = email;
        this.password = password;
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
    public void setPersonInformation(ArrayList<String> information){
        setFirstName(information.get(0));
        setLastName(information.get(1));
        setAddress(information.get(2));
        setPhoneNum(information.get(3));
        setEmail(information.get(4));
        setPassword(information.get(5));
    }

}
