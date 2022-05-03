package com.example.rentacar_sql;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rentacar_sql.base_datos.AlqDB;
import com.example.rentacar_sql.base_datos.clientesDB;
import com.example.rentacar_sql.base_datos.vehiculosDB;
import com.example.rentacar_sql.objects.Alquiler;
import com.example.rentacar_sql.objects.Clientes;
import com.example.rentacar_sql.objects.Vehiculos;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;

public class gest_alq extends AppCompatActivity {

    Button registro,delete;
    TextView precioAlquiler,fechaInicio, fechaFin,tiempoAlquiler;
    Spinner idVehiculo,idCliente;
    int id=0,idV=0,idC=0;

    ArrayList<String> listVehiculos,listClientes;
    vehiculosDB vehiculosDB = new vehiculosDB(this);
    clientesDB clientesDB = new clientesDB(this);
    AlqDB alqDB = new AlqDB (this);

    DatePickerDialog.OnDateSetListener setDateListener,setDateListener2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        setContentView(R.layout.activity_gest_alq);

        registro = findViewById(R.id.btnRegistrarAql);
        fechaInicio = findViewById(R.id.fechaInicio);
        fechaFin = findViewById(R.id.fechaFin);
        tiempoAlquiler = findViewById(R.id.tiempoAlquiler);
        precioAlquiler = findViewById(R.id.precioAlquiler);
        idVehiculo = findViewById(R.id.idVehiculoSp);
        idCliente = findViewById(R.id.idClienteSp);
        delete = findViewById(R.id.btnDelAlq);

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        listVehiculos = vehiculosDB.spinnerVehiculos();
        listClientes = clientesDB.spinnerClientes();

        if(listVehiculos.size()<1){
            Toast.makeText(this, "No hay vehiculos disponibles", Toast.LENGTH_SHORT).show();
        }

        if(extras!=null){
            id = extras.getInt("ID");
            registro.setText("Actualizar");
            delete.setVisibility(View.VISIBLE);

            Alquiler alquiler = alqDB.findAlquiler("idAlquiler",String.valueOf(id));
            Vehiculos ve = vehiculosDB.findVehiculo("idVehiculo",String.valueOf(alquiler.getIdVehiculo()));
            Clientes cli = clientesDB.findClientes("idCliente",String.valueOf(alquiler.getIdCliente()));

            String clienteN = cli.getId()+"-"+cli.getNombre();
            String vehiculoN = ve.getId()+"-"+ve.getModelo();

            idV = ve.getId();
            idC = cli.getId();

            listClientes.add(clienteN);
            listVehiculos.add(vehiculoN);

            fechaInicio.setText(alquiler.getFechaIni());
            fechaFin.setText(alquiler.getFechaFin());
            tiempoAlquiler.setText(alquiler.getTiempoAlquiler());
            precioAlquiler.setText(alquiler.getPrecioAlquiler());

        }

        ArrayAdapter<String> adapterV = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listVehiculos);
        idVehiculo.setAdapter(adapterV);

        ArrayAdapter<String> adapterC = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, listClientes);
        idCliente.setAdapter(adapterC);

        if(extras!=null){
            idVehiculo.setSelection(listVehiculos.size()-1);
            idCliente.setSelection(listClientes.size()-1);
        }

        fechaInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(gest_alq.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,setDateListener, year, month, day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();


            }
        });

        fechaFin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(gest_alq.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,setDateListener2, year, month, day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        idVehiculo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    getPrecioAlquiler();
                }catch (Exception e){
                    e.toString();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
            });

        setDateListener = new DatePickerDialog.OnDateSetListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                i1 = i1 + 1;
                String date = "";

                if(i2<10){
                    date+="0"+i2;
                }else{
                    date+=i2;
                }

                if(i1<10){
                    date+="/0"+i1;
                }else{
                    date+="/"+i1;
                }

                date += "/"+i;
                fechaInicio.setText(date);

                try {
                    setTiempoAlquiler();
                    getPrecioAlquiler();
                }catch (Exception e){
                    e.toString();
                }
            }
        };

        setDateListener2 = new DatePickerDialog.OnDateSetListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                i1 = i1 + 1;
                String date = "";

                if(i2<10){
                    date+="0"+i2;
                }else{
                    date+=i2;
                }

                if(i1<10){
                    date+="/0"+i1;
                }else{
                    date+="/"+i1;
                }

                date += "/"+i;
                fechaFin.setText(date);

                try {
                    setTiempoAlquiler();
                    getPrecioAlquiler();
                }catch (Exception e){
                    e.toString();
                }
            }
        };

        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlqDB db = new AlqDB(gest_alq.this);

                String idV = "";
                String idC = "";
                String fechaI = "";
                String fechaF = "";
                String tiempoAl = "";
                String precioAl = "";

                try {
                    idV = idVehiculo.getSelectedItem().toString().split("-")[0];
                    idC = idCliente.getSelectedItem().toString().split("-")[0];
                    fechaI = fechaInicio.getText().toString();
                    fechaF = fechaFin.getText().toString();
                    tiempoAl = tiempoAlquiler.getText().toString();
                    precioAl = precioAlquiler.getText().toString();
                }catch (Exception e){
                    e.toString();
                }

                boolean campoVacio = false;


                if(fechaI.isEmpty()){
                    campoVacio=true;
                }

                if(fechaF.isEmpty()){
                    campoVacio=true;
                }

                if(tiempoAl.isEmpty()){
                    campoVacio=true;
                }

                if(precioAl.isEmpty()){
                    campoVacio=true;
                }

                if(id>0) {
                    if (!campoVacio) {
                        boolean updated = db.updateAlquiler(id,fechaI, fechaF, tiempoAl, precioAl, idV, idC);

                        if (updated) {
                            Toast.makeText(gest_alq.this, "Alquiler actualizado con éxito", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(gest_alq.this, "Error al actualizar el alquiler", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(gest_alq.this, "No debe dejar campos vacios", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    if (!campoVacio) {
                        long id = db.createAlquiler(fechaI, fechaF, tiempoAl, precioAl, idV, idC);
                        vehiculosDB.updateEstadoVehiculo(Integer.parseInt(idV), "Alquilado");

                        if (id > 0) {
                            Toast.makeText(gest_alq.this, "Alquiler registrado con éxito", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(gest_alq.this, "Error al registrar alquiler", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(gest_alq.this, "No debe dejar campos vacios", Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean removed = alqDB.deleteAlquiler(id);

                if(removed){
                    Toast.makeText(gest_alq.this, "Alquiler eliminado con éxito", Toast.LENGTH_SHORT).show();
                    vehiculosDB.updateEstadoVehiculo(idV, "Disponible");
                    Intent intent = new Intent(gest_alq.this, Adm_Alq.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(gest_alq.this, "Error al eliminar alquiler", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void setTiempoAlquiler(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate inicioD = LocalDate.parse(fechaInicio.getText().toString(),formatter);
        LocalDate finalD = LocalDate.parse(fechaFin.getText().toString(),formatter);

        Period periodo = null;
        String tiempo = "";

        try {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                periodo = Period.between(inicioD, finalD);
                tiempo=String.valueOf(periodo.getYears()*365+(finalD.getDayOfYear()-inicioD.getDayOfYear()));
            }
        } catch (Exception e) {
            e.toString();
        }

        tiempoAlquiler.setText(tiempo);
    }
    public void getPrecioAlquiler(){
        int id = Integer.parseInt(idVehiculo.getSelectedItem().toString().split("-")[0]);
        int tiempo = Integer.parseInt(tiempoAlquiler.getText().toString());

        if (tiempo != 0) {
            vehiculosDB db = new vehiculosDB(gest_alq.this);
            Vehiculos datos = db.findVehiculo("idVehiculo", String.valueOf(id));
            int precio = 0;

            if (datos != null) {
                String tipo = datos.getTipo();

                switch (tipo) {
                    case "Coche":
                        precio = 65 * tiempo;
                        break;
                    case "Microbus":
                        precio = 65 * tiempo + 20;
                        break;
                    case "Furgoneta":
                        precio = 70 * tiempo;
                        break;
                    case "Camión":
                        precio = 75 * tiempo;
                        break;
                    default:
                        precio = 0;
                        break;
                }

            } else {
                Toast.makeText(gest_alq.this, "Error al calcular el precio", Toast.LENGTH_SHORT).show();
            }

            precioAlquiler.setText(String.valueOf(precio));
        }
    }
}