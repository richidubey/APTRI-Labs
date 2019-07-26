package com.example.aptrifeedback;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Pair;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class checkin extends Activity {

    public int j=0;

    ArrayList<Pair<String,String >> checked=new ArrayList<Pair<String, String>>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkin);

    final ArrayList<String> choices;



    choices=new ArrayList<String>();

        DatabaseReference databaseReferencetraining;

        final ArrayAdapter<String> adapter;
        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,choices);


        databaseReferencetraining= FirebaseDatabase.getInstance().getReference("training");

        databaseReferencetraining.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                choices.clear();

                for(DataSnapshot trainingSnapshot: dataSnapshot.getChildren())
                {
                    Training training=trainingSnapshot.getValue(Training.class);

                    choices.add(training.getTitle());
                    adapter.notifyDataSetChanged();


                    // Toast.makeText(result.this,feedback.getFeedname(), Toast.LENGTH_LONG).show();


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });





        Spinner spinner=(Spinner)findViewById(R.id.title);

        spinner.setAdapter(adapter);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        int cur=spinner.getSelectedItemPosition();

        spinner.setSelection(cur);


        DatabaseReference attendancedatabaseReference;

        attendancedatabaseReference = FirebaseDatabase.getInstance().getReference("attendance");

        attendancedatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                checked.clear();

                for(DataSnapshot attendanceSnapshot: dataSnapshot.getChildren())
                {
                    Attendance attendance=attendanceSnapshot.getValue(Attendance.class);


                    checked.add(new Pair<String, String>(attendance.getId(),attendance.getDate()));


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }







    public void getdata(View view)
    {
        Spinner spinner=findViewById(R.id.title);


        final TextView sd=(TextView)findViewById(R.id.startdated);
        final TextView sm=(TextView)findViewById(R.id.startdatem);
        final TextView sy=(TextView)findViewById(R.id.startdatey);

        final TextView ed=(TextView)findViewById(R.id.enddated);
        final TextView em=(TextView)findViewById(R.id.enddateem);
        final TextView ey=(TextView)findViewById(R.id.enddatey);

        final TextView stm=(TextView)findViewById(R.id.starttimem);
        final TextView sth=(TextView)findViewById(R.id.starttimeh);

        final TextView etm=(TextView)findViewById(R.id.endtimem);
        final TextView eth=(TextView)findViewById(R.id.endtimeh);

        final TextView loc=(TextView)findViewById(R.id.loc);

        final String title;


        title=spinner.getSelectedItem().toString();



        DatabaseReference databaseReferencetraining;

        databaseReferencetraining= FirebaseDatabase.getInstance().getReference("training");

        databaseReferencetraining.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {



                for(DataSnapshot trainingSnapshot: dataSnapshot.getChildren())
                {
                    Training training=trainingSnapshot.getValue(Training.class);

                    if(training.getTitle().equals(title))
                    {
                        sd.setText(Integer.toString(training.getStartday())+"/");
                        sm.setText(Integer.toString(training.getStartmonth())+"/");
                        sy.setText(Integer.toString(training.getStartyear()));

                        ed.setText(Integer.toString(training.getStartday())+"/");
                        em.setText(Integer.toString(training.getStartmonth())+"/");
                        ey.setText(Integer.toString(training.getStartyear()));

                        stm.setText(Integer.toString(training.getTimestartmin()));
                        sth.setText(Integer.toString(training.getTimestarthour())+ " : ");

                        etm.setText(Integer.toString(training.getTimeendmin()));
                        eth.setText(Integer.toString(training.getTimeendhour())+" : ");

                        loc.setText(training.getLocation());

                        ArrayList<String> faculties=new ArrayList<String>();

                        faculties=training.getFaculty();


                        ArrayAdapter<String> adapter=new ArrayAdapter<String>(com.example.aptrifeedback.checkin.this,android.R.layout.simple_spinner_item,faculties);

                        Spinner spinner1=(Spinner)findViewById(R.id.faculties);

                        spinner1.setAdapter(adapter);

                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


                        break;
                    }

                    // Toast.makeText(result.this,feedback.getFeedname(), Toast.LENGTH_LONG).show();


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });











    }

    public void submit(View view)
    {
        j=0;
         EditText id=(EditText)findViewById(R.id.traid);

         if(id.getText().toString().length()!=8)
         {
             Toast.makeText(this,"Please enter a valid 8 digit Employee ID ",Toast.LENGTH_LONG).show();
         }

         else {


             Spinner ins = (Spinner) findViewById(R.id.faculties);

             Spinner tit = (Spinner) findViewById(R.id.title);


             String date;

             int sec, min, hour;

             Calendar calendar = Calendar.getInstance();


             calendar.setTimeInMillis(System.currentTimeMillis());
             SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

             date = dateFormat.format(calendar.getTime());



             sec = calendar.get(Calendar.SECOND);
             min = calendar.get(Calendar.MINUTE);
             hour = calendar.get(Calendar.HOUR_OF_DAY);

             DatabaseReference attendancedatabaseReference;

             attendancedatabaseReference = FirebaseDatabase.getInstance().getReference("attendance");

             Pair<String, String> check = new Pair<String, String>(id.getText().toString(), date);


             if (!checked.contains(check)) {
                 Attendance attendance = new Attendance(tit.getSelectedItem().toString(), ins.getSelectedItem().toString(), id.getText().toString(), date, sec, min, hour, 0, 0, 0);


                 String dataid = attendancedatabaseReference.push().getKey();

                 attendancedatabaseReference.child(dataid).setValue(attendance);


                 Toast.makeText(this, "Successful Checked in " + id.getText().toString() + " For : " + date + " Thanks!", Toast.LENGTH_LONG).show();
                 id.setText("");
             } else {
                 Toast.makeText(this, id.getText().toString() + " has already checked in for the date " + date, Toast.LENGTH_LONG).show();
             }

         }
    }
}









