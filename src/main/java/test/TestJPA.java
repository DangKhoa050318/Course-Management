/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;
import model.Student;
import repository.StudentRepository;
public class TestJPA {
    public static void main(String[] args){
        Student s = new Student();
        s.setName("Test Fen");
        s.setMail("fen@test.com");
        
        StudentRepository repo = new StudentRepository();
        repo.addStudent(s);
        
        System.out.println("Danh sách học viện: ");
        for (Student st:repo.findAll()){
            System.out.println(st.getId()+" - "+ st.getName());
        }
    }
}
