package com.example.misubs;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;
import android.widget.Toast;

import com.example.misubs.DB.AdminSQLiteOpenHelper;
import com.example.misubs.model.User;

import java.util.ArrayList;
import java.util.List;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import android.widget.TextView;


public class Home_act extends AppCompatActivity{

    Button btnListSubs;
    User oUser = new User();

String ID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        btnListSubs = findViewById(R.id.btnListSubs);


        btnListSubs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<User> users = getSubscriptions();
                Toast.makeText(Home_act.this, users.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        Intent i = getIntent();
        ID = i.getStringExtra("ID");


    }






    public void Recomendaciones(View view){
        Intent i = new Intent(this, Recomendaciones_act.class);
        startActivity(i);
    }

    public void Mantenedores(View view){
        Intent i = new Intent(this, Mantenedor_act.class);
        i.putExtra("ID", ""+ ID);
        startActivity(i);
    }



    public List<User> getSubscriptions(){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "Database",null, 1);



        List<User> returnList = new ArrayList<>();
        //Tomar bd
        String queryString = "SELECT * FROM user";
        SQLiteDatabase db_getReadable = admin.getReadableDatabase();


        Cursor cursor = db_getReadable.rawQuery(queryString, null);

        if (cursor.moveToFirst()){

            do {
                int id = cursor.getInt(0);
                String username = cursor.getString(1);
                String mail = cursor.getString(2);
                String password = cursor.getString(3);

                User newUser = new User(id, username, mail, password);
                returnList.add(newUser);

            }while (cursor.moveToNext());
        }
        else {

        }
        cursor.close();
        db_getReadable.close();

        return returnList;
    }






















}