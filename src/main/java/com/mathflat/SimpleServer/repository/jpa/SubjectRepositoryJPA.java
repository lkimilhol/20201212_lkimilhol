package com.mathflat.SimpleServer.repository.jpa;

import com.mathflat.SimpleServer.domain.Student;
import com.mathflat.SimpleServer.domain.Subject;
import com.mathflat.SimpleServer.repository.StudentRepository;
import com.mathflat.SimpleServer.repository.SubjectRepository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class SubjectRepositoryJPA implements SubjectRepository {
    private final EntityManager em;

    public SubjectRepositoryJPA(EntityManager em) {
        this.em = em;
    }

    @Override
    public Subject save(Subject subject) {
        em.persist(subject);
        return subject;
    }

    @Override
    public Optional<Subject> findByName(String name) {
        List<Subject> result = em.createQuery("select s from Subject s where s.name =: name ", Subject.class)
                .setParameter("name", name)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public List<Subject> findAll() {
        return em.createQuery("select s from Subject as s", Subject.class)
                .getResultList();
    }

    @Override
    public void remove(Subject subject) {
        em.remove(subject);
    }
}
