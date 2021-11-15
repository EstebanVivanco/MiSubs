package com.example.misubs;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.misubs.DB.AdminSQLiteOpenHelper;
import com.example.misubs.model.Subscription;
import com.example.misubs.model.User;

import java.util.ArrayList;
import java.util.List;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import android.widget.TextView;


public class Home_act extends AppCompatActivity {

    TextView txtTotal;
    Button btnListSubs;
    User oUser = new User();
    ListView lv;
    ArrayList<String> lista;
    ArrayAdapter adapter;

    String ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        lv = (ListView) findViewById(R.id.listi);

        lista = getSubscriptions();

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, lista);
        lv.setAdapter(adapter);

        txtTotal = (TextView) findViewById(R.id.txtTotal);

        txtTotal.setText(getTotal());






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


    public ArrayList getSubscriptions() {

        Intent i = getIntent();
        ID = i.getStringExtra("ID");

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "Database", null, 1);
        SQLiteDatabase db = admin.getReadableDatabase();
        ArrayList<String> lista = new ArrayList<>();


        //Tomar bd
        String queryString = "SELECT name , value FROM subscriptions where user_id ="+ID;

        Cursor query = db.rawQuery(queryString, null);



        if (query.moveToFirst()) {

            do {

                String nombre = (query.getString(0));
                String precio = (query.getString(1));
                String msg = nombre + " $"+precio;
                lista.add(msg);

            } while (query.moveToNext());

        }
            return lista;


    }

    public String getTotal(){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "Database", null, 1);
        SQLiteDatabase db = admin.getReadableDatabase();
        ArrayList<String> lista = new ArrayList<>();
        String total = null;


        //Tomar bd
        String queryString = "SELECT SUM(value) FROM subscriptions where user_id ="+ID;

        Cursor query = db.rawQuery(queryString, null);



        if (query.moveToFirst()) {

            do {
                String precio = (query.getString(0));
                total ="TOTAL: $"+precio;



            } while (query.moveToNext());

        }
        if (total.equalsIgnoreCase("TOTAL: $null")){
            total = "TOTAL: $0";
            return total;

        }else {
            return total;
        }

    }

}