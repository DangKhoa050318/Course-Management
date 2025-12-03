/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import repository.CourseRepository;
import model.Course;
import java.util.List;

/**
 *
 * @author LENOVO
 */
@WebServlet(name = "CourseServlet", urlPatterns = {"/courses"})
public class CourseServlet extends HttpServlet {

    private final CourseRepository courseRepo = new CourseRepository();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("detail".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            Course course = courseRepo.findById(id);
            if (course == null) {
                request.setAttribute("error", "Course not found");
                response.sendRedirect("courses");
                return;
            }
            request.setAttribute("course", course);
            request.getRequestDispatcher("jsp/course_detail.jsp").forward(request, response);

        } else {
            List<Course> courses = courseRepo.findAll();
            courses.sort((c1, c2) -> Integer.compare(c2.getStudents().size(), c1.getStudents().size()));
            request.setAttribute("courses", courses);
            request.getRequestDispatcher("jsp/course_list.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String title = request.getParameter("title");
        String description = request.getParameter("description");

        if (title == null || title.isBlank()) {
            request.setAttribute("error", "Title is required");
            doGet(request, response);
            return;
        }

        Course course = new Course();
        course.setTitle(title);
        course.setDescription(description);

        courseRepo.addCourse(course);
        response.sendRedirect("courses");
    
    }

}
