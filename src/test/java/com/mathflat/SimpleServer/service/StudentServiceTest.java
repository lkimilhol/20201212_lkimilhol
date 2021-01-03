package com.mathflat.SimpleServer.service;

import com.mathflat.SimpleServer.domain.Student;
import com.mathflat.SimpleServer.repository.jpa.ScoreRepositoryJPA;
import com.mathflat.SimpleServer.repository.jpa.StudentRepositoryJPA;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback
public class StudentServiceTest {

    @Autowired
    private EntityManager em;

    private StudentService studentService;

    @Test
    public void 학생추가() {
        //given
        studentService = new StudentService(new StudentRepositoryJPA(em));

        Student student = new Student();
        Date date = new Date();

        student.setName("lkimilhol");
        student.setUpdateTime(date);
        student.setInsertTime(date);

        //when
        studentService.add(student);

        //then
        Long id = student.getId();
        assertTrue(id > 0);
    }

    @Test
    public void 학생리스트불러오기() {
        //given
        studentService = new StudentService(new StudentRepositoryJPA(em));
        Student student1 = new Student();
        Date date = new Date();

        student1.setName("lkimilhol1");
        student1.setUpdateTime(date);
        student1.setInsertTime(date);

        Student student2 = new Student();

        student2.setName("lkimilhol2");
        student2.setUpdateTime(date);
        student2.setInsertTime(date);

        //when
        studentService.add(student1);
        studentService.add(student2);

        //then
        List<Student> studentList = studentService.findStudentList();
        assertTrue(studentList.size() == 2);
    }

    @Test
    public void 학생삭제(){
        //given
        studentService = new StudentService(new StudentRepositoryJPA(em));
        Student student = new Student();
        Date date = new Date();

        student.setName("lkimilhol");
        student.setInsertTime(date);
        student.setUpdateTime(date);

        studentService.add(student);
        studentService.scoreService = new ScoreService((new ScoreRepositoryJPA(em)));

        //then
        studentService.delete("lkimilhol");
        List<Student> studentList = studentService.findStudentList();
        assertTrue(studentList.size() == 0);
    }

    @Test
    public void 학생삭제_없는학생() {
        //given
        studentService = new StudentService(new StudentRepositoryJPA(em));

        //when
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> studentService.delete("test"));
    }
}
