package com.questionsanawya.dell.sanawyaapp;

/**
 * Created by dell on 3/31/2019.
 */

public class Scores {
    float score1,score2,score3;
    public Scores (){}

    public Scores(float score1, float score2, float score3) {
        this.score1 = score1;
        this.score2 = score2;
        this.score3 = score3;
    }

    public float getScore1() {
        return score1;
    }

    public void setScore1(float score1) {
        this.score1 = score1;
    }

    public float getScore2() {
        return score2;
    }

    public void setScore2(float score2) {
        this.score2 = score2;
    }

    public float getScore3() {
        return score3;
    }

    public void setScore3(float score3) {
        this.score3 = score3;
    }
}
