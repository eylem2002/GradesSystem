package servlets;

import Auth.AuthenticationService;
import database.CourseINFO;
import database.DB;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "ViewAllMarksServlet", value = "/viewMarks")
public class ViewAllMarksServlet extends HttpServlet {
    private AuthenticationService authenticationService = AuthenticationService.getInstance();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!authenticationService.isAuthenticated())
        {
            RequestDispatcher requestsDispatcher = request.getRequestDispatcher("/login.html");
            requestsDispatcher.forward(request, response);

        }

        int studentId = AuthenticationService.getInstance().getStudentId();
        ArrayList<CourseINFO> marks = DB.getInstance().getAllMarks(studentId);
        request.setAttribute("marks", marks);
        RequestDispatcher requestsDispatcher = request.getRequestDispatcher("/viewAllMarks.jsp");
        requestsDispatcher.forward(request, response);
    }
}