package com.example.mitchelle.smartest_all_round;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.HashMap;
import java.util.Map;

public class SignUpAcivity extends AppCompatActivity {

    public void changeIntent(View v){
        Intent intent = new Intent(v.getContext(), MainActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ign_up_acivity);
        Firebase.setAndroidContext(this);
    }
    @Override
    protected void onStart() {

        super.onStart();
        final EditText username = (EditText) findViewById(R.id.emailField);
        final EditText password = (EditText) findViewById(R.id.passlField);
        final EditText usuario = (EditText) findViewById(R.id.userField);
        Button buttonLogin = (Button) findViewById(R.id.signupButton);
        final TextView loginScreen = (TextView) findViewById(R.id.loginScreen);

        loginScreen.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){


                Intent intent = new Intent(v.getContext(), MainActivity.class);
                intent.putExtra("name", username.getText().toString());
                startActivity(intent);
            }
        });

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String user = username.getText().toString();
                final String pass = password.getText().toString();
                final String usu = usuario.getText().toString();
                final Firebase ref = new Firebase("https://smart-b4d35.firebaseapp.com");
                Alias.username = username.getText().toString();
                ref.authWithPassword(user, pass,
                        new Firebase.AuthResultHandler() {



                            @Override
                            public void onAuthenticated(AuthData authData) {
                                // Authentication just completed successfully :)
                                Map<String, String> map = new HashMap<String, String>();
                                map.put("provider", authData.getProvider());
                                if(authData.getProviderData().containsKey("id")) {
                                    map.put("provider_id", authData.getProviderData().get("id").toString());
                                }
                                if(authData.getProviderData().containsKey("displayName")) {
                                    map.put("displayName", authData.getProviderData().get("displayName").toString());
                                }

                            }

                            @Override
                            public void onAuthenticationError(FirebaseError error) {
                                System.out.print("Something went wrong :(");
                                // Something went wrong :(
                            }
                        });


                ref.createUser(user, pass, new Firebase.ValueResultHandler<Map<String, Object>>() {

                    @Override
                    public void onSuccess(Map<String, Object> result) {
                        ref.child("user").child(result.get("uid").toString()).child("email").setValue(user);
                        ref.child("user").child(result.get("uid").toString()).child("alias").setValue(usu);
                        Toast.makeText(getApplicationContext(), "User registered!!!",
                                Toast.LENGTH_LONG).show();

                        Intent abc = new Intent(SignUpAcivity.this, Leaders.class);

                        Bundle b = new Bundle();
                        b.putString("user", usu);
                        abc.putExtra("shared", b);

                        Intent login = new Intent(SignUpAcivity.this, MainActivity.class);
                        startActivity(login);


                    }

                    @Override
                    public void onError(FirebaseError firebaseError) {
                        // there was an error
                        System.out.println("firebase error");
                    }

                });
            }
        });
    }

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
}

