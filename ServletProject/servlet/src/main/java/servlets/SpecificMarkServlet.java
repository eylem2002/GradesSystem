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
@WebServlet(name = "ViewSingleMarkServlet", value = "/viewSingle")
public class SpecificMarkServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String directToPage;
        if (AuthenticationService.getInstance().isAuthenticated()) {
            directToPage = "/chooseCourse.jsp";
            request.setAttribute("action", "viewSingle");
        } else
        {
            directToPage = "/login.html";
        }

        RequestDispatcher requestsDispatcher = request.getRequestDispatcher(directToPage);
        requestsDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!AuthenticationService.getInstance().isAuthenticated()) {
            RequestDispatcher requestsDispatcher = request.getRequestDispatcher("/login.html");
            requestsDispatcher.forward(request, response);
            return;
        }

        int studentId = AuthenticationService.getInstance().getStudentId();
        String courseName = request.getParameter("course");
        CourseINFO mark = DB.getInstance().getCourseMark(courseName, studentId);
        request.setAttribute("mark", mark);
        RequestDispatcher requestsDispatcher = request.getRequestDispatcher("/viewMark.jsp");
        requestsDispatcher.forward(request, response);
    }
}
