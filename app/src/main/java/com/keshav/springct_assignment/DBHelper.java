package com.keshav.springct_assignment;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private static final String name = "database.db";
    private static final int version = 1;
    private static final SQLiteDatabase.CursorFactory factory = null;

    public DBHelper(@Nullable Context context) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE employee (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, addres TEXT,age INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public ArrayList<Employee> getAllEmployees() {
        ArrayList<Employee> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = { "id", "name", "addres", "age" };
        Cursor cursor = db.query("employee", columns, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(1);
                String addres = cursor.getString(2);
                int age = cursor.getInt(3);
                list.add(new Employee(name, addres, age));
            } while (cursor.moveToNext());
        }
        return list;
    }

    public void addEmployee(String empName, String empAge, String empAddress) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("INSERT INTO employee (name, age, addres) VALUES ('" + empName + "', '" + empAge + "', '" + empAddress + "')");
    }
}
