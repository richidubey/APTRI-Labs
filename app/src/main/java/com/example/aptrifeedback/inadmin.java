package com.example.aptrifeedback;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class inadmin extends Activity {



    private FirebaseAuth firebaseAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inadmin);


    }
    public void seeattendance(View view)
    {
        startActivity(new Intent(this,seeattendance.class));

    }

    public void seeresult(View view)
    {
        startActivity(new Intent(this,result.class));

    }

    public void addnewadmin(View view)
    {
        startActivity(new Intent(this,addnewadmin.class));

    }

    public void addtraining(View view)
    {
        startActivity(new Intent(this,addtraining.class));

    }

    public void logout(View view)
    {
        firebaseAuth.getInstance().signOut();
        finish();
    }

    public void deletefeedback(View view)
    {
        startActivity(new Intent(this,deletefeedback.class));
    }
    public void deletetraining(View view)
    {
        startActivity(new Intent(this,deletetraining.class));
    }
    public void deleteatt(View view)
    {
        startActivity(new Intent(this,deleteatt.class));
    }

}
