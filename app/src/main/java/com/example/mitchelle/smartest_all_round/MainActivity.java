package com.example.mitchelle.smartest_all_round;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.firebase.client.authentication.Constants;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by mitchelle on 9/19/16.
 */
public class MainActivity extends AppCompatActivity {

    //defining view objects
    private EditText editTextUsername;
    private EditText editTextPassword;
    private Button buttonSignup;
    private Button buttonLogin;
    private ProgressDialog progressDialog;
    //defining firebaseauth object
    private FirebaseAuth firebaseAuth;
    private Firebase mRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Firebase.setAndroidContext(this);

    }

    @Override
    protected void onStart() {
        super.onStart();

/*
        initializing firebase auth object
*/
        firebaseAuth = FirebaseAuth.getInstance();

        //initializing views

        editTextUsername = (EditText) findViewById(R.id.editText);
        editTextPassword = (EditText) findViewById(R.id.editText2);

        buttonSignup = (Button) findViewById(R.id.signup);
        buttonLogin = (Button) findViewById(R.id.buttonLogin);

        progressDialog = new ProgressDialog(this);



        buttonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), SignUpAcivity.class);
                startActivity(intent);

            }
        });



        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




                


                mRef = new Firebase("https://smart-b4d35.firebaseio.com/");

                Firebase.AuthResultHandler authResultHandler = new Firebase.AuthResultHandler() {
                    @Override
                    public void onAuthenticated(AuthData authData) {
                        // Authenticated successfully with payload authData

                        mRef = new Firebase("https://smart-b4d35.firebaseio.com/user" + authData.getUid().toString() + "/alias");

                        mRef.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot snapshot) {
                                System.out.println("The read failed: " + snapshot.getValue().toString());
                                final String alias = snapshot.getValue().toString();
                                Alias.alias = alias;
                            }
                            @Override
                            public void onCancelled(FirebaseError firebaseError) {
                                System.out.println("The read failed: " + firebaseError.getMessage());
                            }
                        });
                        ;

                        Intent i = new Intent(MainActivity.this, uestions.class);

                        Bundle b = new Bundle();
                        b.putString("email", editTextUsername.getText().toString());
                        i.putExtra("shared", b);
                        startActivity(i);



                    }

                    @Override
                    public void onAuthenticationError(FirebaseError firebaseError) {
                        // Authenticated failed with error firebaseError
                        Toast.makeText(getApplicationContext(), "Invalid User!",
                                Toast.LENGTH_LONG).show();

                    }
                };

            String user=editTextUsername.getText().toString();
            String  pass=editTextPassword.getText().toString();

                mRef.authWithPassword(user,pass,authResultHandler);



            }});}




        //attaching listener to button
        /*buttonSignup.setOnClickListener(this);
        buttonLogin.setOnClickListener(this);}
*/


        /*private void registerUser(){

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
                            }else{
                                //display some message here
                                Toast.makeText(MainActivity.this,"Registration Error",Toast.LENGTH_LONG).show();
                            }
                            progressDialog.dismiss();
                        }
                    });

        }

*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sign_up, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }








/*
    @Override
    public void onClick(View view){
        //calling register method on click
        registerUser();
        buttonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), SignUpAcivity.class);
                startActivity(intent);

            }
        });

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mRef = new Firebase("https://<YOUR-FIREBASE-URL>.firebaseio.com/");

                Firebase.AuthResultHandler authResultHandler = new Firebase.AuthResultHandler() {
                    @Override
                    public void onAuthenticated(AuthData authData) {
                        // Authenticated successfully with payload authData

                        mRef = new Firebase("https://<YOUR-FIREBASE-URL>.firebaseio.com/usuario/" + authData.getUid().toString() + "/alias");

                        mRef.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot snapshot) {
                                System.out.println("The read failed: " + snapshot.getValue().toString());
                                final String alias = snapshot.getValue().toString();
                                Alias.alias = alias;
                            }
                            @Override
                            public void onCancelled(FirebaseError firebaseError) {
                                System.out.println("The read failed: " + firebaseError.getMessage());
                            }
                        });
        ;





    }

                    @Override
                    public void onAuthenticationError(FirebaseError firebaseError) {
                        // Authenticated failed with error firebaseError
                        Toast.makeText(getApplicationContext(), "Invalid User!",
                                Toast.LENGTH_LONG).show();

                    }
                };}});}*/}


