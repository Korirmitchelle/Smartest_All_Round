package com.example.mitchelle.smartest_all_round;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class uestions extends AppCompatActivity {

    int questionsId;
    int hits;
    String email;

    public class User {
        private int hits;
        private String email;

        public User() {}

        public User(String email, int hits) {
            this.email = email;
            this.hits =hits;
        }

        public long getHits() {
            return hits;
        }

        public String getEmail() {
            return email;
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uestions);
    }

    @Override
    protected void onStart() {
        questionsId = 0;
       hits = 0;

        Intent i = getIntent();
        Bundle b = i.getBundleExtra("shared");
        email = b.getString("email");
        super.onStart();
        loadQuestions();

    }

    public void loadQuestions(){

        final TextView perguntaquiz = (TextView) findViewById(R.id.qnquiz);
        final RadioButton response1 = (RadioButton) findViewById(R.id.response);
        final RadioButton response2 = (RadioButton) findViewById(R.id.response2);
        final RadioButton response3 = (RadioButton) findViewById(R.id.respose3);
        final RadioButton response4 = (RadioButton) findViewById(R.id.respomse4);
        final Button buttonNext = (Button) findViewById(R.id.nextQuiz);
        final RadioGroup radio = (RadioGroup) findViewById(R.id.radio);

        buttonNext.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {



                final Firebase resp1 = new Firebase("https://<YOUR-FIREBASE-URL>.firebaseio.com/questions/" + questionsId + "/responses/0/correct");
                final Firebase resp2 = new Firebase("https://<YOUR-FIREBASE-URL>.firebaseio.com/questions/" + questionsId + "/responses/1/correct");
                final Firebase resp3 = new Firebase("https://<YOUR-FIREBASE-URL>.firebaseio.com/questions/" + questionsId + "/responses/2/correct");
                final Firebase resp4 = new Firebase("https://<YOUR-FIREBASE-URL>.firebaseio.com/questions/" + questionsId + "/responses/3/correct");

                final Firebase arrayQuestions = new Firebase("https://<YOUR-FIREBASE-URL>.firebaseio.com/questions/");

                if (response1.isChecked()) {
                    resp1.addValueEventListener(new ValueEventListener() {

                        @Override
                        public void onDataChange(DataSnapshot snapshot) {
                            final String alternativa = snapshot.getValue().toString();
                            if (alternativa.equals("true")) {
                               hits++;
                                radio.clearCheck();

                            } else {
                                radio.clearCheck();
                            }


                        }

                        @Override
                        public void onCancelled(FirebaseError error) {
                        }

                    });

                }

                if (response2.isChecked()) {
                    resp2.addValueEventListener(new ValueEventListener() {

                        @Override
                        public void onDataChange(DataSnapshot snapshot) {
                            final String alternativa = snapshot.getValue().toString();
                            if (alternativa.equals("true")) {
                               hits++;
                                radio.clearCheck();

                            } else {
                                radio.clearCheck();
                            }


                        }

                        @Override
                        public void onCancelled(FirebaseError error) {
                        }

                    });

                }

                if (response3.isChecked()) {
                    resp3.addValueEventListener(new ValueEventListener() {

                        @Override
                        public void onDataChange(DataSnapshot snapshot) {
                            final String alternativa = snapshot.getValue().toString();
                            if (alternativa.equals("true")) {
                               hits++;
                                radio.clearCheck();

                            } else {
                                radio.clearCheck();
                            }


                        }

                        @Override
                        public void onCancelled(FirebaseError error) {
                        }

                    });

                }

                if (response4.isChecked()) {
                    resp4.addValueEventListener(new ValueEventListener() {

                        @Override
                        public void onDataChange(DataSnapshot snapshot) {
                            final String alternativa = snapshot.getValue().toString();
                            if (alternativa.equals("true")) {
                               hits++;
                                radio.clearCheck();

                            } else {
                                radio.clearCheck();
                            }


                        }

                        @Override
                        public void onCancelled(FirebaseError error) {
                        }

                    });

                }

                questionsId++;
                if(questionsId < 16) {
                    loadQuestions();
                }

                else{

                    final Firebase leaderboard = new Firebase("https://<YOUR-FIREBASE-URL>.firebaseio.com/leaderboard/");

                    Intent i = new Intent(uestions.this, Leaders.class);

                    Bundle b = new Bundle();
                    b.putInt("hits",hits);
                    i.putExtra("shared", b);
                    startActivity(i);

                }
            }
        });


        Firebase ref = new Firebase("https://<YOUR-FIREBASE-URL>.firebaseio.com/questions/" + questionsId + "/question");

        ref.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot snapshot) {
                perguntaquiz.setText(snapshot.getValue().toString());
            }

            @Override public void onCancelled(FirebaseError error) { }

        });

        Firebase ref1 = new Firebase("https://<YOUR-FIREBASE-URL>.firebaseio.com/questions/" + questionsId + "/responses/0/answer");

        ref1.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot snapshot) {
                response1.setText(snapshot.getValue().toString());
            }

            @Override public void onCancelled(FirebaseError error) { }

        });

        Firebase ref2 = new Firebase("https://<YOUR-FIREBASE-URL>.firebaseio.com/questions/" + questionsId + "/responses/1/answer");

        ref2.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot snapshot) {
                response2.setText(snapshot.getValue().toString());
            }

            @Override public void onCancelled(FirebaseError error) { }

        });

        Firebase ref3 = new Firebase("https://<YOUR-FIREBASE-URL>.firebaseio.com/questions/" + questionsId + "/responses/2/answer");

        ref3.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot snapshot) {
                response3.setText(snapshot.getValue().toString());
            }

            @Override public void onCancelled(FirebaseError error) { }

        });

        Firebase ref4 = new Firebase("https://<YOUR-FIREBASE-URL>.firebaseio.com/questions/" + questionsId + "/responses/3/resposta");

        ref4.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot snapshot) {
                response4.setText(snapshot.getValue().toString());
            }

            @Override public void onCancelled(FirebaseError error) { }

        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_quiz, menu);
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
