package main.SocketSystem.server.example.DataBase;
import main.SocketSystem.server.example.Student.Course;
import java.sql.*;
import java.util.ArrayList;

public class DB {
    private Connection connection;
    private final String HOST = "jdbc:mysql://localhost:3306/StudentGrades";
    private final String DATABASE_USER = "root";
    private final String DATABASE_PASSWORD = "12345678";

    public DB() {
        connectToDB();
    }

    private void connectToDB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(HOST, DATABASE_USER, DATABASE_PASSWORD);
            System.out.println("Successfully Connected to the database");
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }


    private Course getCourseStatistics(String courseName, String type) {
        if (courseName == null) {
            throw new NullPointerException();
        }


        if (!courseName.equalsIgnoreCase("arabic") &&
                !courseName.equalsIgnoreCase("english") &&
                !courseName.equalsIgnoreCase("spanish"))
        {
            throw new IllegalStateException("Invalid course name");
        }

        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT " + type + "(" + courseName + ") FROM MARKS";
            ResultSet rs = statement.executeQuery(sql);

            Course result = null;
            while (rs.next())
            {
                result = new Course(courseName, rs.getDouble(1));
            }

            return result;
        }
        catch (Exception e) {
            throw new IllegalStateException();
        }
    }

    public Double getCourseAverage(String courseName) {
        return getCourseStatistics(courseName, "AVG").getMark();
    }
    public Double getCourseMaxMark(String courseName) {
        return getCourseStatistics(courseName, "MAX").getMark();
    }

    public Double getCourseMinMark(String courseName) {
        return getCourseStatistics(courseName, "MIN").getMark();
    }


    public Course getMedian(String courseName){
        Course course = null;
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
            if(!result.next())
            {
                System.out.println("-----The result of the Query is empty-----");
            }
            else{
                course = new Course(courseName , result.getDouble(1));
            }
            return course;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Course> getAllCourses(int id) {
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select arabic, english, spanish FROM MARKS WHERE id=" + id);

            ArrayList<Course> result = new ArrayList<>();
            while (rs.next()) {
                result.add(new Course("Arabic Course", rs.getDouble(1)));
                result.add(new Course("English Course", rs.getDouble(2)));
                result.add(new Course("Spanish Course", rs.getDouble(3)));
            }

            return result;
        }
        catch (Exception e) {
            throw new IllegalStateException();
        }
    }

    public Course getCourse(String courseName, int id) {
        if (courseName == null) {
            throw new NullPointerException();
        }


        if (!courseName.equalsIgnoreCase("arabic") &&
                !courseName.equalsIgnoreCase("english") &&
                !courseName.equalsIgnoreCase("spanish"))
        {
            throw new IllegalStateException("Invalid course name");
        }

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select " + courseName + " from MARKS where id=" + id);

            Course result = null;
            while (rs.next()) {
                result = new Course(courseName, rs.getDouble(1));
            }

            return result;
        }
        catch (Exception e) {
            throw new IllegalStateException();
        }
    }


    public boolean isValidPassword(int id, String enteredPassword) {
        if (enteredPassword == null) {
            throw new NullPointerException();
        }

        try {
            String sql = "SELECT password from STUDENTS where id=" + id;
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            String pass = "";
            while (rs.next()) {
                pass = rs.getString(1);
            }

            return pass.equals(enteredPassword);


        }
        catch (Exception e) {
            throw new IllegalStateException();
        }
    }

    public boolean isRegisteredStudent(int id) {
        try {
            String sql = "SELECT name FROM STUDENTS WHERE id =" + id;
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next())
            {
                return true;
            }
            return false;
        }
        catch (Exception e) {
            throw new IllegalStateException();
        }

    }


}