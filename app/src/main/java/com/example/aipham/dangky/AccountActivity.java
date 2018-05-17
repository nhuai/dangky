package com.example.aipham.dangky;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

public class AccountActivity extends AppCompatActivity {
    Button swhome;
    Button swaction;
    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);


        toolbar = (Toolbar) findViewById(R.id.toolbar_acc);
        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        SharedPreferences prefs = getSharedPreferences("MyPref", MODE_PRIVATE);

        String username = prefs.getString("Username", null);
        TextView tv = (TextView)findViewById(R.id.username);
        tv.setText(username);

        swaction = (Button) findViewById(R.id.access);
        swhome = (Button)findViewById(R.id.swhome);

        swaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccountActivity.this, TruyCapActivity.class);
                startActivity(intent);
            }
        });

        swhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AccountActivity.this, MainActivity.class);
                startActivity(i);
            }
        });


    }
}
//    String username  = getIntent().getStringExtra("Username");