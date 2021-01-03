package com.mathflat.SimpleServer.service;

import com.mathflat.SimpleServer.domain.Score;
import com.mathflat.SimpleServer.domain.Subject;
import com.mathflat.SimpleServer.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
public class SubjectService {
    private final SubjectRepository subjectRepository;

    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    @Autowired
    ScoreService scoreService;

    /*
        과목 추가
     */
    public Long add(Subject subject) {
        validDuplicateSubject(subject);

        subjectRepository.save(subject);
        return subject.getId();
    }

    /*
        과목 중복 검증
     */
    private void validDuplicateSubject(Subject subject) {
        subjectRepository.findByName(subject.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("과목 이름 중복");
                });
    }

    /*
        전체 과목 조회
     */
    public List<Subject> findSubjectList() {
        return subjectRepository.findAll();
    }



    /*
        과목 삭제
     */
    public void remove(String name) {
        Optional<Subject> result = Optional.of(subjectRepository.findByName(name).orElseGet(() -> {
            throw new IllegalStateException("과목이 존재하지 않음");
        }));
        Subject subject = result.get();
        Score score = new Score();
        score.setSubject(subject);
        List<Score> scoreList = scoreService.findBySubject(subject).get();
        for(Score s : scoreList) {
            scoreService.delete(s.getId());
        }
        subjectRepository.remove(result.get());
    }
}
