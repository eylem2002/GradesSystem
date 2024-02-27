package com.example.systemspring.service.database;

import com.example.systemspring.model.Course;
import java.util.ArrayList;
import org.springframework.jdbc.core.JdbcTemplate;
import com.example.systemspring.Rowmapper.CourseMarkRetrive;
import org.springframework.stereotype.Service;

@Service
public class DB {
    private JdbcTemplate jdbcTemplate;

    public DB(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public boolean isValidStudent(int studentId) {
        String SQL = "SELECT password FROM STUDENTS WHERE id = ?";

        String password = jdbcTemplate.queryForObject(SQL, new Object[]{studentId}, String.class);
        return (password != null);
    }

    public boolean isValidPassword(int studentId, String password) {
        final String SQL = "SELECT password FROM STUDENTS WHERE id = ?";
        String correctPassword = jdbcTemplate.queryForObject(SQL, new Object[]{studentId}, String.class);
        return correctPassword.equals(password);
    }

    public ArrayList<Course> getAllStudentMarks(int studentId)  {
        final String SQL = "SELECT arabic, english, spanish FROM MARKS WHERE id = " + studentId;
        ArrayList<Course> courses = jdbcTemplate.query(SQL , new CourseMarkRetrive()).get(0);
        return courses;
    }

    public Course getStudentMark(int studentId, String courseName)  {
        final String SQL = "SELECT " + courseName + " FROM MARKS WHERE id = ?";
        Double mark = jdbcTemplate.queryForObject(SQL, new Object[]{studentId}, Double.class);
        return new Course(courseName, mark);
    }

    public Course getAvg(String courseName) {
        final String SQL = "SELECT AVG( "+ courseName +" ) FROM MARKS";
        Double mark = jdbcTemplate.queryForObject(SQL, Double.class);
        return new Course(courseName, mark);
    }

    public Course getMax(String courseName) {
        final String SQL = "SELECT MAX( "+ courseName +" ) FROM MARKS";
        Double mark = jdbcTemplate.queryForObject(SQL, Double.class);
        return new Course(courseName, mark);
    }

    public Course getMin(String courseName) {
        final String SQL = "SELECT MIN( "+courseName +" ) FROM MARKS";
        Double mark = jdbcTemplate.queryForObject(SQL , Double.class);
        return new Course(courseName, mark);
    }

    public Course getMedian(String courseName){
        final String SQL = "SELECT AVG( subq." + courseName + ") as median_value " +
                "FROM (" +
                "    SELECT @row_index:=@row_index + 1 AS row_index, " + courseName +
                "    FROM MARKS" +
                "    ORDER BY " + courseName +
                "  ) AS subq" +
                "  WHERE subq.row_index " +
                "  IN (FLOOR(@row_index / 2) , CEIL(@row_index / 2))";
        jdbcTemplate.execute("SET @row_index := -1");
        Double mark = jdbcTemplate.queryForObject(SQL , Double.class);
        return new Course(courseName, mark);
    }

}
