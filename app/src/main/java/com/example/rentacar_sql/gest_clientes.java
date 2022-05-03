package com.example.rentacar_sql;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rentacar_sql.adap_list.vehiculo_clientes_lista;
import com.example.rentacar_sql.base_datos.clientesDB;
import com.example.rentacar_sql.objects.Clientes;
import com.example.rentacar_sql.objects.Vehiculos_Clientes;

import java.util.ArrayList;

public class gest_clientes extends AppCompatActivity {

    private TextView idtext; //textViewID
    private EditText nombretxt;
    private Button editarBtn,eliminarBtn;
    Clientes cliente;

    RecyclerView listaVehiculosCliente;
    ArrayList<Vehiculos_Clientes> listaCCvehiculos;

    boolean pasar =false;
    boolean modoEdicion = false;
    int id=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gest_clientes);

        idtext = findViewById(R.id.textViewId);
        nombretxt = findViewById(R.id.NombreClientetxt2);
        editarBtn = findViewById(R.id.botonActulizarCliente);
        eliminarBtn = findViewById(R.id.botonEliminarCliente);
        listaVehiculosCliente = findViewById(R.id.vehiculosDeUsuarios);
        listaVehiculosCliente.setLayoutManager(new LinearLayoutManager(this));

        if(savedInstanceState ==null){
            Bundle extras = getIntent().getExtras();
            if (extras==null){
                id = Integer.parseInt(null);
            }else{
                modoEdicion = extras.getBoolean("editar");
                id = extras.getInt("id");
            }
        }else{
            id = (int) savedInstanceState.getSerializable("id");
        }

        clientesDB dbclientes = new clientesDB(gest_clientes.this);
        listaCCvehiculos = new ArrayList<>();
        vehiculo_clientes_lista adapter = new vehiculo_clientes_lista(dbclientes.listadoVehiculosCliente(id));
        listaVehiculosCliente.setAdapter(adapter);
        cliente = dbclientes.mostrarCliente(id);

        if (cliente !=null){
            nombretxt.setText(cliente.getNombre());
            idtext.setText(String.valueOf(cliente.getId()));

        }
        if (!modoEdicion){
            editarBtn.setVisibility(View.INVISIBLE);
            eliminarBtn.setVisibility(View.INVISIBLE);
            nombretxt.setInputType(InputType.TYPE_NULL);
        }
    }

    public void btonEditarCliente (View view){
        if (!nombretxt.getText().toString().equals("")){
            clientesDB clientesDB = new clientesDB(gest_clientes.this);
            pasar = clientesDB.editarCliente(id,nombretxt.getText().toString());

            if (pasar){
                Toast.makeText(this, "REGISTRO EDITADO CON EXITO", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this, "ERROR EN LA MODIFICAION DEL CLIENTE", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, "Parametros vacios!", Toast.LENGTH_SHORT).show();
        }

    }
    public void btonEliminarCliente(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(gest_clientes.this);
        builder.setMessage("Eliminar el contacto?")
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        clientesDB clientesDB = new clientesDB(gest_clientes.this);
                        if (clientesDB.eliminarClinete(id)){
                            //regresar al la vista
                            Toast.makeText(gest_clientes.this, "CLIENTE ELIMINADO CONE EXITO!", Toast.LENGTH_SHORT).show();
                            principal();

                        }

                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        }).show();
    }
    private void principal(){
        Intent intent = new Intent(this, Adm_CLients.class);
        startActivity(intent);
    }
}