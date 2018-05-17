package com.example.aipham.dangky;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity{

    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    Button btsubmitlog;
    Button btcancel;
    EditText edtuserlog, edtpasslog;
    Cursor cursor;
    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btcancel = (Button) findViewById(R.id.cancellog);
        btsubmitlog = (Button) findViewById(R.id.btsubmitlogin);
        edtuserlog = (EditText) findViewById(R.id.edtxtuserlogin);
        edtpasslog = (EditText) findViewById(R.id.edtxtpasslogin);
        toolbar = (Toolbar) findViewById(R.id.toolbar_log);
        setSupportActionBar(toolbar);

        openHelper = new DatabaseHelper(this);
        db = openHelper.getReadableDatabase();


        btcancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, TruyCapActivity.class);
                startActivity(i);
            }
        });
        btsubmitlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userlog = edtuserlog.getText().toString();
                String passlog = edtpasslog.getText().toString();

                cursor = db.rawQuery(" select * from "
                        + DatabaseHelper.TABLE_NAME
                        + " where " + DatabaseHelper.COL_2 + " =? and "
                        + DatabaseHelper.COL_3 + " =? ", new String[]{userlog, passlog});


                if (userlog.length() == 0) {
                    Toast.makeText(getApplicationContext(), "Tên tài khoản rỗng", Toast.LENGTH_SHORT).show();
                } else {
                    if (passlog.length() == 0)
                        Toast.makeText(getApplicationContext(), "Mật khẩu rỗng", Toast.LENGTH_SHORT).show();
                }
                if (userlog.length() != 0 && passlog.length() != 0) {
                    if (cursor.moveToFirst()) {
                        do {
                            Toast.makeText(getApplicationContext(), "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(LoginActivity.this, AccountActivity.class);

                            startActivity(i);
                            SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
                            SharedPreferences.Editor editor = pref.edit();
                            editor.putString("Username", userlog);
                            editor.commit();
                            editor.apply();
                        } while (cursor.moveToNext());
                    } else {
                        Cursor user = db.rawQuery(" select * from "
                                + DatabaseHelper.TABLE_NAME
                                + " where " + DatabaseHelper.COL_2 + " =? ", new String[]{userlog});

                        if (user.moveToFirst()) {
                            do {
                                Toast.makeText(getApplicationContext(), "Mật khẩu chưa đúng", Toast.LENGTH_SHORT).show();

                            } while (user.moveToNext());
                        } else
                            Toast.makeText(getApplicationContext(), "Tài khoản chưa đăng ký", Toast.LENGTH_SHORT).show();

                    }
                }
            }







        });
    }
}
 //       if (cursor.getCount() > 0){

//                    Intent i = new Intent(LoginActivity.this, AccountActivity.class);
//                    i.putExtra("Username",userlog);
//                    startActivity(i);

//Toast.makeText(LoginActivity.this,"Tài khoản hoặc mật khẩu chưa đúng",Toast.LENGTH_SHORT).show();
//        Intent i = new Intent(LoginActivity.this, LoginActivity.class);
//  i.putExtra("Username",userlog);



//                    if (cursor.getCount() > 0)
//                    {
//                        Toast.makeText(getApplicationContext(),"Đăng nhập thành công",Toast.LENGTH_SHORT).show();
//
//                    }else{
//                        Toast.makeText(LoginActivity.this,"Tài khoản hoặc mật khẩu chưa đúng",Toast.LENGTH_SHORT).show();
//                }