package main.SocketSystem.server.example.DataBase;




import main.SocketSystem.server.example.Student.Course;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class StdRequest {
    private int studentId;
    private Scanner scanner = new Scanner(System.in);

    public StdRequest(int id) {
        studentId = id;
    }

    private void printList() {
        System.out.println("Enter an number from [1-6] : ");
        System.out.println("1--> All marks in all the courses");
        System.out.println("2--> The mark in a Specific course");
        System.out.println("3-->  Average in a Specific course");
        System.out.println("4-->  Max mark in a Specific course");
        System.out.println("5-->  Min mark in a Specific course");
        System.out.println("6-->  Median mark in a Specific course");
    }

    private void printCourses() {
        System.out.println("Enter course number:");
        System.out.println("1) Arabic");
        System.out.println("2) English");
        System.out.println("3) Spanich");
    }

    private String getCourseName() {
        while (true) {
            int choice = scanner.nextInt();
            if (choice == 1)
                return "arabic";
            else if (choice == 2)
                return "english";
            else if (choice == 3)
                return "spanish";
        }
    }

    public void reciveMarks(ObjectInputStream fromServer, ObjectOutputStream toServer) throws IOException, ClassNotFoundException {

        active:
        while (true) {

            printList();

            int requestType = scanner.nextInt();

            toServer.writeInt(requestType);
            toServer.flush();

            switch(requestType) {
                case 1: {
                    toServer.writeInt(studentId);
                    toServer.flush();
                    ArrayList<Course> courses = (ArrayList<Course>) fromServer.readObject(); //recive all the  makers in all courses
                    for (Course c : courses) {
                        System.out.println(c);
                    }
                }
                break;

                case 2: {
                    printCourses();
                    String courseName = getCourseName();
                    toServer.writeInt(studentId);      toServer.flush();
                    toServer.writeUTF(courseName);  toServer.flush();

                    System.out.println(fromServer.readObject());//print the mark on single course

                }
                break;

                case 3: case 4: case 5: case 6: {
                    printCourses();
                    String courseName = getCourseName();

                    toServer.writeUTF(courseName);

                    toServer.flush();
                    System.out.println(fromServer.readDouble());
                }
                break;

                case 0:
                    break active;
                default:
                    System.out.println("Invalid Option ");
            }
        }
    }
}
