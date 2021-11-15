package com.example.misubs;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.VideoView;

public class Recomendaciones_act extends AppCompatActivity {

    private VideoView video;

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
}