package com.example.rentacar_sql;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.example.rentacar_sql.base_datos.vehiculosDB;
import com.example.rentacar_sql.objects.Vehiculos;

public class gest_vehiculos extends AppCompatActivity {

    EditText modelo,placa;
    Spinner tipoVehiculo;
    Switch estadoVehiculo;
    Button vehiculoSave,deleteVehiculo;
    public int id=0;

    private void clrScr(){
        modelo.setText("");
        placa.setText("");
        estadoVehiculo.callOnClick();
        tipoVehiculo.setSelection(1);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        setContentView(R.layout.activity_gest_vehiculos);

        modelo = findViewById(R.id.modelo);
        placa = findViewById(R.id.placa);
        tipoVehiculo = findViewById(R.id.tipoVehiculo);
        estadoVehiculo = findViewById(R.id.estadoVehiculo);
        vehiculoSave = findViewById(R.id.saveVehiculo);
        deleteVehiculo = findViewById(R.id.delVehiculo);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.spinnerTypes, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        tipoVehiculo.setAdapter(adapter);

        if(bundle!=null){
            id = bundle.getInt("ID");
            vehiculoSave.setText("Actualizar");
            vehiculosDB db = new vehiculosDB(gest_vehiculos.this);
            Vehiculos ve = db.findVehiculo("idVehiculo",String.valueOf(id));

            modelo.setText(ve.getModelo());
            placa.setText(ve.getPlaca());
            String tipos[] = {"Coche","Microbus","Furgoneta","Camión"};

            for(int i=0; i<4;i++){
                if(ve.getTipo()==tipos[i]){
                    tipoVehiculo.setSelection(i);
                }
            }
            estadoVehiculo.setText(ve.getEstado());
            deleteVehiculo.setVisibility(View.VISIBLE);

        }else{
            vehiculoSave.setText("Registrar");
            estadoVehiculo.setText("Disponible");
            estadoVehiculo.setVisibility(View.INVISIBLE);
        }

        vehiculoSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vehiculosDB db = new vehiculosDB(gest_vehiculos.this);

                if(id>0){
                    boolean updated = db.updateVehiculo(id,placa.getText().toString(), tipoVehiculo.getSelectedItem().toString(), estadoVehiculo.getText().toString(), modelo.getText().toString());

                    if(updated){
                        Toast.makeText(gest_vehiculos.this, "Vehiculo actualizado con éxito", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(gest_vehiculos.this, "Error al actualizar el vehículo", Toast.LENGTH_SHORT).show();
                    }
                }else {

                    long id = db.createVehiculo(placa.getText().toString(), tipoVehiculo.getSelectedItem().toString(), estadoVehiculo.getText().toString(), modelo.getText().toString());

                    if (id > 0) {
                        Toast.makeText(gest_vehiculos.this, "Vehiculo registrado con éxito", Toast.LENGTH_SHORT).show();
                        clrScr();
                    } else {
                        Toast.makeText(gest_vehiculos.this, "Error al registrar el vehículo", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        deleteVehiculo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vehiculosDB db = new vehiculosDB(gest_vehiculos.this);

                boolean removed = db.deleteVehiculo(id);

                if(removed){
                    Toast.makeText(gest_vehiculos.this, "Vehículo eliminado exitosamente", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(gest_vehiculos.this,Vehiculos.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(gest_vehiculos.this, "Error al eliminar el vehiculo", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}