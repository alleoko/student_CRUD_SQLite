package com.alleoko.crudsqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(Context context) {
        super(context, "Student.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table tbl_student(Student_No integer primary key autoincrement, Firstname TEXT, Lastname TEXT, Course TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if exists tbl_student");
      //  onCreate(DB);
    }

    public Boolean insertuserdata(String Fname, String Lname, String Course)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Firstname", Fname);
        contentValues.put("Lastname", Lname);
        contentValues.put("Course", Course);
        long result=DB.insert("tbl_student", null, contentValues);
        if(result==-1){
            return false;
        }else{
            return true;
        }
    }


    public Boolean updateuserdata(String Student_No, String Lname, String Fname, String Course) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Firstname", Fname);
        contentValues.put("Lastname", Lname);
        contentValues.put("Course", Course);
        Cursor cursor = DB.rawQuery("Select * from tbl_student where Student_No = ?", new String[]{Student_No});
        if (cursor.getCount() > 0) {
            long result = DB.update("tbl_student", contentValues, "Student_No=?", new String[]{Student_No});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }}


    public Boolean deletedata (String Student_No)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from tbl_student where Student_No = ?", new String[]{Student_No});
        if (cursor.getCount() > 0) {
            long result = DB.delete("tbl_student", "Student_No=?", new String[]{Student_No});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }

    }

    public Cursor getdata ()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from tbl_student", null);
        return cursor;

    }
//    public Boolean deleteTable(){
//        SQLiteDatabase DB = this.getWritableDatabase();
//     long result =   DB.execSQL("drop Table tbl_student");
//    }
}