package main.SocketSystem.server.example.Server;

import main.SocketSystem.server.example.Auth.AuthServer;
import main.SocketSystem.server.example.DataBase.Response;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    static ServerSocket serverSocket;
    static ServerDB serverDatabase;
    static Socket socket;
    static ObjectOutputStream outputToClient;
    static ObjectInputStream inputFromClient;
    public static void main(String[] args) {
        new Thread( () -> {
            try {
                serverSocket = new ServerSocket(8090);
                serverDatabase = new ServerDB();


                socket = serverSocket.accept();


                outputToClient = new ObjectOutputStream(socket.getOutputStream());
                inputFromClient = new ObjectInputStream(socket.getInputStream());
                System.out.println("FIRST");

                AuthServer authServer=new AuthServer();

                authServer.check(inputFromClient,outputToClient);//This to check the id and the pass //send msg to client auth is done

                Response res=new Response();
                res.receive(inputFromClient,outputToClient);//after get the number of service from the user

            }
            catch(IOException ex) {
                ex.printStackTrace();
            }




        }).start();

    }



}

