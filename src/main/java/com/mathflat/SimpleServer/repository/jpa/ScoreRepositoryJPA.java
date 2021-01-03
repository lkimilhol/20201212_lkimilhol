package com.mathflat.SimpleServer.repository.jpa;

import com.mathflat.SimpleServer.domain.Score;
import com.mathflat.SimpleServer.domain.Student;
import com.mathflat.SimpleServer.domain.Subject;
import com.mathflat.SimpleServer.repository.ScoreRepository;
import org.springframework.data.jpa.repository.Modifying;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ScoreRepositoryJPA implements ScoreRepository {
    private final EntityManager em;

    public ScoreRepositoryJPA(EntityManager em) {
        this.em = em;
    }

    @Override
    public Score save(Score score) {
        em.persist(score);
        return score;
    }

    @Override
    public Optional<Score> findById(long id) {
        List<Score> result = em.createQuery("select s from Score s where s.id =: id ", Score.class)
                .setParameter("id", id)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public List<Score> findByStudent(Student student) {
        List<Score> result = em.createQuery("select s from Score s where s.student =: student ", Score.class)
                .setParameter("student", student)
                .getResultList();

        if (result == null) {
            result = new ArrayList<>();
        }

        return result;
    }

    @Override
    public List<Score> findBySubject(Subject subject) {
        List<Score> result = em.createQuery("select s from Score s where s.subject =: subject ", Score.class)
                .setParameter("subject", subject)
                .getResultList();

        return result;
    }

    @Override
    public List<Score> findByFK(Score score) {
        List<Score> result = em.createQuery("select s from Score s where s.student =: student and s.subject =: subject ", Score.class)
                .setParameter("student", score.getStudent())
                .setParameter("subject", score.getSubject())
                .getResultList();

        return result;
    }

    @Override
    public List<Score> findAll() {
        List<Score> scoreList = em.createQuery("select s " +
                "from Score as s " +
                "inner join s.student s1 " +
                "inner join s.subject s2"
                , Score.class)
                .getResultList();

        return scoreList;
    }

    @Override
    public void remove(Score score) {
        em.remove(score);
    }

}
