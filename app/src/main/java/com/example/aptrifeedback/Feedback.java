package com.example.aptrifeedback;

public class Feedback {
    String feedid;
    String feedname;
    String feedins;

    String a1;
    String a2;
    String a3;

    String b1;
    String b2;
    String b3;

    String c1;
    String c2;
    String c3;

    String d1;
    String d2;

    String title;

    String e1;
    String e2;
    String e3;




    public Feedback()
    {

    }

    public Feedback(String feedid,String feedname,String title,String feedins,String a1,String a2,String a3,String b1,String b2,String b3,String c1,String c2,String c3,String d1,String d2,String e1,String e2,String e3)
    {
        this.feedid=feedid;
        this.feedins=feedins;
        this.feedname=feedname;

        this.title=title;

        this.a1=a1;
        this.a2=a2;
        this.a3=a3;

        this.b1=b1;
        this.b2=b2;
        this.b3=b3;

        this.c1=c1;
        this.c2=c2;
        this.c3=c3;

        this.d1=d1;
        this.d2=d2;


        this.e1=e1;
        this.e2=e2;
        this.e3=e3;


    }

    public String getFeedid() {
        return feedid;
    }

    public String getFeedname() {
        return feedname;
    }

    public String getA1() {
        return a1;
    }

    public String getA2() {
        return a2;
    }

    public String getA3() {
        return a3;
    }

    public String getB1() {
        return b1;
    }

    public String getB2() {
        return b2;
    }

    public String getB3() {
        return b3;
    }

    public String getC1() {
        return c1;
    }

    public String getC2() {
        return c2;
    }

    public String getTitle() {
        return title;
    }

    public String getC3() {
        return c3;
    }

    public String getD1() {
        return d1;
    }

    public String getD2() {
        return d2;
    }

    public String getE1() {
        return e1;
    }

    public String getE2() {
        return e2;
    }

    public String getE3() {
        return e3;
    }

    public String getFeedins(){
        return feedins;
    }
}
