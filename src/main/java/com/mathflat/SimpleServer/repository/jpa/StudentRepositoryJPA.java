package com.mathflat.SimpleServer.repository.jpa;

import com.mathflat.SimpleServer.domain.Student;
import com.mathflat.SimpleServer.repository.StudentRepository;
import org.hibernate.annotations.Entity;
import org.hibernate.annotations.Table;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class StudentRepositoryJPA implements StudentRepository {
    private final EntityManager em;

    public StudentRepositoryJPA(EntityManager em) {
        this.em = em;
    }

    @Override
    public Student save(Student student) {
        em.persist(student);
        return student;
    }

    @Override
    public Optional<Student> findByName(String name) {
        List<Student> result = em.createQuery("select s from Student s where s.name =: name ", Student.class)
                .setParameter("name", name)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public List<Student> findAll() {
        return em.createQuery("select s from Student as s", Student.class)
                .getResultList();
    }

    @Override
    public void remove(Student student) {
        em.remove(student);
    }
}
