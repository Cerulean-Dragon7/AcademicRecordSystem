public class InputValidation {
    public static boolean checkTeacherID(String s){
        return s.matches("^t\\d{7}$");
    }
    public static boolean checkStudentID(String s){
        return s.matches("^s\\d{7}$");
    }
    public static boolean checkFirstName(String s){
        return s.matches("^.{1,20}$");
    }
    public static boolean checkLastName(String s){
        return s.matches("^.{1,20}$");
    }
    public static boolean checkAddress(String s){
        return s.matches("^.{1,50}$");
    }
    public static boolean checkPhoneNumber(String s) {
        return s.matches("^\\d{8,10}$");
    }
    public static boolean checkEmail(String s){
        return s.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    }
    public static boolean checkPassword(String s){
        return s.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,20}$");
    }
    public static boolean checkScore(String s){
        return s.matches("^([1-9][0-9]?|100|0)$");
    }
}
