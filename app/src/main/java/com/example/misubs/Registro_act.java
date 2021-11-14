package com.example.misubs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.misubs.DB.AdminSQLiteOpenHelper;

public class Registro_act extends AppCompatActivity {

    private TextView username, mail,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        username = findViewById(R.id.txtUsername);
        mail = findViewById(R.id.txtMail);
        password = findViewById(R.id.txtPassword);

    }

    public void addUser(View view){

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "Database",null, 1);
        SQLiteDatabase db = admin.getWritableDatabase(); //Permite sobreescribir database

        String Tusername = username.getText().toString();
        String Tmail = mail.getText().toString();
        String Tpassword = password.getText().toString();

        if (!Tusername.isEmpty() && !Tmail.isEmpty() && !Tpassword.isEmpty()){

            ContentValues content = new ContentValues();

            content.put("username", Tusername);
            content.put("mail", Tmail);
            content.put("password", Tpassword);

            db.insert("user",null, content);
            db.close();

            Toast.makeText(getBaseContext(), "Usuario Registrado", Toast.LENGTH_LONG).show();


        }else{
            Toast.makeText(getBaseContext(), "Ingrese todos los campos", Toast.LENGTH_LONG).show();
        }


    }


}