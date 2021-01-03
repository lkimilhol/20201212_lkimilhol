package com.mathflat.SimpleServer.service;

import com.mathflat.SimpleServer.domain.Subject;
import com.mathflat.SimpleServer.repository.jpa.ScoreRepositoryJPA;
import com.mathflat.SimpleServer.repository.jpa.SubjectRepositoryJPA;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import javax.persistence.EntityManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback
public class SubjectServiceTest {
    @Autowired
    private EntityManager em;

    private SubjectService subjectService;

    @Test
    public void 과목생성() {
        //given
        subjectService = new SubjectService(new SubjectRepositoryJPA(em));

        Subject subject = new Subject();
        subject.setName("math");

        //when
        subjectService.add(subject);

        //then
        Long id = subject.getId();
        assertTrue(id > 0);
    }

    @Test
    public void 과목리스트불러오기() {
        //given
        subjectService = new SubjectService(new SubjectRepositoryJPA(em));

        Subject subject = new Subject();
        subject.setName("math");

        //when
        subjectService.add(subject);

        //then
        List<Subject> subjectList = subjectService.findSubjectList();
        assertTrue(subjectList.size() == 1);
    }

    @Test
    public void 과목삭제() {
        //given
        subjectService = new SubjectService(new SubjectRepositoryJPA(em));

        Subject subject = new Subject();
        subject.setName("math");

        //when
        subjectService.add(subject);
        subjectService.scoreService = new ScoreService((new ScoreRepositoryJPA(em)));

        //then
        subjectService.remove("math");
        List<Subject> subjectList = subjectService.findSubjectList();
        assertTrue(subjectList.size() == 0);
    }

    @Test
    public void 과목삭제_없는과목() {
        //given
        subjectService = new SubjectService(new SubjectRepositoryJPA(em));

        //when
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> subjectService.remove("test"));
    }
}
