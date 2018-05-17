package com.example.aipham.dangky;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {


    private Button btcancel;
    DatabaseHelper helper =  new DatabaseHelper(this);
    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    Button btsubmitreg;
    EditText edtuserreg, edtpassreg;
    Cursor cursor;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btcancel = (Button) findViewById(R.id.cancelreg);
        btsubmitreg = (Button) findViewById(R.id.btsubmitreg);
        edtuserreg = (EditText) findViewById(R.id.edtxtuserreg);
        edtpassreg = (EditText) findViewById(R.id.edtxtpassreg);
        toolbar = (Toolbar) findViewById(R.id.toolbar_reg);
        setSupportActionBar(toolbar);


        openHelper = new DatabaseHelper(this);
        db = openHelper.getReadableDatabase();

        btcancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RegisterActivity.this, TruyCapActivity.class);
                startActivity(i);
            }
        });


        btsubmitreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userreg = edtuserreg.getText().toString().trim();
                String passreg = edtpassreg.getText().toString().trim();

                if (userreg.length() == 0)
                { Toast.makeText(getApplicationContext(),"Tên tài khoản rỗng",Toast.LENGTH_SHORT).show();}
                else{
                    if (passreg.length() == 0)
                   Toast.makeText(getApplicationContext(),"Mật khẩu rỗng",Toast.LENGTH_SHORT).show();}
                if (userreg.length() != 0 && passreg.length() != 0)
                {
                    cursor = db.rawQuery(" select * from "
                            + DatabaseHelper.TABLE_NAME
                            + " where " + DatabaseHelper.COL_2 + " =? ", new String[]{userreg});
                    if (cursor.moveToFirst()){
                        do {
                            Toast.makeText(getApplicationContext(),"Tài khoản đã tồn tại",Toast.LENGTH_SHORT).show();

                        }while (cursor.moveToNext());
                    }else
                    {
                        Contact c = new Contact();
                        c.setUsername(userreg);
                        c.setPass(passreg);
                        helper.insertContact(c);

                        Toast.makeText(getApplicationContext(),"Đăng ký thành công.",Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(RegisterActivity.this, TruyCapActivity.class);
                        startActivity(i);
                    }
                }




            }

        });


    }

}
//                db = openHelper.getWritableDatabase();
//                String userreg = edtuserreg.getText().toString();
//                String passreg = edtpassreg.getText().toString();
//                insertdata(userreg,passreg);
//                Toast.makeText(getApplicationContext(),"Đăng ký thành công!",Toast.LENGTH_SHORT).show();
//            }
//        });
//
//    }
//    protected void insertdata (String userreg, String passreg){
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(DatabaseHelper.COL_2, userreg);
//        contentValues.put(DatabaseHelper.COL_3, passreg);
//        long id = db.insert(DatabaseHelper.TABLE_NAME, null,contentValues);
//
//
//    }
//public void showDialog() {
//    dialog = new Dialog(RegisterActivity.this);
//    dialog.setContentView(R.layout.dialog_duplicate_username);
//    dialog.show();
//}