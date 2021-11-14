package com.example.misubs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Home_act extends AppCompatActivity {

String ID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);



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
























































































}