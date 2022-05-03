package com.example.rentacar_sql;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rentacar_sql.adap_list.alq_lista;
import com.example.rentacar_sql.base_datos.AlqDB;
import com.example.rentacar_sql.objects.AlqList;

import android.os.Bundle;

import java.util.ArrayList;

public class Adm_Alq extends AppCompatActivity {

    RecyclerView listView;

    AlqDB alquileresDB = new AlqDB(Adm_Alq.this);
    ArrayList<AlqList> lista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adm_alq);

        listView = findViewById(R.id.listaAlquiler);
        listView.setLayoutManager(new LinearLayoutManager(this));

        alq_lista adapter = new alq_lista(alquileresDB.readListAlquiler());
        listView.setAdapter(adapter);
    }
}