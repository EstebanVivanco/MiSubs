package com.example.misubs;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
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


public class Home_act extends AppCompatActivity{

    Button btnListSubs;
    User oUser = new User();
    ListView lvSubscription;

String ID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        btnListSubs = findViewById(R.id.btnListSubs);


        btnListSubs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(Home_act.this, "Database",null, 1);
                List<Subscription> oSubscriptions = getSubscriptions();
                ArrayAdapter customerArrayAdapter = new ArrayAdapter<Subscription>(Home_act.this, android.R.layout.simple_list_item_1, oSubscriptions);

                lvSubscription.setAdapter(customerArrayAdapter);

                //Toast.makeText(Home_act.this, oSubscriptions.toString(), Toast.LENGTH_SHORT).show();
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



    public List<Subscription> getSubscriptions(){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "Database",null, 1);



        List<Subscription> returnList = new ArrayList<>();
        //Tomar bd
        String queryString = "SELECT id, name, value, user_id FROM subscriptions WHERE user_id = "+ ID;
        SQLiteDatabase db_getReadable = admin.getReadableDatabase();


        Cursor cursor = db_getReadable.rawQuery(queryString, null);

        if (cursor.moveToFirst()){

            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                int value = cursor.getInt(2);
                int user_id = cursor.getInt(3);

                Subscription oSubscription = new Subscription(id, name, value, user_id);
                returnList.add(oSubscription);

            }while (cursor.moveToNext());
        }
        else {

        }
        cursor.close();
        db_getReadable.close();

        return returnList;
    }


}