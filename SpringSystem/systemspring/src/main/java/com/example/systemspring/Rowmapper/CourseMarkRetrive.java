package com.example.systemspring.Rowmapper;

import com.example.systemspring.model.Course;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.ArrayList;


public class CourseMarkRetrive  implements RowMapper<ArrayList<Course>> {
    private final ArrayList<String> coursesNames = new ArrayList<>(
            Arrays.asList(
                    "Arabic",
                    "English",
                    "Spanish"
            )
    );
    @Override
    public ArrayList<Course> mapRow(ResultSet resultSet, int i) throws SQLException {
        ArrayList<Course> marks = new ArrayList<>();
        for (String cName : coursesNames) {
            marks.add(new Course(cName, resultSet.getDouble(cName)));
        }
        return marks;
    }
}
