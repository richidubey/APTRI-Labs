package com.example.aptrifeedback;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class Training {

    String title;
    ArrayList<String> faculty=new ArrayList<>();

   int startday;
   int startmonth;
   int startyear;

   int endday;
   int endmonth;
   int endyear;

   int timestartmin;
    int timestarthour;
    int timeendmin;
    int timeendhour;
    int numfac;
    String location;

    public Training()
    {

    }

    public Training(String title,int numfac,ArrayList<String> faculty,
            int startday,int startmonth,int startyear,int endday,int endmonth,int endyear,int timestartmin,int timestarthour,int timeendmin,int timeendhour,String location)
    {

        this.title=title;
        this.numfac=numfac;
        this.faculty=faculty;
        this.startday=startday;
        this.startmonth=startmonth;
        this.startyear=startyear;
        this.endday=endday;
        this.endmonth=endmonth;
        this.endyear=endyear;
        this.timestarthour=timestarthour;
        this.timestartmin=timestartmin;
        this.timeendmin = timeendmin;
        this.timeendhour=timeendhour;
        this.location=location;

    }

    public String getTitle() {
        return title;
    }

    public int getNumfac() {
        return numfac;
    }

    public ArrayList<String> getFaculty() {
        return faculty;
    }

    public int getStartday() {
        return startday;
    }

    public int getStartmonth() {
        return startmonth;
    }

    public int getStartyear() {
        return startyear;
    }

    public int getEndday() {
        return endday;
    }

    public int getEndmonth() {
        return endmonth;
    }

    public int getEndyear() {
        return endyear;
    }

    public int getTimestartmin() {
        return timestartmin;
    }

    public int getTimestarthour() {
        return timestarthour;
    }

    public int getTimeendmin() {
        return timeendmin;
    }

    public int getTimeendhour() {
        return timeendhour;
    }

    public String getLocation() {
        return location;
    }
}
