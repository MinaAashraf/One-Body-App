package com.questionsanawya.dell.sanawyaapp;

/**
 * Created by dell on 4/4/2019.
 */

public class Mosapaa {
    String q,ans1,ans2,ans3,ans4;
    
    public Mosapaa (){};

    public Mosapaa(String q, String ans1, String ans2, String ans3, String ans4) {
        this.q = q;
        this.ans1 = ans1;
        this.ans2 = ans2;
        this.ans3 = ans3;
        this.ans4 = ans4;
    }

    public String getQ() {
        return q;
    }

    public void setQ(String q) {
        this.q = q;
    }

    public String getAns1() {
        return ans1;
    }

    public void setAns1(String ans1) {
        this.ans1 = ans1;
    }

    public String getAns2() {
        return ans2;
    }

    public void setAns2(String ans2) {
        this.ans2 = ans2;
    }

    public String getAns3() {
        return ans3;
    }

    public void setAns3(String ans3) {
        this.ans3 = ans3;
    }

    public String getAns4() {
        return ans4;
    }

    public void setAns4(String ans4) {
        this.ans4 = ans4;
    }
}
