package com.example.aipham.dangky;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class TruyCapActivity extends AppCompatActivity {
    Button signin, signup, turnback, signout;
    Toolbar toolbar;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_truycap);

        signin = (Button) findViewById(R.id.dangnhap);
        signup = (Button) findViewById(R.id.dangky);
        turnback = (Button) findViewById(R.id.quaylai);
        signout = (Button) findViewById(R.id.dangxuat);
        toolbar =(Toolbar)findViewById(R.id.toolbar_truy);
        setSupportActionBar(toolbar);


        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent isingin = new Intent(TruyCapActivity.this,LoginActivity.class);
                startActivity(isingin);
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent isingup = new Intent(TruyCapActivity.this,RegisterActivity.class);
                startActivity(isingup);
            }
        });
        turnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iturnback = new Intent(TruyCapActivity.this,AccountActivity.class);
                startActivity(iturnback);
            }
        });

        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences prefs = getSharedPreferences("MyPref", MODE_PRIVATE);
                String username = prefs.getString("Username", null);
                if (username.length() != 0)
                {
                    SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putString("Username", "");
                    editor.commit();
                    editor.apply();

                    Toast.makeText(getApplicationContext(),"Đăng xuất thành công",Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(getApplicationContext(),"Chưa đăng nhập",Toast.LENGTH_SHORT).show();
                }





            }
        });
    }
}

