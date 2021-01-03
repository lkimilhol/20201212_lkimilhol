package com.mathflat.SimpleServer.controller;

import com.mathflat.SimpleServer.domain.Student;
import com.mathflat.SimpleServer.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/student/new")
    public String createFrom() {
        return "student/createStudentForm";
    }

    @PostMapping("/student/new")
    public String create(StudentForm form) {
        Student student = new Student();
        student.setName(form.getName());

        studentService.add(student);

        return "redirect:/student/new";
    }

    @GetMapping("/student/studentList")
    public String list(Model model) {
        List<Student> students = studentService.findStudentList();
        model.addAttribute("students", students);
        return "student/studentList";
    }

    @GetMapping("/student/delete")
    public String deleteFrom() {
        return "student/deleteStudentForm";
    }

    @DeleteMapping("/student/delete")
    public String delete(StudentForm form) {
        studentService.delete(form.getName());
        return "redirect:/student/delete";
    }
}
