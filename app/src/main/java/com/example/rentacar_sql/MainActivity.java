package com.example.rentacar_sql;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.rentacar_sql.base_datos.helperDB;

public class MainActivity extends AppCompatActivity {

    Button admAlq, admVhl, admCln;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createDB();

        admAlq = findViewById(R.id.btnGesAlq);
        admVhl = findViewById(R.id.btnGesVhcl);
        admCln = findViewById(R.id.btnGesCln);

        admAlq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, gest_alq.class);
                startActivity(intent);
            }
        });

        admVhl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Adm_Vehiculos.class);
                startActivity(intent);
            }
        });

        admCln.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Adm_CLients.class);
                startActivity(intent);
            }
        });
    }

    public void createDB(){
         helperDB helperDB = new helperDB(MainActivity.this);
        SQLiteDatabase db = helperDB.getWritableDatabase();
        if(db != null){
            Toast.makeText(MainActivity.this, "Base de datos creada", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(MainActivity.this, "Error la crear la base de datos", Toast.LENGTH_SHORT).show();

        }
    }
}