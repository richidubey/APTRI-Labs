package com.example.aptrifeedback;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
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

public class result extends AppCompatActivity {


    ListView listViewFeedbacks;
    DatabaseReference databaseFeedbacks;
    int size=0;
    List<Feedback> Feedbacklist;
    ArrayList<String> towrite;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);

        towrite= new ArrayList<String>();

        Feedbacklist=new ArrayList<>();

        databaseFeedbacks= FirebaseDatabase.getInstance().getReference("feedback");
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);







        listViewFeedbacks=(ListView)findViewById(R.id.listviewfeedbacks);
    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseFeedbacks.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Feedbacklist.clear();

                for(DataSnapshot feedbackSnapshot: dataSnapshot.getChildren())
                {
                    Feedback feedback=feedbackSnapshot.getValue(Feedback.class);
                   // Toast.makeText(result.this,feedback.getFeedname(), Toast.LENGTH_LONG).show();

                    towrite.add(feedback.getFeedid());
                    towrite.add(feedback.getFeedname());
                    towrite.add(feedback.getTitle());
                    towrite.add(feedback.getFeedins());
                    String a1=feedback.getA1();
                    String a2=feedback.getA2();
                    String a3=feedback.getA3();

                    int sum=0;

                    sum+=Integer.parseInt(a1);
                    sum+=Integer.parseInt(a2);
                    sum+=Integer.parseInt(a3);


                    sum=sum/3;

                    towrite.add(Integer.toString(sum));

                    a1=feedback.getB1();
                    a2=feedback.getB2();
                    a3=feedback.getB3();


                    sum=0;

                    sum+=Integer.parseInt(a1);
                    sum+=Integer.parseInt(a2);
                    sum+=Integer.parseInt(a3);

                    sum=sum/3;

                    towrite.add(Integer.toString(sum));


                    a1=feedback.getC1();
                    a2=feedback.getC2();
                    a3=feedback.getC3();


                    sum=0;

                    sum+=Integer.parseInt(a1);
                    sum+=Integer.parseInt(a2);
                    sum+=Integer.parseInt(a3);



                    sum=sum/3;

                    towrite.add(Integer.toString(sum));

                    towrite.add(feedback.getD1());
                    towrite.add(feedback.getD2());
                    a1=feedback.getE1();

                    sum=0;
                    sum+=Integer.parseInt(a1);



                    towrite.add(Integer.toString(sum));
                    a1=feedback.getE2();

                    sum=0;
                    sum+=Integer.parseInt(a1);

                    towrite.add(Integer.toString(sum));
                    a1=feedback.getE3();

                    sum=0;
                    sum+=Integer.parseInt(a1);



                    towrite.add(Integer.toString(sum));



                    size++;
                    Feedbacklist.add(feedback);
                }


                FeedBackList adapter=new FeedBackList(result.this,Feedbacklist);

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
        private final ProgressDialog dialog = new ProgressDialog(result.this);
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

            File file = new File(exportDir, "ExcelFileFeedback.csv");
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
                String arrStr1[] ={"ID", "Name", "Title", "Faculty","Program Content","Program Faculty","Program Coordination","Skill Learnt","Time Limit","Before","After","Overall Rating"};
                csvWrite.writeNext(arrStr1);

                for(int k=0;k<size;k++) {
                    String arrStr[] = {towrite.get(k*12 + 0), towrite.get(1 + k*12), towrite.get(2 + k*12), towrite.get(3 + k*12),towrite.get(k*12 + 4), towrite.get(5 + k*12), towrite.get(6 + k*12), towrite.get(7 + k*12),towrite.get(k*12 + 8), towrite.get(9 + k*12), towrite.get(10 + k*12), towrite.get(11 + k*12)};
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

                Toast.makeText(result.this, "Export successful!", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(result.this, "Export failed!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}

