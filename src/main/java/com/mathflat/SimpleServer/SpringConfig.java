package com.mathflat.SimpleServer;

import com.mathflat.SimpleServer.repository.ScoreRepository;
import com.mathflat.SimpleServer.repository.StudentRepository;
import com.mathflat.SimpleServer.repository.SubjectRepository;
import com.mathflat.SimpleServer.repository.jpa.ScoreRepositoryJPA;
import com.mathflat.SimpleServer.repository.jpa.StudentRepositoryJPA;
import com.mathflat.SimpleServer.repository.jpa.SubjectRepositoryJPA;
import com.mathflat.SimpleServer.service.ScoreService;
import com.mathflat.SimpleServer.service.StudentService;
import com.mathflat.SimpleServer.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

@Configuration
public class SpringConfig {

    private EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em) {
        this.em = em;
    }

    @Bean
    public StudentService StudentService() {
        return new StudentService(StudentRepository());
    }

    @Bean
    public StudentRepository StudentRepository() {
        return new StudentRepositoryJPA(em);
    }

    @Bean
    public SubjectService SubjectService() {
        return new SubjectService(SubjectRepository());
    }

    @Bean
    public SubjectRepository SubjectRepository() {
        return new SubjectRepositoryJPA(em);
    }

    @Bean
    public ScoreService ScoreService() {
        return new ScoreService(ScoreRepository());
    }

    @Bean
    public ScoreRepository ScoreRepository() {
        return new ScoreRepositoryJPA(em);
    }
}

