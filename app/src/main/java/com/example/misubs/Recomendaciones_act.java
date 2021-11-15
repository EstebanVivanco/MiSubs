package com.example.misubs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.VideoView;

public class Recomendaciones_act extends AppCompatActivity {

    private VideoView video;
    String ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recomendaciones);

        video = findViewById(R.id.vw);
        String ruta = "android.resource://" + getPackageName() +"/"+ R.raw.video;

        Uri uri = Uri.parse(ruta);
        video.setVideoURI(uri);

        video.start();

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

    public void Recomendaciones(View view) {
        Intent i = new Intent(this, Recomendaciones_act.class);
        startActivity(i);
    }


}