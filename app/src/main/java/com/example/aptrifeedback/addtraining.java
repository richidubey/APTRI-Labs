package com.example.aptrifeedback;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.support.annotation.Nullable;
import android.support.annotation.NonNull;

import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class addtraining extends Activity {

    DatabaseReference databaseReferencetraining;

    DatePicker startc,endc;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addtraining);

        TimePicker st,et;

        st=(TimePicker)findViewById(R.id.starttime);
        et=(TimePicker)findViewById(R.id.endtime);

        startc=(DatePicker)findViewById(R.id.startdate);
        endc=(DatePicker)findViewById(R.id.enddate);


        DatePicker datePicker = (DatePicker) findViewById(R.id.startdate);
        Calendar calendar = Calendar.getInstance();

        calendar.setTimeInMillis(System.currentTimeMillis());
        datePicker.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), new DatePicker.OnDateChangedListener() {

            @Override
            public void onDateChanged(DatePicker datePicker, int year, int month, int dayOfMonth) {
                Log.d("Date", "Year=" + year + " Month=" + (month + 1) + " day=" + dayOfMonth);

                DatePicker end = (DatePicker) findViewById(R.id.enddate);
                Calendar calendar = Calendar.getInstance();
                end.init(year, month, dayOfMonth,null );

            }
        });

        st.setHour(9);
        st.setMinute(30);

        et.setHour(18);
        et.setMinute(30);

        databaseReferencetraining= FirebaseDatabase.getInstance().getReference("training");
    }

    public void genfacfields(View view)
    {
        LinearLayout faculties=(LinearLayout)findViewById(R.id.faculties);

        faculties.removeAllViews();

        EditText numfac=(EditText)findViewById(R.id.numfac);

        String number=numfac.getText().toString();

        if(!TextUtils.isEmpty(number))
        {
            int num=Integer.parseInt(number);
           // Toast.makeText(this,num + " is entered",Toast.LENGTH_LONG).show();

            for(int i=0;i<num;i++)
            {
                EditText fac=new EditText(addtraining.this);

                LinearLayout.LayoutParams saved=new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);

                saved.setMargins(50,50,50,50);

                fac.setLayoutParams(saved);

                fac.setHint("Faculty "+(i+1) + " Name");
                fac.setId(i+1);

                faculties.addView(fac);
            }

        }
        else
        {
            Toast.makeText(this,"Write Num of Faculty First!",Toast.LENGTH_LONG).show();
        }
    }

    public void submit(View view)
    {
        EditText title=(EditText)findViewById(R.id.title);
        EditText facultynum=(EditText)findViewById(R.id.numfac);

        DatePicker dps=(DatePicker)findViewById(R.id.startdate);

        DatePicker dpe=(DatePicker)findViewById(R.id.enddate);

        int sd,sm,sy,ed,em,ey;

        sd=dps.getDayOfMonth();
        sm=dps.getMonth();
        sy=dps.getYear();

        ed=dpe.getDayOfMonth();
        em=dpe.getMonth();
        ey=dpe.getYear();

        TimePicker stime,etime;

        stime=(TimePicker)findViewById(R.id.starttime);
        etime=(TimePicker)findViewById(R.id.endtime);


        EditText location=(EditText)findViewById(R.id.location);

        String loc=location.getText().toString();
        String tit=title.getText().toString();
        String facnum=facultynum.getText().toString();

        if(TextUtils.isEmpty(facnum))
        {
            Toast.makeText(this,"Number of Faculty Empty !! ",Toast.LENGTH_LONG).show();
            return;
        }
        int num=Integer.parseInt(facnum);






        int starttimem=stime.getCurrentMinute();
        int starttimeh=stime.getCurrentHour();

        int endtimem=etime.getCurrentMinute();
        int endtimeh=etime.getCurrentHour();

        if(!TextUtils.isEmpty(loc)&&!TextUtils.isEmpty(tit)&&!TextUtils.isEmpty(facnum)) {

            int check=1;
            ArrayList<String> facs=new ArrayList<>();
            for(int i=0;i<num;i++)
            {
                EditText wow=findViewById(i+1);
                String fac=wow.getText().toString();

                if(!TextUtils.isEmpty(fac))
                {
                    facs.add(fac);

                }
                else
                {
                    Toast.makeText(this,"Enter all faculty fields please",Toast.LENGTH_LONG).show();
                    return;
                }
            }

                databaseReferencetraining= FirebaseDatabase.getInstance().getReference("training");
                String dataid = databaseReferencetraining.push().getKey();

                Training training=new Training(tit,num,facs,sd,sm,sy,ed,em,ey,starttimem,starttimeh,endtimem,endtimeh,loc);

                //Toast.makeText(this,training.getLocation(),Toast.LENGTH_LONG).show();


                databaseReferencetraining.child(dataid).setValue(training);

                Toast.makeText(this,"Training Addition Successful!!",Toast.LENGTH_LONG).show();

                finish();


        }
        else
        {

            Toast.makeText(this,"Enter all fields first",Toast.LENGTH_LONG).show();
        }



    }



}
