package main.SocketSystem.server.example.Auth;

import main.SocketSystem.server.example.Server.ServerDB;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class AuthServer {
    private ServerDB server = new ServerDB();
    public void check(ObjectInputStream input , ObjectOutputStream output) throws IOException {
        while (true) {
            int id = input.readInt();
            String password = input.readUTF();

            if (!server.isRegisteredStudent(id)) {
                output.writeObject("Unregistered student !!");
                output.flush();
                output.writeBoolean(false);
                output.flush();
            }
            else if (!server.isValidPassword(id, password)) {
                output.writeObject("Password is incorrect !!");
                output.flush();
                output.writeBoolean(false);
                output.flush();
            }
            else {
                output.writeObject("Authenticated Done");
                output.flush();
                output.writeBoolean(true);
                output.flush();
                break;
            }
        }
    }
}
