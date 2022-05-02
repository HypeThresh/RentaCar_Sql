package com.example.rentacar_sql.base_datos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.rentacar_sql.objects.Clientes;
import com.example.rentacar_sql.objects.Vehiculos_Clientes;

import java.util.ArrayList;

public class clientesDB extends helperDB {

    Context context;

    public clientesDB(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    public long insertarCliente(String nombre, String telefono, String direccion){
        long id=0;
        try{


            helperDB dbhelper = new helperDB(context);
            SQLiteDatabase db = dbhelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("nombre",nombre);
            values.put("telfono",telefono);
            values.put("direccion",direccion);

            id = db.insert(TABLA_CLIENTES,null,values);
        }catch (Exception ex){
            ex.toString();
        }
        return id;
    }

    public ArrayList<Clientes> mostrarClientes(){
        helperDB dbhelper = new helperDB(context);
        SQLiteDatabase db = dbhelper.getWritableDatabase();

        ArrayList<Clientes> listaClientes = new ArrayList<>();
        Clientes cliente = null;
        Cursor cursorClientes = null;

        cursorClientes = db.rawQuery("SELECT * FROM "+TABLA_CLIENTES,null);
        if (cursorClientes.moveToFirst()){
            do {
                cliente = new Clientes();
                cliente.setId(cursorClientes.getInt(0));
                cliente.setNombre(cursorClientes.getString(1));
                cliente.setTelefono(cursorClientes.getString(2));
                listaClientes.add(cliente);
            }while (cursorClientes.moveToNext());

        }
        cursorClientes.close();
        return listaClientes;
    }

    public ArrayList<Vehiculos_Clientes> listadoVehiculosCliente(int idCliente){
        ArrayList<Vehiculos_Clientes> lista = new ArrayList<>();
        Vehiculos_Clientes vehi = null;
        Cursor cursor = null;



        try{
            helperDB dbhelper = new helperDB(context);
            SQLiteDatabase db = dbhelper.getWritableDatabase();
            cursor = db.rawQuery("SELECT alquileres.idA,alquileres.fechaInicio,alquileres.fechaFin,alquileres.tiempoAlquiler,alquileres.precioAlquiler,vehiculos.nombre,vehiculos.placa " +
                    "as 'nombreV',clientes.nombre as 'nombreC' FROM alquileres INNER JOIN vehiculos  ON alquileres.idV = vehiculos.idV " +
                    "INNER JOIN clientes ON alquileres.idC = clientes.idC WHERE clientes.idC='"+idCliente+"'",null);

            if (cursor.moveToFirst()){
                do {
                    vehi = new Vehiculos_Clientes();

                    vehi.setFechaIni(cursor.getString(1));

                    vehi.setFechFin(cursor.getString(2));

                    vehi.setTiempoAlq(cursor.getString(3));

                    vehi.setPrecio(cursor.getString(4));

                    vehi.setModelo(cursor.getString(5));

                    vehi.setPlaca(cursor.getString(6));

                    lista.add(vehi);

                }while (cursor.moveToNext());
            }cursor.close();
            Toast.makeText(context, "Consulta hecha", Toast.LENGTH_SHORT).show();
            db.close();
        }
        catch (Exception e){
            Toast.makeText(context, "error consulta"+e.toString(), Toast.LENGTH_SHORT).show();
            Log.d("ErrorBase: ", e.toString());
            e.toString();
        }

        return lista;
    }
}
