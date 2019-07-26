package com.example.aptrifeedback;

public class Attendance {

    String title;
    String fac;
    String id;


    int insec, inmin, inhour;

    String date;

    int outsec, outmin, outhour;

    public Attendance()
    {

    }
    public Attendance(String title, String fac, String id, String date, int insec, int inmin, int inhour, int outsec, int outmin, int outhour) {
        this.title = title;
        this.fac = fac;
        this.id = id;
        this.date = date;
        this.insec = insec;
        this.inmin = inmin;
        this.inhour = inhour;
        this.outsec = outsec;
        this.outmin = outmin;
        this.outhour = outhour;
    }

    public String getTitle() {
        return title;
    }

    public String getFac() {
        return fac;
    }

    public String getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public int getInsec() {
        return insec;
    }

    public int getInmin() {
        return inmin;
    }

    public int getInhour() {
        return inhour;
    }

    public int getOutsec() {
        return outsec;
    }

    public int getOutmin() {
        return outmin;
    }

    public int getOuthour() {
        return outhour;
    }
}

