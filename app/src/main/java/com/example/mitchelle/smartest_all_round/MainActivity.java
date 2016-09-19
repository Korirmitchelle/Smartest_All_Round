package com.example.mitchelle.smartest_all_round;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.client.authentication.Constants;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by mitchelle on 9/19/16.
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    //defining view objects
    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button buttonSignup;
    private ProgressDialog progressDialog;
    //defining firebaseauth object
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//initializing firebase auth object
        firebaseAuth = FirebaseAuth.getInstance();

        //initializing views
        editTextEmail = (EditText) findViewById(R.id.editText);
        editTextPassword = (EditText) findViewById(R.id.editText2);

        buttonSignup = (Button) findViewById(R.id.buttonLogin);

        progressDialog = new ProgressDialog(this);

        //attaching listener to button
        buttonSignup.setOnClickListener(this);}



        private void registerUser(){

            //getting email and password from edit texts
            String email = editTextEmail.getText().toString().trim();
            String password  = editTextPassword.getText().toString().trim();

            //checking if email and passwords are empty
            if(TextUtils.isEmpty(email)){
                Toast.makeText(this,"Please enter email",Toast.LENGTH_LONG).show();
                return;
            }

            if(TextUtils.isEmpty(password)){
                Toast.makeText(this,"Please enter password", Toast.LENGTH_LONG).show();
                return;
            }

            //if the email and password are not empty
            //displaying a progress dialog

            progressDialog.setMessage("Registering Please Wait...");
            progressDialog.show();

            //creating a new user
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            //checking if success
                            if(task.isSuccessful()){
                                //display some message here
                                Toast.makeText(MainActivity.this,"Successfully registered",Toast.LENGTH_LONG).show();
                                Intent intent=new Intent(MainActivity.this,uestions.class);
                            }else{
                                //display some message here
                                Toast.makeText(MainActivity.this,"Registration Error",Toast.LENGTH_LONG).show();
                            }
                            progressDialog.dismiss();
                        }
                    });

        }


    @Override
    public void onClick(View view) {
        //calling register method on click
        registerUser();
    }
}


