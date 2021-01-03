package com.mathflat.SimpleServer.service;

import com.mathflat.SimpleServer.domain.Score;
import com.mathflat.SimpleServer.domain.Student;
import com.mathflat.SimpleServer.domain.Subject;
import com.mathflat.SimpleServer.repository.jpa.ScoreRepositoryJPA;
import com.mathflat.SimpleServer.repository.jpa.StudentRepositoryJPA;
import com.mathflat.SimpleServer.repository.jpa.SubjectRepositoryJPA;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback
public class ScoreServiceTest {
    @Autowired
    private EntityManager em;

    private StudentService studentService;
    private SubjectService subjectService;
    private ScoreService scoreService;

    @Test
    public void 점수추가() {
        //given
        studentService = new StudentService(new StudentRepositoryJPA(em));
        subjectService = new SubjectService(new SubjectRepositoryJPA(em));
        scoreService = new ScoreService(new ScoreRepositoryJPA(em));

        String studentName = "학생1";
        String subjectName = "수학";
        int studentScore = 100;

        Date date = new Date();
        Student student = new Student();
        student.setName(studentName);
        student.setInsertTime(date);
        student.setUpdateTime(date);
        studentService.add(student);

        Subject subject = new Subject();
        subject.setName(subjectName);
        subjectService.add(subject);

        Score score = new Score();
        score.setStudent(student);
        score.setSubject(subject);
        score.setScore(studentScore);
        score.setInsertTime(date);
        score.setUpdateTime(date);
        Long scoreId = scoreService.add(score);

        assertTrue(scoreId > 0);
    }

    @Test
    public void 점수삭제() {
        //given
        studentService = new StudentService(new StudentRepositoryJPA(em));
        subjectService = new SubjectService(new SubjectRepositoryJPA(em));
        scoreService = new ScoreService(new ScoreRepositoryJPA(em));

        String studentName = "학생1";
        String subjectName = "수학";
        int studentScore = 100;

        Date date = new Date();
        Student student = new Student();
        student.setName(studentName);
        student.setInsertTime(date);
        student.setUpdateTime(date);
        studentService.add(student);

        Subject subject = new Subject();
        subject.setName(subjectName);
        subjectService.add(subject);

        Score score = new Score();
        score.setStudent(student);
        score.setSubject(subject);
        score.setScore(studentScore);
        score.setInsertTime(date);
        score.setUpdateTime(date);
        Long scoreId = scoreService.add(score);

        scoreService.delete(scoreId);
        List<Score> scoreList = scoreService.findScoreList();
        assertTrue(scoreList.size() == 0);
    }

    @Test
    public void 점수삭제_없는점수() {
        //given
        scoreService = new ScoreService(new ScoreRepositoryJPA(em));

        //when
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> scoreService.delete(1));
    }

}
