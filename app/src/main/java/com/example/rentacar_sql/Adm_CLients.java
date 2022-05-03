package com.example.rentacar_sql;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rentacar_sql.adap_list.clientes_lista;
import com.example.rentacar_sql.base_datos.clientesDB;
import com.example.rentacar_sql.objects.Clientes;

import java.util.ArrayList;

public class Adm_CLients extends AppCompatActivity {
    private EditText txtNombre;
    private RecyclerView listaClientes;
    ArrayList<Clientes> listaArrayClientes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adm_clients);

        txtNombre = findViewById(R.id.txtNombres);
        listaClientes = findViewById(R.id.listaClientes);
        listaClientes.setLayoutManager( new LinearLayoutManager(this));

        clientesDB dbCliente = new clientesDB(Adm_CLients.this);
        listaArrayClientes = new ArrayList<>();
        clientes_lista adapter = new clientes_lista(dbCliente.mostrarClientes());
        listaClientes.setAdapter(adapter);
    }

    public void agregarCliente(View view){
        clientesDB dbClientes = new clientesDB(Adm_CLients.this);
        long id= dbClientes.insertarCliente(txtNombre.getText().toString());
        if (id >0){
            Toast.makeText(this, "CLIENTE REGISTRADO", Toast.LENGTH_SHORT).show();
            limpiarFormulario();
        }else{
            Toast.makeText(this, "ERROR AL REGISTRAR AL CLIENTE", Toast.LENGTH_SHORT).show();
        }
    }
    private void limpiarFormulario(){ txtNombre.setText(""); }
}