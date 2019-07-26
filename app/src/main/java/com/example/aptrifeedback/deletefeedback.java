package com.example.aptrifeedback;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class deletefeedback extends Activity {


    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.deletefeedback);

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

        final Spinner tit=(Spinner) findViewById(R.id.title);

        DatabaseReference databaseReferencefeedback;

        databaseReferencefeedback = FirebaseDatabase.getInstance().getReference("feedback");

        databaseReferencefeedback.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {



                for(DataSnapshot feedbackSnapshot: dataSnapshot.getChildren())
                {
                    Feedback feedback=feedbackSnapshot.getValue(Feedback.class);

                    if(feedback.getTitle().equals(tit.getSelectedItem().toString()))
                    {

                        DatabaseReference databaseReferencedel;

                        databaseReferencedel= FirebaseDatabase.getInstance().getReference("feedback");

                        databaseReferencedel.child(feedbackSnapshot.getKey()).removeValue();


                    }


                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        Toast.makeText(this,"Deleted " + tit.getSelectedItem().toString()+"'s Feedback successfully!",Toast.LENGTH_LONG).show();
       finish();
    }
}


