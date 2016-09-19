package com.example.mitchelle.smartest_all_round;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


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


}
