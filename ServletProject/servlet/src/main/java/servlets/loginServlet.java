package servlets;

import Auth.AuthenticationService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "LoginServlet", value = "/login")
public class loginServlet extends HttpServlet {
    private AuthenticationService authenticationService = AuthenticationService.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String directToPage;
        if (authenticationService.isAuthenticated())
        {
            directToPage = "/loggedIn.html";
        } else {
            directToPage = "/login.html";
        }

        RequestDispatcher requestsDispatcher = request.getRequestDispatcher(directToPage);
        requestsDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (authenticationService.isAuthenticated()) {
            RequestDispatcher requestsDispatcher = request.getRequestDispatcher("/loggedIn.html");
            requestsDispatcher.forward(request, response);
            return;
        }

        int id = Integer.parseInt(request.getParameter("id"));
        String password = request.getParameter("password");

        authenticationService.authenticate(id, password);

        RequestDispatcher requestDispatcher;
        if (authenticationService.isAuthenticated()) {
            requestDispatcher = request.getRequestDispatcher("/loggedIn.html");
        } else {
            requestDispatcher = request.getRequestDispatcher("/invaliduser.html");
        }
        requestDispatcher.forward(request,response);
    }
}
