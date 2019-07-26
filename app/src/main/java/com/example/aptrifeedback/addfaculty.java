package com.example.aptrifeedback;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class addfaculty extends Activity {

    private FirebaseAuth mAuth;
    private Button buttonRegister;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textViewLogin;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    private static final String TAG = "addfaculty";

    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.addnewadmin);

        buttonRegister = (Button) findViewById(R.id.buttonRegister);
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
    public void registerUser (View view) {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();


        if (TextUtils.isEmpty(email)) {
            //email is empty

            Toast.makeText(this, "Please Enter Emp ID", Toast.LENGTH_SHORT).show();
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

        progressDialog.setMessage("Being Registered. Please Wait");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()) {
                            //Show that user is successfully registered
                            Toast.makeText(addfaculty.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            //  Toast.makeText(HomeActivity.this, "Registeration Failed :( !!", Toast.LENGTH_SHORT).show();

                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(addfaculty.this, "Registration failed. No Internet Maybe? Or you didnt write proper email? Or your password is too small.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        progressDialog.dismiss();

    }

}

