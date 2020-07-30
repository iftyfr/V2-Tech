package com.example.v2tech.model;

public class SurveyHistoryModel {
    private String question;
    private String answer;

    public SurveyHistoryModel(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public SurveyHistoryModel() {
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
