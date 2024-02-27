package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
public class DB {
    private final String HOST = "jdbc:mysql://localhost:3306/StudentGrades";
 //  private   final String HOST = "jdbc:mysql://localhost:3306/School?allowPublicKeyRetrieval=true&useSSL=false";

    private final String DATABASE_USER = "root";
    private final String DATABASE_PASSWORD = "12345678";
    private Connection connection;
    private static DB instance = null;

    private DB() {
        connectToDB();
    }

    public static DB getInstance()
    {
        if (instance == null)
        {
            instance = new DB();
        }
        return instance;
    }

    private void connectToDB()
    {


        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(HOST, DATABASE_USER, DATABASE_PASSWORD);
            System.out.println("Successfully Connected to the database");
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    private CourseINFO getCourseStatistics(String courseName, String type)
    {
        if (courseName == null) {
            throw new NullPointerException();
        }


        if (!courseName.equalsIgnoreCase("spanish") &&
                !courseName.equalsIgnoreCase("arabic") &&
                !courseName.equalsIgnoreCase("english"))
        {
            throw new IllegalStateException("The course name is wrong");
        }

        try {
            Statement statement = connection.createStatement();
            String sql = "select " + type + "(" + courseName + ") from MARKS";
            ResultSet rs = statement.executeQuery(sql);

            CourseINFO result = null;
            while (rs.next()) {
                result = new CourseINFO(courseName, rs.getDouble(1));
            }

            return result;
        }
        catch (Exception e) {
            throw new IllegalStateException();
        }
    }

    public Double getCourseAverage(String courseName) {
        return getCourseStatistics(String.valueOf(courseName), "AVG").getMark();
    }

    public Double getCourseMinMark(String courseName) {
        return getCourseStatistics(String.valueOf(courseName), "MIN").getMark();
    }

    public Double getCourseMaxMark(String courseName) {
        return getCourseStatistics(String.valueOf(courseName), "MAX").getMark();
    }

    public ArrayList<CourseINFO> getAllMarks(int id) {
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select arabic, english, spanish from MARKS where id=" + id);

            ArrayList<CourseINFO> result = new ArrayList<>();
            while (rs.next()) {
                result.add(new CourseINFO("Arabic Course", rs.getDouble(1)));
                result.add(new CourseINFO("English Course", rs.getDouble(2)));
                result.add(new CourseINFO("Spanish Course", rs.getDouble(3)));
            }

            return result;
        }
        catch (Exception e) {
            throw new IllegalStateException();
        }
    }

    public CourseINFO getCourseMark(String courseName, int id) {
        if (courseName == null) {
            throw new NullPointerException();
        }

        courseName = courseName.toLowerCase();
        if (!courseName.equals("arabic") && !courseName.equals("english") && !courseName.equals("spanish")) {
            throw new IllegalStateException("Invalid course name");
        }

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select " + courseName + " from MARKS where id=" + id);

            CourseINFO result = null;
            while (rs.next()) {
                result = new CourseINFO(courseName, rs.getDouble(1));
            }

            return result;
        }
        catch (Exception e) {
            throw new IllegalStateException();
        }
    }

    public boolean isRegisteredStudent(int id) {
        try {
            String sql = "select name from STUDENTS where id=" + id;
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                return true;
            }
            return false;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isValidPassword(int id, String enteredPassword) {
        if (enteredPassword == null) {
            throw new NullPointerException();
        }

        try {
            String sql = "select password from STUDENTS where id=" + id;
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            String password = null;
            while (rs.next()) {
                password = rs.getString(1);
            }

            return password.equals(enteredPassword);
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public CourseINFO getMedian(String courseName){
        CourseINFO course = null;
        try {
            Statement statement =connection.createStatement();
            statement.execute("SET @row_index := -1");
            ResultSet result = statement.executeQuery(
                    "SELECT AVG( subq." + courseName + ") as median_value " +
                            "FROM (" +
                            "    SELECT @row_index:=@row_index + 1 AS row_index, " + courseName +
                            "    FROM MARKS" +
                            "    ORDER BY " + courseName +
                            "  ) AS subq" +
                            "  WHERE subq.row_index " +
                            "  IN (FLOOR(@row_index / 2) , CEIL(@row_index / 2))"
            );
            if(!result.next()){
                System.out.println("The result of the query is empty , check again.");
            }
            else{
                course = new CourseINFO(courseName , result.getDouble(1));
            }
            return course;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
