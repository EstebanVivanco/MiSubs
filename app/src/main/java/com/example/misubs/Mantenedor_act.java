package com.example.misubs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.misubs.DB.AdminSQLiteOpenHelper;

import java.util.ArrayList;

public class Mantenedor_act extends AppCompatActivity {

    String ID;
    private TextView  name, price;
    private Spinner spnNames;

    ArrayList<String> nombres;

    ArrayList<String> nameList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mantenedor);

        spnNames = (Spinner) findViewById(R.id.spnSuscripciones);

        name = findViewById(R.id.txtName);
        price = findViewById(R.id.txtPrice);

        Intent i = getIntent();
        ID = i.getStringExtra("ID");



        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1);

        spnNames.setAdapter(adapter);

        spnNames = findViewById(R.id.spnSuscripciones);



    }

    public ArrayList llenarspinner(){


        Intent i = getIntent();
        ID = i.getStringExtra("ID");

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "Database", null, 1);
        SQLiteDatabase db = admin.getReadableDatabase();
        ArrayList<String> nombres = new ArrayList<>();

        String queryString = "SELECT name FROM subscriptions where user_id ="+ID;

        Cursor query = db.rawQuery(queryString, null);

        if (query.moveToFirst()) {

            do {

                String nombre = (query.getString(0));
                nombres.add(nombre);

            } while (query.moveToNext());

        }
        return nombres;


    }


    public void CrearSub(View view){

        //Credenciales
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "Database",null, 1);
        SQLiteDatabase db = admin.getWritableDatabase(); //Permite sobreescribir database

        String Sname = name.getText().toString();
        String Sprice = price.getText().toString();

        if(!Sname.isEmpty() && !Sprice.isEmpty()){

            ContentValues content = new ContentValues();

            content.put("name",Sname);
            content.put("value",Integer.parseInt(Sprice));
            content.put("user_id",Integer.parseInt(ID));

            db.insert("subscriptions", null, content);
            db.close();

            Toast.makeText(getBaseContext(), "Guardado Correctamente", Toast.LENGTH_LONG).show();


        }



    }


    public void home(View view){

        Intent i = new Intent(this, Home_act.class );
        i.putExtra("ID", ""+ ID);


        startActivity(i);

    }


}