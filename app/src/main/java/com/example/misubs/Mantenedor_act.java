package com.example.misubs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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


    ArrayList<String> lista;



    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mantenedor);

        spnNames = (Spinner) findViewById(R.id.spnSuscripciones);

        name = findViewById(R.id.txtName);
        price = findViewById(R.id.txtPrice);

        Intent i = getIntent();
        ID = i.getStringExtra("ID");

        lista = getValores();
        llenarspinner();

        spnNames = findViewById(R.id.spnSuscripciones);



    }

    public void llenarspinner(){


        Intent i = getIntent();
        ID = i.getStringExtra("ID");

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "Database", null, 1);
        SQLiteDatabase db = admin.getReadableDatabase();


        spnNames.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item,lista));

    }

    public ArrayList getValores() {


        Intent i = getIntent();
        ID = i.getStringExtra("ID");

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "Database", null, 1);
        SQLiteDatabase db = admin.getReadableDatabase();
        ArrayList<String> lista = new ArrayList<>();


        //Tomar bd
        String queryString = "SELECT name FROM subscriptions where user_id ="+ID;

        Cursor query = db.rawQuery(queryString, null);



        if (query.moveToFirst()) {

            do {

                String nombre = (query.getString(0));
                lista.add(nombre);

            } while (query.moveToNext());

        }
        return lista;


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

            clean();

            //llenar valores del combobox again
            lista = getValores();
            llenarspinner();

            db.close();

            Toast.makeText(getBaseContext(), "Guardado Correctamente", Toast.LENGTH_LONG).show();


        }



    }

    public void Recomendaciones(View view) {
        Intent i = new Intent(this, Recomendaciones_act.class);
        startActivity(i);
    }

    public void Mantenedores(View view) {
        Intent i = new Intent(this, Mantenedor_act.class);
        i.putExtra("ID", "" + ID);
        startActivity(i);
    }

    public void home(View view){

        Intent i = new Intent(this, Home_act.class );
        i.putExtra("ID", ""+ ID);


        startActivity(i);

    }



    public void eliminarSub(View view){

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "Database",null, 1);
        SQLiteDatabase db = admin.getWritableDatabase(); //Permite sobreescribir database

        String names = (String) spnNames.getSelectedItem();

            Intent i = getIntent();
            ID = i.getStringExtra("ID");

            db.execSQL("DELETE FROM subscriptions WHERE name ='" +names+"' AND user_id="+ID);

            //llenar valores del combobox again
            lista = getValores();
            llenarspinner();


            Toast.makeText(getBaseContext(), "Suscripción eliminada", Toast.LENGTH_LONG).show();
            db.close();


    }




    public void clean(){
        name.setText("");
        price.setText("");
    }


}