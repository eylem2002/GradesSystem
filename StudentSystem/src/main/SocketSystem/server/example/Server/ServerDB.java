package main.SocketSystem.server.example.Server;


import main.SocketSystem.server.example.DataBase.DB;
import main.SocketSystem.server.example.Student.Course;

import java.util.ArrayList;
public class ServerDB {
    DB dataBase;

    public ServerDB() {
        dataBase = new DB();
    }

    public ArrayList<Course> getAllCourses(int id) {
        return dataBase.getAllCourses(id);
    }

    public boolean isRegisteredStudent(int id) {
        return dataBase.isRegisteredStudent(id);
    }

    public boolean isValidPassword(int id, String enteredPassword) {
        return dataBase.isValidPassword(id, enteredPassword);
    }

    public Course getCourse(String courseName, int id) {
        return dataBase.getCourse(courseName, id);
    }

    public double getCourseAverage(String courseName) {
        return dataBase.getCourseAverage(courseName);
    }

    public double getCourseMaxMark(String courseName) {
        return dataBase.getCourseMaxMark(courseName);
    }

    public double getCourseMinMark(String courseName) {
        return dataBase.getCourseMinMark(courseName);
    }
    public double getMedian(String courseName) {
        return dataBase.getMedian(courseName).getMark();
    }
}
