package com.example.misubs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.misubs.DB.AdminSQLiteOpenHelper;

public class Mantenedor_act extends AppCompatActivity {

    String ID;
    private TextView UserID, name, price;
    private Spinner spnNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mantenedor);


        UserID = findViewById(R.id.idUser);
        name = findViewById(R.id.txtName);
        price = findViewById(R.id.txtPrice);

        Intent i = getIntent();
        ID = i.getStringExtra("ID");

        UserID.setText(ID);

        spnNames = findViewById(R.id.spnSuscripciones);



    }


    public void CrearSub(View view){

        //Credenciales
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "Database",null, 1);
        SQLiteDatabase db = admin.getWritableDatabase(); //Permite sobreescribir database

        String Sname = name.getText().toString();
        String Sprice = name.getText().toString();

        if(!Sname.isEmpty() && !Sprice.isEmpty()){

            ContentValues content = new ContentValues();

            content.put("name",Sname);
            content.put("value",Sprice);
            content.put("user_id",Integer.parseInt(ID));

            db.insert("subscriptions", null, content);
            db.close();

            Toast.makeText(getBaseContext(), "Guardado Correctamente", Toast.LENGTH_LONG).show();


        }



    }

//    public Cursor PoblarSpinner(){
//
//        try {
//            AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "Database",null, 1);
//            SQLiteDatabase db = admin.getReadableDatabase(); //Permite sobreescribir database
//            Cursor query = db.rawQuery("SELECT name FROM subscriptions WHERE user_id ='" + ID + "' " ,null);
//
//            if(query.moveToFirst()){
//                return query;
//            }else{
//                return null;
//            }
//
//        }catch (Exception ex){
//            return null;
//
//        }
//    }


}