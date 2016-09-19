package com.example.mitchelle.smartest_all_round;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.firebase.client.Firebase;
import com.firebase.client.authentication.Constants;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by mitchelle on 9/19/16.
 */
public class MainActivity extends AppCompatActivity {
    //defining firebaseauth object
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//initializing firebase auth object
        firebaseAuth = FirebaseAuth.getInstance();}



        private void registerUser(){

            //creating a new user
            firebaseAuth.createUserWithEmailAndPassword("user email here", "user password here")
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            //checking if success
                            if(task.isSuccessful()){
                                //display some message here
                            }else{
                                //display some message here
                            }

                        }
                    });

        }
    }


