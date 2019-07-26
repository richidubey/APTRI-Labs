package com.example.aptrifeedback;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class infaculty extends Activity {

    private  FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.infaculty);
    }

    public void checkin( View view)
    {
        startActivity(new Intent(this,checkin.class));
    }

    public void checkout(View view)
    {
        startActivity(new Intent(this,checkout.class));
    }

    public void logout(View view)
    {



        firebaseAuth.getInstance().signOut();
        finish();
    }











}
