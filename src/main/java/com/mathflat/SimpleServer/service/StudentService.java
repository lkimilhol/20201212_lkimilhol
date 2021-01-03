package com.mathflat.SimpleServer.service;

import com.mathflat.SimpleServer.controller.CustomErrorController;
import com.mathflat.SimpleServer.domain.Score;
import com.mathflat.SimpleServer.domain.Student;
import com.mathflat.SimpleServer.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Transactional
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Autowired
    ScoreService scoreService;

    /*
        학생 추가
     */
    public Long add(Student student) {
        Date date = new Date();

        validDuplicateStudent(student);

        student.setInsertTime(date);
        student.setUpdateTime(date);

        studentRepository.save(student);
        return student.getId();
    }

    /*
        학생 중복 검증(이름은 같을 수 있으나 이번 프로젝트에서는 중복을 막는다)
     */
    @ExceptionHandler(CustomErrorController.class)
    private void validDuplicateStudent(Student student) {
        studentRepository.findByName(student.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("학생 이름 중복");
                });
    }

    /*
        전체 학생 조회
     */
    public List<Student> findStudentList() {
        return studentRepository.findAll();
    }



    /*
        학생 삭제
     */
    public void delete(String name) {
        Optional<Student> result = Optional.of(studentRepository.findByName(name).orElseGet(() -> {
            throw new IllegalStateException("학생이 존재하지 않음");
        }));
        Student student = result.get();
        List<Score> scoreList = scoreService.findByStudent(student);
        for(Score s : scoreList) {
            scoreService.delete(s.getId());
        }
        studentRepository.remove(result.get());
    }

}
