package com.example.my_quiz.model;

public class Application_Model {


    public Application_Model(int postion, String op1, String op2, String op3, String op4, String title, String ans) {
        this.postion = postion;
        this.op1 = op1;
        this.op2 = op2;
        this.op3 = op3;
        this.op4 = op4;
        this.title = title;
        this.ans = ans;
    }

    public int getPostion() {
        return postion;
    }

    int postion;

    public String getOp1() {
        return op1;
    }

    public String getOp2() {
        return op2;
    }

    public String getOp3() {
        return op3;
    }

    public String getOp4() {
        return op4;
    }

    public String getTitle() {
        return title;
    }

    public String getAns() {
        return ans;
    }

    String op1,op2,op3,op4,title,ans;
}
