package main.SocketSystem.server.example.DataBase;

import main.SocketSystem.server.example.Server.ServerDB;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Response {
    public void receive(ObjectInputStream input, ObjectOutputStream output) throws IOException {
        ServerDB server = new ServerDB();
        active:
        while (true) {

            int requestType = input.readInt();//the service from the user

            switch (requestType) {
                case 1: {
                    int id = input.readInt();
                    output.writeObject(server.getAllCourses(id));
                    output.flush();
                }
                break;

                case 2: {
                    int id = input.readInt();

                    String courseName = input.readUTF();

                    output.writeObject(server.getCourse(courseName, id));//course mark
                    output.flush();
                }
                break;

                case 3: {
                    String courseName = input.readUTF();
                    output.writeDouble(server.getCourseAverage(courseName));
                    output.flush();
                }
                break;

                case 4: {
                    String courseName = input.readUTF();
                    output.writeDouble(server.getCourseMaxMark(courseName));
                    output.flush();
                }
                break;

                case 5: {
                    String courseName = input.readUTF();
                    output.writeDouble(server.getCourseMinMark(courseName));
                    output.flush();
                }
                break;
                case 6: {
                    String courseName = input.readUTF();
                    output.writeDouble(server.getMedian(courseName));
                    output.flush();
                }
                break;
                case 0:
                    break active;

                default:
                    throw new IllegalStateException("Operation is not supported from the server");
            }
        }
    }
}
