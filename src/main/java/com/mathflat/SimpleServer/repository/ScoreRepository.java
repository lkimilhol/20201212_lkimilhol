package com.mathflat.SimpleServer.repository;

import com.mathflat.SimpleServer.domain.Score;
import com.mathflat.SimpleServer.domain.Student;
import com.mathflat.SimpleServer.domain.Subject;

import java.util.List;
import java.util.Optional;

public interface ScoreRepository {
    Score save(Score subject);
    Optional<Score> findById(long id);
    List<Score> findAll();
    List<Score> findByStudent(Student student);
    List<Score> findBySubject(Subject subject);
    List<Score> findByFK(Score score);
    void remove(Score score);
}
