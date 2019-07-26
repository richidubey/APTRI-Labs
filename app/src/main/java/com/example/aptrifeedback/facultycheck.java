package com.example.aptrifeedback;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class facultycheck extends Activity {



    private FirebaseAuth mAuth;
    private Button buttonLogin;
    private EditText editTextEmail;
    private EditText editTextPassword;

    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    private static final String TAG = "facultycheck";

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admincheck);

        buttonLogin = (Button) findViewById(R.id.buttonSignIn);
        editTextEmail = (EditText) findViewById(R.id.editTextID);
        editTextPassword=(EditText) findViewById(R.id.editTextPassword);



        progressDialog = new ProgressDialog(this);

        firebaseAuth = FirebaseAuth.getInstance();


        //TextView textView1 = (TextView) findViewById(R.id.NewAcc);

        // textView1.setOnClickListener(new View.OnClickListener(){

        //   Intent myIntent = new Intent(view.getContext(), SignupActivity.class);
        // startActivityForResult(myIntent, 0);
        //   }
        //});


        // TextView prettyText= (TextView) findViewById(R.id.prettyme);

        //   Typeface font = Typeface.createFromAsset(getAssets(), "Pacifico.ttf");

        //   prettyText.setTypeface(font);


    }
    public void UserLogin(View view) {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();


        if (TextUtils.isEmpty(email)) {
            //email is empty

            Toast.makeText(this, "Please Enter Email Address", Toast.LENGTH_SHORT).show();
            //return stops from executing further
            return;
        }

        if (TextUtils.isEmpty(password)) {
            //Password is empty

            Toast.makeText(this, "Please Enter Password", Toast.LENGTH_SHORT).show();
            //return stops from executing further
            return;
        }

        //Validations are OKAY!!

        progressDialog.setMessage("Logging In. Please Wait...");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()) {
                            //Show that user is successfully registered
                            Toast.makeText(facultycheck.this, "Sign In Successful", Toast.LENGTH_SHORT).show();

                            startActivity(new Intent(facultycheck.this,infaculty.class));
                            finish();


                        } else {
                            //  Toast.makeText(HomeActivity.this, "Registeration Failed :( !!", Toast.LENGTH_SHORT).show();

                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(facultycheck.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });



    }



}


