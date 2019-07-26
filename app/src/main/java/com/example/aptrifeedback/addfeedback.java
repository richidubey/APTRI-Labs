package com.example.aptrifeedback;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.sql.Types.NULL;

public class addfeedback extends Activity {


    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addfeedback);

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


                             ArrayAdapter<String> adapter=new ArrayAdapter<String>(addfeedback.this,android.R.layout.simple_spinner_item,faculties);

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
        EditText id=(EditText)findViewById(R.id.traid);
        EditText name=(EditText)findViewById(R.id.traname);

        Spinner ins=(Spinner) findViewById(R.id.faculties);

        Spinner tit=(Spinner) findViewById(R.id.title);

        Spinner a1=(Spinner)findViewById(R.id.a1);
        Spinner a2=(Spinner)findViewById(R.id.a2);
        Spinner a3=(Spinner)findViewById(R.id.a3);
        Spinner b1=(Spinner)findViewById(R.id.b1);
        Spinner b2=(Spinner)findViewById(R.id.b2);
        Spinner b3=(Spinner)findViewById(R.id.b3);
        Spinner c1=(Spinner)findViewById(R.id.c1);
        Spinner c2=(Spinner)findViewById(R.id.c2);
        Spinner c3=(Spinner)findViewById(R.id.c3);
        Spinner e1=(Spinner)findViewById(R.id.e1);
        Spinner e2=(Spinner)findViewById(R.id.e2);
        Spinner e3=(Spinner)findViewById(R.id.e3);
        EditText d1=(EditText)findViewById(R.id.d1);
        EditText d2=(EditText)findViewById(R.id.d2);

        if(!(TextUtils.isEmpty(d1.getText().toString())||TextUtils.isEmpty(d2.getText().toString()))) {

            Feedback feedback = new Feedback(id.getText().toString(), name.getText().toString(), tit.getSelectedItem().toString(), ins.getSelectedItem().toString(), a1.getSelectedItem().toString(), a2.getSelectedItem().toString()
                    , a3.getSelectedItem().toString(), b1.getSelectedItem().toString(), b2.getSelectedItem().toString(), b3.getSelectedItem().toString(), c1.getSelectedItem().toString(), c2.getSelectedItem().toString(), c3.getSelectedItem().toString()
                    , d1.getText().toString(), d2.getText().toString(), e1.getSelectedItem().toString(), e2.getSelectedItem().toString(), e3.getSelectedItem().toString());


            DatabaseReference feedbackdatabaseReference;

            feedbackdatabaseReference = FirebaseDatabase.getInstance().getReference("feedback");

            String dataid = feedbackdatabaseReference.push().getKey();

            feedbackdatabaseReference.child(dataid).setValue(feedback);


            Toast.makeText(this, "Submission Successful! Thank you for your valuable inputs and time.", Toast.LENGTH_LONG).show();

            finish();
        }
        else
        {
            Toast.makeText(this, "Please enter all the fields !", Toast.LENGTH_SHORT).show();
        }
    }
}


