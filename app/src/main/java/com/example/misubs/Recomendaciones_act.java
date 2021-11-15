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

    String url_amazon = "https://www.primevideo.com/?ref_=dvm_pds_amz_cl_dc_s_g_mkw_sfmhqtFid-dc_pcrid_231852902819&mrntrk=slid__pgrid_42306243528_pgeo_1003307_x__ptid_kwd-3151046130";
    String url_disney = "https://www.disneyplus.com/es-cl";

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

    public void Amazon(View view) {

        Uri _link = Uri.parse(url_amazon);
        Intent i = new Intent(Intent.ACTION_VIEW, _link);
        startActivity(i);
    }


    public void Disney(View view) {
        Uri _link = Uri.parse(url_disney);
        Intent i = new Intent(Intent.ACTION_VIEW, _link);
        startActivity(i);
        startActivity(i);
    }


}