package com.example.chapter2;

import java.util.HashMap;
import java.util.Map;

public class Profile {
    private Map<String, Answer> answers = new HashMap<>();
    private int score;
    private String name;

    public Profile(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void add(Answer answer) {
        answers.put(answer.getQuestionText(), answer);
    }

    /**
     * 해당 기준이 프로파일에 있는 답변과 맞는지 결정 (정답과 맞지 않는 경우나 프로파일에 맞는 기준이 없는 경우, false. 그 외 모든 경우 true)
     * 어떤 기준이 프로파일의 정답과 일치하는 경우, 프로파일에 대한 점수가 기준의 가중치만큼 증가
     * @param criteria
     * @return
     */
    public boolean matches(Criteria criteria) {
        score = 0;

        boolean kill = false;
        boolean anyMatches = false;

        for (Criterion criterion : criteria) {
            Answer answer = answers.get(criterion.getAnswer().getQuestionText());
            boolean match = criterion.getWeight() == Weight.DontCare || answer.match(criterion.getAnswer());

            if (!match && criterion.getWeight() == Weight.MustMatch) {
                kill = true;
            }

            if (match) {
                score += criterion.getWeight().getValue();
            }

            anyMatches |= match;
        }

        if (kill) {
            return false;
        }

        return anyMatches;
    }

    public int score() {
        return score;
    }
}
