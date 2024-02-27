package main.SocketSystem.server.example.Client;

import main.SocketSystem.server.example.Auth.AuthClient;
import main.SocketSystem.server.example.DataBase.StdRequest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
    static AuthClient authenticationRequest;
    static StdRequest stdreq;
    static ObjectInputStream fromServer;
    static ObjectOutputStream toServer;

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 8090);

            toServer = new ObjectOutputStream(socket.getOutputStream());
            fromServer = new ObjectInputStream(socket.getInputStream());
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }

        authenticationRequest = new AuthClient();
        try {
            authenticationRequest.run(fromServer, toServer);

        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        int studentId = authenticationRequest.getId();

        stdreq = new StdRequest(studentId);
        try {

            stdreq.reciveMarks(fromServer, toServer);
        }
        catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }

    }

}
