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
import model.Course;
import model.Student;
import repository.CourseRepository;
import repository.StudentRepository;

import java.io.IOException;
import java.util.List;

/**
 *
 * @author LENOVO
 */
@WebServlet(name = "StudentServlet", urlPatterns = {"/students"})
public class StudentServlet extends HttpServlet {
    private final StudentRepository studentRepo = new StudentRepository();
    private final CourseRepository courseRepo = new CourseRepository();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        
        if ("detail".equals(action)){
            int id = Integer.parseInt(request.getParameter("id"));
            Student student = studentRepo.findById(id);
            List<Course> availableCourses = courseRepo.findCoursesNotEnrolledByStudent(id);
            request.setAttribute("student", student);
            request.setAttribute("availableCourses", availableCourses);
            request.getRequestDispatcher("jsp/student_detail.jsp").forward(request, response);
        } else {
            List<Student> students=studentRepo.findAll();
            request.setAttribute("students", students);
            request.getRequestDispatcher("jsp/student_list.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("add".equals(action)){
            Student student= new Student();
            student.setName(request.getParameter("name"));
            student.setMail(request.getParameter("mail"));
            studentRepo.addStudent(student);
            response.sendRedirect("students");
        } else if ("enroll".equals(action)){
            int studentId = Integer.parseInt(request.getParameter("studentId"));
            int courseId = Integer.parseInt(request.getParameter("courseId"));
            studentRepo.enrollStudent(studentId, courseId);
            response.sendRedirect("students?action=detail&id="+studentId);
        }
    }
}
