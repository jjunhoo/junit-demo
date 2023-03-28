package com.example.chapter2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProfileTest {

    private Profile profile;
    private BooleanQuestion question;
    private Criteria criteria;

    // JUnit4 - @Before -> JUnit5 - @BeforeEach
    @BeforeEach
    public void create() {
        profile = new Profile("Bull Hockey, Inc."); // 프로파일 생성
        question = new BooleanQuestion(1, "Got bonuses ?"); // 질문 생성
        criteria = new Criteria(); // Criteria 객체 생성
    }

    @Test
    void matchAnswersFalseWhenMustMatchCriteriaNotMet() {
        profile.add(new Answer(question, Bool.FALSE));
        criteria.add(new Criterion(new Answer(question, Bool.TRUE), Weight.MustMatch));

        boolean matches = profile.matches(criteria);

        assertFalse(matches);
    }

    @Test
    void matchAnswersTureForAnyDontCareCriteria() {
        profile.add(new Answer(question, Bool.FALSE));
        criteria.add(new Criterion(new Answer(question, Bool.TRUE), Weight.DontCare));

        boolean matches = profile.matches(criteria);

        assertTrue(matches);
    }

}