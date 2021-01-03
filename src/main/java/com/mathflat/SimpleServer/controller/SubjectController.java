package com.mathflat.SimpleServer.controller;

import com.mathflat.SimpleServer.domain.Subject;
import com.mathflat.SimpleServer.service.SubjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class SubjectController {
    private final SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping("/subject/new")
    public String createFrom() {
        return "subject/createSubjectForm";
    }

    @PostMapping("/subject/new")
    public String create(StudentForm form) {
        Subject subject = new Subject();
        subject.setName(form.getName());

        subjectService.add(subject);

        return "redirect:/subject/new";
    }

    @GetMapping("/subject/subjectList")
    public String list(Model model) {
        List<Subject> subjects = subjectService.findSubjectList();
        model.addAttribute("subjects", subjects);
        return "subject/subjectList";
    }

    @GetMapping("/subject/delete")
    public String deleteFrom() {
        return "subject/deleteSubjectForm";
    }

    @DeleteMapping("/subject/delete")
    public String delete(StudentForm form) {
        subjectService.remove(form.getName());
        return "redirect:/subject/delete";
    }
}
