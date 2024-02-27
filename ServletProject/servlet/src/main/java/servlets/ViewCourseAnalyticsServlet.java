package servlets;

import Auth.AuthenticationService;
import database.DB;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "ViewCourseAnalyticsServlet", value = "/viewAnalytics")
public class ViewCourseAnalyticsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String directToPage;
        if (AuthenticationService.getInstance().isAuthenticated()) {
            directToPage = "/chooseCourse.jsp";
            request.setAttribute("action", "viewAnalytics");
        }
        else {
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

        String courseName = request.getParameter("course");
        Double minMark = DB.getInstance().getCourseMinMark(courseName);
        Double maxMark = DB.getInstance().getCourseMaxMark(courseName);
        Double average = DB.getInstance().getCourseAverage(courseName);
        Double median = DB.getInstance().getMedian(courseName).getMark();
        request.setAttribute("median",median);
        request.setAttribute("minMark", minMark);
        request.setAttribute("maxMark", maxMark);
        request.setAttribute("average", average);
        RequestDispatcher requestsDispatcher = request.getRequestDispatcher("/viewAnalytics.jsp");
        requestsDispatcher.forward(request, response);
    }
}
