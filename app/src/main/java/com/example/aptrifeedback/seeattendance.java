package com.example.aptrifeedback;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Pair;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class seeattendance extends AppCompatActivity {


    int ssd,ssm,ssy;
    int sed,sem,sey;
    ListView listViewFeedbacks;
    DatabaseReference attendancedatabaseReference;
    int size=0;
    List<Attendance> Feedbacklist;

    ArrayList<Pair<String ,String>> start=new ArrayList<Pair<String , String>>();
    ArrayList<Pair<String ,String>> end=new ArrayList<Pair<String , String>>();
    ArrayList<Pair<String ,String>> trastart=new ArrayList<Pair<String , String>>();
    ArrayList<Pair<String ,String>> traend=new ArrayList<Pair<String , String>>();
    ArrayList<Pair<String ,Float>> savetime=new ArrayList<Pair<String , Float>>();

    ArrayList<Training> tlist=new ArrayList<Training>();

    ArrayList<Pair<String ,String>> refer=new ArrayList<Pair<String , String>>();

    ArrayList<String> towrite;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seeattendance);

        towrite= new ArrayList<String>();

        Feedbacklist=new ArrayList<>();

        attendancedatabaseReference= FirebaseDatabase.getInstance().getReference("attendance");
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);


        listViewFeedbacks=(ListView)findViewById(R.id.listviewattendance);

        DatabaseReference databaseReferencetraining;

        databaseReferencetraining = FirebaseDatabase.getInstance().getReference("training");

        databaseReferencetraining.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                start.clear();
                tlist.clear();
                trastart.clear();
                traend.clear();
                end.clear();
                for (DataSnapshot trainingSnapshot : dataSnapshot.getChildren()) {
                    Training training = trainingSnapshot.getValue(Training.class);

                    tlist.add(training);

                    start.add(new Pair<String, String>(training.getTitle(),training.getStartday()+" : "+training.getStartmonth() + " : "+training.getStartyear()));
                    trastart.add(new Pair<String, String>(training.getTitle(),training.getStartday()+" : "+training.getStartmonth() + " : "+training.getStartyear()));

                    end.add(new Pair<String, String>(training.getTitle(),training.getTimestarthour()+" : "+training.getTimestartmin()));

                    traend.add(new Pair<String, String>(training.getTitle(),training.getTimeendhour()+" : "+training.getTimeendmin()));

                    savetime.add(new Pair<String, Float>(training.getTitle(),((((float)(training.getEndday()-training.getStartday())+(training.getEndmonth()-training.getStartmonth())*30+(training.getEndyear()-training.getStartyear())*365)+1)*((float)((training.getTimeendhour()-training.getTimestarthour())+(training.getTimestartmin()-training.getTimeendmin())/60)/8))));



                    /*
                    if (attendance.getTitle().equals(training.getTitle())) {
                        int sd, sm, sy;
                        int ed, em, ey;

                        sd = training.getStartday();
                        sm = training.getStartmonth();
                        sy = training.getStartyear();


                        ed = training.getEndday();
                        em = training.getEndmonth();
                        ey = training.getEndyear();

                        ssd=sd;
                        ssm=sm;
                        ssy=sy;

                        sed=ed;
                        sem=em;
                        sey=ey;

                        break;
                    }*/




                    // Toast.makeText(result.this,feedback.getFeedname(), Toast.LENGTH_LONG).show();


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        DatabaseReference databaseReferencetraining;

        databaseReferencetraining = FirebaseDatabase.getInstance().getReference("training");

        databaseReferencetraining.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                start.clear();
                tlist.clear();
                end.clear();
                for (DataSnapshot trainingSnapshot : dataSnapshot.getChildren()) {
                    Training training = trainingSnapshot.getValue(Training.class);

                    tlist.add(training);

                    start.add(new Pair<String, String>(training.getTitle(),training.getStartday()+" : "+training.getStartmonth() + " : "+training.getStartyear()));

                    end.add(new Pair<String, String>(training.getTitle(),training.getEndday()+" : "+training.getEndmonth() + " : "+training.getEndyear()));

                    /*
                    if (attendance.getTitle().equals(training.getTitle())) {
                        int sd, sm, sy;
                        int ed, em, ey;

                        sd = training.getStartday();
                        sm = training.getStartmonth();
                        sy = training.getStartyear();


                        ed = training.getEndday();
                        em = training.getEndmonth();
                        ey = training.getEndyear();

                        ssd=sd;
                        ssm=sm;
                        ssy=sy;

                        sed=ed;
                        sem=em;
                        sey=ey;

                        break;
                    }*/




                    // Toast.makeText(result.this,feedback.getFeedname(), Toast.LENGTH_LONG).show();


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        attendancedatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Feedbacklist.clear();


                for(DataSnapshot attendanceSnapshot: dataSnapshot.getChildren())
                {
                    Attendance attendance=attendanceSnapshot.getValue(Attendance.class);
                   // Toast.makeText(result.this,feedback.getFeedname(), Toast.LENGTH_LONG).show();

                    refer.clear();
                    Pair <String,String> check = new Pair<String , String>(attendance.getId(),attendance.getTitle());
                    if(refer.contains(check))
                    {
                        continue;
                    }
                    else {



                        for (Pair <String,String> temp : start)
                        {

                            if (temp.first.equals(attendance.getTitle()))
                            {
                                towrite.add(temp.second);

                                break;

                            };
                        }


                        for (Pair <String,String> temp : end)
                        {

                            if (temp.first.equals(attendance.getTitle()))
                            {
                                towrite.add(temp.second);

                                break;

                            };
                        }

                        for (Pair <String,String> temp : trastart)
                        {

                            if (temp.first.equals(attendance.getTitle()))
                            {
                                towrite.add(temp.second);

                                break;

                            };
                        }


                        for (Pair <String,String> temp : traend)
                        {

                            if (temp.first.equals(attendance.getTitle()))
                            {
                                towrite.add(temp.second);

                                break;

                            };
                        }



                        Training savet=new Training();
                        for(Training temp: tlist)
                        {
                            if(temp.getTitle()==attendance.getTitle())
                                savet=temp;
                        }





                        towrite.add(Integer.toString(attendance.getInhour())+" : "+ attendance.getInmin() + " : "+attendance.getInsec());
                        towrite.add(attendance.getOuthour()+" : "+ attendance.getOutmin() + " : "+attendance.getOutsec());


                        for (Pair <String,Float> temp : savetime)
                        {

                            if (temp.first.equals(attendance.getTitle()))
                            {
                                towrite.add(Float.toString(temp.second));

                                break;

                            };
                        }


                        towrite.add(attendance.getId());

                        towrite.add(attendance.getTitle());



                        towrite.add(attendance.getFac());

                        size++;
                        Feedbacklist.add(attendance);

                        refer.add(check);
                    }
                }


                AttendanceList adapter=new AttendanceList(seeattendance.this,Feedbacklist);

                ExportDatabaseCSVTask task=new ExportDatabaseCSVTask();
                task.execute();

                listViewFeedbacks.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    private class ExportDatabaseCSVTask extends AsyncTask<String ,String, String> {
        private final ProgressDialog dialog = new ProgressDialog(seeattendance.this);
        @Override
        protected void onPreExecute() {
            this.dialog.setMessage("Exporting database...");
            this.dialog.show();
        }

        protected String doInBackground(final String... args){
            File exportDir = new File(Environment.getExternalStorageDirectory(), "");
            if (!exportDir.exists()) {
                exportDir.mkdirs();
            }

            File file = new File(exportDir, "ExcelFileAttendance.csv");
            try {

                file.createNewFile();
                CSVWriter csvWrite = new CSVWriter(new FileWriter(file));

                //data
                ArrayList<String> listdata= new ArrayList<String>();
                listdata.add("Aniket");
                listdata.add("Shinde");
                listdata.add("pune");
                listdata.add("anything@anything");
                //Headers
                String arrStr1[] ={"Date (From) ", "Date (To) ", "Training (From)", "Training (To)"," Time ( In )", "Time ( Out )","Man Days","Employee ID","Training Title","Faculty"};
                csvWrite.writeNext(arrStr1);

                for(int k=0;k<size;k++) {
                    String arrStr[] = {towrite.get(k*10 + 0), towrite.get(1 + k*10), towrite.get(2 + k*10), towrite.get(3 + k*10),towrite.get(k*10 + 4), towrite.get(5 + k*10), towrite.get(6 + k*10),towrite.get(7+k*10),towrite.get(8+k*10),towrite.get(9+k*10)};
                    csvWrite.writeNext(arrStr);
                }

                csvWrite.close();
                return "";
            }
            catch (IOException e){
                Log.e("MainActivity", e.getMessage(), e);
                return "";
            }
        }

        @SuppressLint("NewApi")
        @Override
        protected void onPostExecute(final String success) {

            if (this.dialog.isShowing()){
                this.dialog.dismiss();
            }
            if (success.isEmpty()){

                Toast.makeText(seeattendance.this, "Export successful!", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(seeattendance.this, "Export failed!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}

