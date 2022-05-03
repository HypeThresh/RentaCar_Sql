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

    public long insertarCliente(String nombre){
        long id=0;
        try{


            helperDB dbhelper = new helperDB(context);
            SQLiteDatabase db = dbhelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("nombre",nombre);

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
            cursor = db.rawQuery("SELECT alquileres.idAlquiler,alquileres.fechaIni,alquileres.fechaFin,alquileres.tiempoAlq,alquileres.precio,vehiculos.modelo,vehiculos.placa " +
                    "as 'modelo',clientes.nombre as 'cliente' FROM alquileres INNER JOIN vehiculos  ON alquileres.idVehiculo = vehiculos.idVehiculo " +
                    "INNER JOIN clientes ON alquileres.idCliente = clientes.idCliente WHERE clientes.idCliente='"+idCliente+"'",null);

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
    public Clientes findClientes(String clave, String valor){
        helperDB dbhelper = new helperDB(context);
        SQLiteDatabase db = dbhelper.getWritableDatabase();

        Clientes cliente = null;
        Cursor cursorClientes = null;

        cursorClientes = db.rawQuery("SELECT * FROM "+TABLA_CLIENTES+" WHERE "+clave+" = \""+valor+"\" LIMIT 1",null);
        if (cursorClientes.moveToFirst()){
            cliente = new Clientes();
            cliente.setId(cursorClientes.getInt(0));
            cliente.setNombre(cursorClientes.getString(1));
        }
        cursorClientes.close();
        return cliente;
    }

    public ArrayList<String> spinnerClientes(){
        ArrayList<String> lista = new ArrayList<>();
        String ve = null;
        Cursor cursorClientes = null;

        try{

            helperDB DBHelper = new helperDB(context);
            SQLiteDatabase db = DBHelper.getReadableDatabase();
            cursorClientes = db.rawQuery("SELECT idC,nombre FROM "+TABLA_CLIENTES,null);

            if(cursorClientes.moveToFirst()){
                do{
                    ve = String.valueOf(cursorClientes.getInt(0));
                    ve += "-";
                    ve += cursorClientes.getString(1);
                    lista.add(ve);
                }while(cursorClientes.moveToNext());
            }
            db.close();
        }catch (Exception e){
            e.toString();
        }
        cursorClientes.close();
        return  lista;
    }

    public Clientes mostrarCliente(int idc){
        helperDB dbhelper = new helperDB(context);
        SQLiteDatabase db = dbhelper.getWritableDatabase();


        Clientes cliente =null;
        Cursor cursorClientes;

        cursorClientes = db.rawQuery("SELECT * FROM "+TABLA_CLIENTES + " WHERE idc = "+idc + " LIMIT 1",null);
        if (cursorClientes.moveToFirst()){

            cliente = new Clientes();
            cliente.setId(cursorClientes.getInt(0));
            cliente.setNombre(cursorClientes.getString(1));


        }
        cursorClientes.close();
        return cliente;
    }
    public boolean editarCliente(int id,String nombre){
        boolean encontrado =false;
        helperDB dbhelper = new helperDB(context);
        SQLiteDatabase db = dbhelper.getWritableDatabase();

        try{
            db.execSQL("UPDATE " + TABLA_CLIENTES + " SET nombre='" +nombre+ "' WHERE idc='"+id+"' ");
            encontrado = true;
        }catch (Exception ex){
            ex.toString();
        }finally {
            db.close();
        }


        return encontrado;
    }
    public boolean eliminarClinete(int id){
        boolean encontrado = false;
        helperDB dbhelper = new helperDB(context);
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        try{
            db.execSQL("DELETE FROM "+TABLA_CLIENTES+" WHERE idc = '"+id+"'");
            encontrado = true;
        }catch (Exception ex){
            ex.toString();
        }finally {
            db.close();
        }


        return encontrado;

    }
}
