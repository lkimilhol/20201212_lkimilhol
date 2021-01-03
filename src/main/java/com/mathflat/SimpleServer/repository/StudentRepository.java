package com.mathflat.SimpleServer.repository;

import com.mathflat.SimpleServer.domain.Student;

import java.util.List;
import java.util.Optional;

public interface StudentRepository {
    Student save(Student student);
    Optional<Student> findByName(String name);
    List<Student> findAll();
    void remove(Student student);
}
