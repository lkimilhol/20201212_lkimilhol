package com.mathflat.SimpleServer.controller;

public class ScoreForm {
    private String student;
    private String subject;
    private int score;

    public String getStudentName() {
        return student;
    }

    public void setStudent(String student) {
        this.student = student;
    }

    public String getSubjectName() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
