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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class checkout extends Activity {

    ArrayList<Pair<String,Pair<String,DataSnapshot>>> refer=new ArrayList<Pair<String,Pair<String,DataSnapshot>>>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkout);

        DatabaseReference attendancedatabaseReference;

        attendancedatabaseReference = FirebaseDatabase.getInstance().getReference("attendance");

        attendancedatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                refer.clear();


                for(DataSnapshot attendanceSnapshot: dataSnapshot.getChildren())
                {
                    Attendance attendance=attendanceSnapshot.getValue(Attendance.class);


                    refer.add(new Pair<String, Pair<String, DataSnapshot>>(attendance.getId(),new Pair<String, DataSnapshot>(attendance.getDate(),attendanceSnapshot)));
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


         EditText id=(EditText)findViewById(R.id.traid);

         String date;

        int sec,min,hour;

        Calendar calendar = Calendar.getInstance();


        calendar.setTimeInMillis(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

        date=dateFormat.format(calendar.getTime());

        sec=calendar.get(Calendar.SECOND);
        min=calendar.get(Calendar.MINUTE);
        hour=calendar.get(Calendar.HOUR_OF_DAY);

        int j=0;

        for(Pair<String,Pair<String,DataSnapshot>> temp : refer)
        {
            if(temp.first.equals(id.getText().toString())&&temp.second.first.equals(date))
            {
                Attendance attendance=temp.second.second.getValue(Attendance.class);

                if(attendance.getOuthour()==0&&attendance.getOutmin()==0&&attendance.getOutsec()==0)
                {
                    j=1;
                    DatabaseReference updatecheckout;

                    updatecheckout = FirebaseDatabase.getInstance().getReference("attendance");



                    calendar.setTimeInMillis(System.currentTimeMillis());

                    updatecheckout.child(temp.second.second.getKey()).child("outsec").setValue(calendar.get(Calendar.SECOND));
                    updatecheckout.child(temp.second.second.getKey()).child("outmin").setValue(calendar.get(Calendar.MINUTE));
                    updatecheckout.child(temp.second.second.getKey()).child("outhour").setValue(calendar.get(Calendar.HOUR));


                    Toast.makeText(checkout.this, "Successful Checked out " + id.getText().toString() + " For : " + date + " Thanks!", Toast.LENGTH_LONG).show();
                    id.setText("");
                }
                else
                {
                    j=1;
                    Toast.makeText(checkout.this,id.getText().toString()+" has already checked out at : "+ attendance.getOuthour() + " : " + attendance.getOutmin() +" : "  + attendance.getOutsec() ,Toast.LENGTH_LONG).show();

                }
            }
        }

        if(j==0)
        {
            Toast.makeText(checkout.this,id.getText().toString()+" has not checked in for : "+ date,Toast.LENGTH_LONG).show();
        }

    }
}









