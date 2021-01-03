package com.mathflat.SimpleServer.repository;

import com.mathflat.SimpleServer.domain.Subject;

import java.util.List;
import java.util.Optional;

public interface SubjectRepository {
    Subject save(Subject subject);
    Optional<Subject> findByName(String name);
    List<Subject> findAll();
    void remove(Subject subject);
}
