package Auth;

import database.DB;

public class AuthenticationService {
    DB mysql = DB.getInstance();
    private int studentId;
    private boolean isAuthenticated = false;
    private static AuthenticationService instance = null;
    public static AuthenticationService getInstance() {
        if (instance == null) {
            instance = new AuthenticationService();
        }
        return instance;
    }

    private AuthenticationService() { }

    private void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getStudentId() {
        return studentId;
    }

    public boolean isAuthenticated() {
        return isAuthenticated;
    }
    public void logout(){
        instance = null;
        System.out.println("Logout --> End the session ");
    }

    public boolean authenticate(int id, String password) {
        if (!mysql.isRegisteredStudent(id)) {
            isAuthenticated = false;
        }
        if (!mysql.isValidPassword(id, password)) {
            isAuthenticated = false;
        }
        else {
            isAuthenticated = true;
            setStudentId(id);
            System.out.println("The Student ID ---> "+id);
        }
        return isAuthenticated;
    }
}
