package com.mathflat.SimpleServer.service;

import antlr.JavaCodeGenerator;
import com.mathflat.SimpleServer.domain.Score;
import com.mathflat.SimpleServer.domain.Student;
import com.mathflat.SimpleServer.domain.Subject;
import com.mathflat.SimpleServer.repository.ScoreRepository;
import com.mathflat.SimpleServer.repository.StudentRepository;
import com.mathflat.SimpleServer.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Transactional
public class ScoreService {
    private final ScoreRepository scoreRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    SubjectRepository subjectRepository;


    public ScoreService(ScoreRepository scoreRepository) {
        this.scoreRepository = scoreRepository;
    }

    /*
        점수 추가
     */
    public Long add(Score score) {
        scoreRepository.save(score);
        return score.getId();
    }

    public Long add(String studentName, String subjectName, int studentScore) {
        Student student = getStudentByName(studentName);
        Subject subject = getSubjectByName(subjectName);
        Date date = new Date();

        Score score = new Score();
        score.setStudent(student);
        score.setSubject(subject);
        score.setScore(studentScore);
        score.setInsertTime(date);
        score.setUpdateTime(date);

        List<Score> scoreList = scoreRepository.findByFK(score);
        if (scoreList.size() > 0) {
            throw new IllegalStateException("이미 점수가 들어가 있음");
        }

        scoreRepository.save(score);
        return score.getId();
    }

    public List<Score> findScoreList() {
        return scoreRepository.findAll();
    }

    /*
    점수 삭제
     */
    public void delete(long id) {
        Optional<Score> result = Optional.of(scoreRepository.findById(id).orElseGet(() -> {
            throw new IllegalStateException("점수가 존재하지 않음");
        }));
        scoreRepository.remove(result.get());
    }

    /*
    점수 수정
     */
    public void update(long id, int score) {
        Optional<Score> result = Optional.of(scoreRepository.findById(id).orElseGet(() -> {
            throw new IllegalStateException("점수가 존재하지 않음");
        }));
        result.get().setScore(score);
    }

    /*
    평균 값 구하기- 학생 이름
     */
    public float getAverageStudent(String name) {
        Student student = getStudentByName(name);
        List<Score> scoreList = scoreRepository.findByStudent(student);
        if (scoreList.size() == 0 ) {
            throw new IllegalStateException("학생의 점수가 없음");
        }
        int sum = 0;
        for (Score s : scoreList) {
            sum += s.getScore();
        }
        float avg = Math.round((sum / scoreList.size() * 100)/100.0);
        return avg;
    }

    /*
  평균 값 구하기- 과목 이름
   */
    public float getAverageSubject(String name) {
        Subject subject = getSubjectByName(name);
        List<Score> ScoreList = scoreRepository.findBySubject(subject);
        if (ScoreList.size() == 0 ) {
            throw new IllegalStateException("과목의 점수가 없음");
        }
        int sum = 0;
        for (Score s : ScoreList) {
            sum += s.getScore();
        }
        float avg = Math.round((sum / ScoreList.size() * 100)/100.0);
        return avg;
    }

    public List<Score> findByStudent(Student student) {
        List<Score> scoreList = scoreRepository.findByStudent(student);
        return scoreList;
    }

    public Optional<List<Score>> findBySubject(Subject subject) {
        List<Score> scoreList = scoreRepository.findBySubject(subject);
        Optional<List<Score>> result = Optional.ofNullable(scoreList);
        return result;
    }

    private Student getStudentByName(String name) {
        Optional<Student> student = Optional.of(studentRepository.findByName(name).orElseGet(() -> {
            throw new IllegalStateException("학생이 존재하지 않음");
        }));

        return student.get();
    }

    private Subject getSubjectByName(String name) {
        Optional<Subject> subject = Optional.of(subjectRepository.findByName(name).orElseGet(() -> {
            throw new IllegalStateException("과목이 존재하지 않음");
        }));

        return subject.get();
    }
}
