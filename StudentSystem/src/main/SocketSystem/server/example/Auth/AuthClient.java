package main.SocketSystem.server.example.Auth;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;
public class AuthClient {
    private int id;
    private String password;
    private boolean isAuthenticated = false;
    private Scanner scanner = new Scanner(System.in);

    private void getInfo() {
        System.out.println("Enter Your student id:");
        id = scanner.nextInt();
        System.out.println("Enter Your student password:");
        password = scanner.next();
    }

    public boolean isAuthenticated() {
        return isAuthenticated();
    }

    public int getId() {
        return id;
    }

    public void run(ObjectInputStream fromServer, ObjectOutputStream toServer) throws IOException, ClassNotFoundException {

        while (!isAuthenticated)
        {
            getInfo();

            toServer.writeInt(id);
            toServer.flush();
            toServer.writeUTF(password);
            toServer.flush();

            System.out.println("OUR --> "+fromServer.readObject());
            isAuthenticated = fromServer.readBoolean();
        }
    }
}
