package com.example.rentacar_sql;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button admAlq, admVhl, admCln;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        admAlq = findViewById(R.id.btnGesAlq);
        admVhl = findViewById(R.id.btnGesVhcl);
        admCln = findViewById(R.id.btnGesCln);

        admAlq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Adm_Alq.class);
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
}