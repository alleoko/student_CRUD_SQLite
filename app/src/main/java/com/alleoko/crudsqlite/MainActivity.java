package com.alleoko.crudsqlite;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper DB;
    EditText ET_Fname, ET_Lname, ET_ID, ET_Course;
    Button bt_Add, bt_Del, bt_Search, bt_Update, bt_View;



        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            ET_Fname = findViewById(R.id.stud_fname);
            ET_Lname = findViewById(R.id.stud_Lname);
            ET_Course = findViewById(R.id.stud_course);
            ET_ID = findViewById(R.id.stud_id);

            bt_Add = findViewById(R.id.btnAdd);
            bt_Update = findViewById(R.id.btnUpdate);
            bt_Del = findViewById(R.id.btnDel);
            bt_View = findViewById(R.id.btnView);
            bt_Search = findViewById(R.id.btnSearc);
            DB = new DatabaseHelper(this);
            bt_Add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String nameTXT = ET_Fname.getText().toString();
                    String contactTXT = ET_Lname.getText().toString();
                    String dobTXT = ET_Course.getText().toString();

                    Boolean checkinsertdata = DB.insertuserdata(nameTXT, contactTXT, dobTXT);
                    if(checkinsertdata==true)
                        Toast.makeText(MainActivity.this, "New Entry Inserted", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(MainActivity.this, "New Entry Not Inserted", Toast.LENGTH_SHORT).show();
                }        });


            bt_Update.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String IDTXT = ET_ID.getText().toString();
                    String FnameTXT = ET_Fname.getText().toString();
                    String LnameTXT = ET_Lname.getText().toString();
                    String CourseTXT = ET_Course.getText().toString();

                    Boolean checkupdatedata = DB.updateuserdata(IDTXT, FnameTXT, LnameTXT,CourseTXT );
                    if(checkupdatedata==true)
                        Toast.makeText(MainActivity.this, "Entry Updated", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(MainActivity.this, "New Entry Not Updated", Toast.LENGTH_SHORT).show();
                }        });





            bt_Del.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String IDTXT = ET_ID.getText().toString();
                    Boolean checkudeletedata = DB.deletedata(IDTXT);
                    if(checkudeletedata==true)
                        Toast.makeText(MainActivity.this, "Entry Deleted", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(MainActivity.this, "Entry Not Deleted, Input Student ID", Toast.LENGTH_SHORT).show();
                }        });

            bt_View.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Cursor res = DB.getdata();
                    if(res.getCount()==0){
                        Toast.makeText(MainActivity.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    StringBuffer buffer = new StringBuffer();
                    while(res.moveToNext()){
                        buffer.append("Student Number :"+res.getString(0)+"\n");
                        buffer.append("First Name :"+res.getString(1)+"\n");
                        buffer.append("Last Name :"+res.getString(2)+"\n");
                        buffer.append("Course :"+res.getString(3)+"\n\n");
                    }

                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setCancelable(true);
                    builder.setTitle("Student Information");
                    builder.setMessage(buffer.toString());
                    builder.show();
                }        });

            bt_Search.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }}
