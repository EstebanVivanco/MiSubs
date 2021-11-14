package com.example.misubs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Home_act extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }





    public void Recomendaciones(View view){
        Intent i = new Intent(this, Recomendaciones_act.class);
        startActivity(i);
    }




}