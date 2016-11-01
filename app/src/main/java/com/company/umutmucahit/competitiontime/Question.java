package com.company.umutmucahit.competitiontime;

/**
 * Question class - holds refernce to a question object
 * Created by UmutMucahit on 5/4/2015.
 */
public class Question
{
    private String question, answer1, answer2, answer3, answer4,correct;

    // init the question text, 4 answers and the correct answer
    public Question(String q,String a1, String a2, String a3, String a4, String ac)
    {
        this.question = q;
        this.answer1 = a1;
        this.answer2 = a2;
        this.answer3 = a3;
        this.answer4 = a4;
        correct = ac;
    }

    // getter methods for all properties.
    public String getQuestion() {
        return question;
    }

    public String getAnswer1() {
        return answer1;
    }

    public String getAnswer2() {
        return answer2;
    }

    public String getAnswer3() {
        return answer3;
    }

    public String getAnswer4() {
        return answer4;
    }

    public String getCorrect() {
        return correct;
    }
}
