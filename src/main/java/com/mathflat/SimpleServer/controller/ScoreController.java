package com.mathflat.SimpleServer.controller;

import com.mathflat.SimpleServer.domain.Score;
import com.mathflat.SimpleServer.service.ScoreService;
import org.dom4j.rule.Mode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ScoreController {
    private final ScoreService scoreService;

    public ScoreController(ScoreService scoreService) {
        this.scoreService = scoreService;
    }

    @GetMapping("/score/new")
    public String createForm() {
        return "score/createScoreForm";
    }

    @GetMapping("/score/update")
    public String updateForm() {
        return "score/updateScoreForm";
    }

    @GetMapping("/score/delete")
    public String deleteForm() {
        return "score/deleteScoreForm";
    }

    @GetMapping("score/averageStudent")
    public String averageStudentForm() {
        return "score/averageStudentForm";
    }

    @GetMapping("/score/averageSubject")
    public String averageSubjectForm() {
        return "score/averageSubjectForm";
    }

    @GetMapping("/score/scoreList")
    public String list(Model model) {
        List<Score> scores = scoreService.findScoreList();
        model.addAttribute("scores", scores);
        return "score/scoreList";
    }

    @PostMapping("/score/new")
    public String create(ScoreForm form) {
        String studentName = form.getStudentName();
        String subjectName = form.getSubjectName();
        int score = form.getScore();

        scoreService.add(studentName, subjectName, score);
        return "redirect:/score/new";
    }

    @DeleteMapping("/score/delete")
    public String delete(ScoreDeleteForm form) {
        long id = form.getScoreId();
        scoreService.delete(id);
        return "redirect:/score/delete";
    }

    @PutMapping("/score/update")
    public String update(ScoreUpdateForm form) {
        long id = form.getScoreId();
        int score = form.getScore();
        scoreService.update(id, score);
        return "redirect:/score/update";
    }

    @PostMapping("/score/averageStudent")
    public String averageStudent(ScoreAverageForm form, Model m) {
        String s = form.getName();
        float f = scoreService.getAverageStudent(s);
        m.addAttribute("average", f);
        return "score/average";
    }

    @PostMapping("/score/averageSubject")
    public String averageSubject(ScoreAverageForm form, Model m) {
        String s = form.getName();
        float f = scoreService.getAverageSubject(s);
        m.addAttribute("average", f);
        return "score/average";
    }

    @GetMapping("score/average")
    public String getAverage() {
        return "score/average";
    }
}
