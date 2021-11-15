package com.example.misubs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.misubs.DB.AdminSQLiteOpenHelper;

public class MainActivity extends AppCompatActivity {

    private TextView txtmail, txtpass;
    private ProgressBar pbLoadLogin;
    private Button btnLogin;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtmail = findViewById(R.id.txtMailLogin);
        txtpass = findViewById(R.id.txtPasswordLogin);
        btnLogin = findViewById(R.id.btnLogin);
        pbLoadLogin = findViewById(R.id.pbLoadLogin);
        pbLoadLogin.setVisibility(View.INVISIBLE);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new LoadLoginPB().execute();
            }
        });

    }

    class LoadLoginPB extends AsyncTask<String, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pbLoadLogin.setVisibility(View.VISIBLE);
        }

        @Override
        protected Void doInBackground(String... strings) {

            try {
                for (int i = 0; i <= 10; i++) {
                    Thread.sleep(200);
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return null;
        }
        @Override
        protected void onPostExecute(Void s) {
            super.onPostExecute(s);
            pbLoadLogin.setVisibility(View.INVISIBLE);

            //Credenciales
            AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(MainActivity.this, "Database",null, 1);
            SQLiteDatabase db = admin.getWritableDatabase(); //Permite sobreescribir database

            String mail = txtmail.getText().toString();
            String pass = txtpass.getText().toString();


            if (!mail.isEmpty() && !pass.isEmpty()){

                //Hago la consulta
                Cursor query = db.rawQuery("SELECT id FROM user WHERE mail ='" + mail + "' and password= '"+pass+"'" ,null);

                if(query.moveToFirst()){

                    String ID = query.getString(0);

                    Intent i = new Intent(MainActivity.this, Home_act.class );
                    i.putExtra("ID", ""+ ID);


                    startActivity(i);

                }else{

                    Toast.makeText(getBaseContext(), "Error al iniciar sesión", Toast.LENGTH_LONG).show();

                }

            }else{

                Toast.makeText(getBaseContext(), "Ingrese los campos faltantes", Toast.LENGTH_LONG).show();


            }
        }
    }



    public void login(View view){

        Intent i = new Intent(this, Home_act.class );
        startActivity(i);

    }

    public void Registro(View view){

        Intent i = new Intent(this, Registro_act.class );
        startActivity(i);

    }

    public void info(View view){


        Intent i = new Intent(this, informacion_act.class );
        startActivity(i);


    }




}