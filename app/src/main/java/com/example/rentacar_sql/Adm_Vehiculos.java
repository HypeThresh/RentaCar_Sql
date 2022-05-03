package com.example.rentacar_sql;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.rentacar_sql.adap_list.vehiculos_lista;
import com.example.rentacar_sql.base_datos.vehiculosDB;
import com.example.rentacar_sql.objects.Vehiculos;

import java.util.ArrayList;

public class Adm_Vehiculos extends AppCompatActivity {
    public Button newVehiculo,findVehiculo;
    Spinner claveBusqueda;
    EditText valorBusqueda;
    public RecyclerView lista;
    public vehiculosDB db = new vehiculosDB(Adm_Vehiculos.this);
    ArrayList<Vehiculos> listaVehiculos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adm_vehiculos);

        findVehiculo = findViewById(R.id.btnBuscarVehiculo);
        valorBusqueda = findViewById(R.id.txtBuscarVehiculo);

        lista = findViewById(R.id.listaVehiculos);
        lista.setLayoutManager(new LinearLayoutManager(this));

        newVehiculo = findViewById(R.id.btnAddVehiculo);

        listaVehiculos = new ArrayList<>();

        vehiculos_lista adapter = new vehiculos_lista(db.readVehiculos());
        lista.setAdapter(adapter);

        ArrayAdapter<CharSequence> adapterSpinner = ArrayAdapter.createFromResource(this,R.array.spinnerClaveVehiculo, android.R.layout.simple_spinner_dropdown_item);
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_item);
        claveBusqueda.setAdapter(adapterSpinner);

        newVehiculo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Adm_Vehiculos.this,gest_vehiculos.class);
                startActivity(intent);
            }
        });
        findVehiculo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Vehiculos ve = db.findVehiculo(claveBusqueda.getSelectedItem().toString(), valorBusqueda.getText().toString());

                if (ve != null) {
                    Intent intent = new Intent(Adm_Vehiculos.this, gest_vehiculos.class);
                    intent.putExtra("ID", ve.getId());
                    startActivity(intent);
                } else {
                    Toast.makeText(Adm_Vehiculos.this, "Vehiculo no encontrado", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}