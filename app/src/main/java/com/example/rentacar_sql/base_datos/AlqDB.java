package com.example.rentacar_sql.base_datos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.NonNull;

import com.example.rentacar_sql.objects.AlqList;
import com.example.rentacar_sql.objects.Alquiler;

import java.util.ArrayList;

public class AlqDB extends helperDB{
    Context context;
    public AlqDB(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    public long createAlquiler(String fechaIni, String fechaFin,String tiempoAlq,String precio,String idVehiculo,String idCliente){
        long id=0;

        try{
            helperDB DBHelper = new helperDB(context);
            SQLiteDatabase db = DBHelper.getReadableDatabase();

            ContentValues values= new ContentValues();
            values.put("fechaIni",fechaIni);
            values.put("fechaFin",fechaFin);
            values.put("tiempoAlq",tiempoAlq);
            values.put("precio",precio);
            values.put("idVehiculo",idVehiculo);
            values.put("idCliente",idCliente);

            id = db.insert(TABLA_ALQUILERES,null,values);
            db.close();
        }catch (Exception e){
            e.toString();
        }

        return id;
    }

    public ArrayList<AlqList> readListAlquiler(){
        ArrayList<AlqList> lista = new ArrayList<>();
        AlqList al = null;
        Cursor cursorVehiculos = null;

        try{

            helperDB DBHelper = new helperDB(context);
            SQLiteDatabase db = DBHelper.getReadableDatabase();
            cursorVehiculos = db.rawQuery("SELECT alquileres.idAlquiler,vehiculos.modelo as \"modelo\",clientes.nombre as \"nombre\" FROM alquileres INNER JOIN vehiculos  ON alquileres.idVehiculo = vehiculos.idVehiculo INNER JOIN clientes ON alquileres.idCliente = clientes.idCliente",null);

            if(cursorVehiculos.moveToFirst()){
                do{
                    al = new AlqList();
                    al.setIdAlquiler(cursorVehiculos.getInt(0));
                    al.setModelo(cursorVehiculos.getString(1));
                    al.setNombre(cursorVehiculos.getString(2));

                    lista.add(al);
                }while(cursorVehiculos.moveToNext());
            }
            db.close();
        }catch (Exception e){
            e.toString();
        }
        cursorVehiculos.close();
        return  lista;
    }

    public Alquiler findAlquiler(String clave, String valor){
        Alquiler al = null;
        Cursor cursorAlquiler = null;

        try {
            helperDB DBHelper = new helperDB(context);
            SQLiteDatabase db = DBHelper.getReadableDatabase();
            cursorAlquiler = db.rawQuery("SELECT * FROM "+TABLA_ALQUILERES+" WHERE "+clave+" = \""+valor+"\" LIMIT 1",null);
            //Toast.makeText(context, "SELECT * FROM "+TABLA_VEHICULOS+" WHERE "+clave+" = \""+valor+"\" LIMIT 1", Toast.LENGTH_SHORT).show();
            if(cursorAlquiler.moveToFirst()){
                al = new Alquiler();
                al.setIdAlquiler(cursorAlquiler.getInt(0));
                al.setFechaIni(cursorAlquiler.getString(1));
                al.setFechaFin(cursorAlquiler.getString(2));
                al.setTiempoAlquiler(cursorAlquiler.getString(3));
                al.setPrecioAlquiler(cursorAlquiler.getString(4));
                al.setIdVehiculo(cursorAlquiler.getInt(5));
                al.setIdCliente(cursorAlquiler.getInt(6));
            }

        }catch (Exception e){
            e.toString();
        }

        cursorAlquiler.close();
        return  al;
    }
    public boolean updateAlquiler(int id,String fechaIni, String fechaFin,String tiempoAlq,String precio,String idVehiculo,String idCliente){
        boolean updated = false;

        try{
            helperDB DBHelper = new helperDB(context);
            SQLiteDatabase db = DBHelper.getReadableDatabase();

            ContentValues values= new ContentValues();
            values.put("fechaInicio",fechaIni);
            values.put("fechaFin",fechaFin);
            values.put("tiempoAlquiler",tiempoAlq);
            values.put("precioAlquiler",precio);
            values.put("idVehiculo",idVehiculo);
            values.put("idCliente",idCliente);

            db.update(TABLA_ALQUILERES,values,"idAlquiler = "+id,null);
            updated=true;
            db.close();
        }catch (Exception e){
            updated = false;
            e.toString();
        }

        return updated;
    }
    public boolean deleteAlquiler(int id){
        helperDB DBHelper = new helperDB(context);
        SQLiteDatabase db = DBHelper.getReadableDatabase();

        boolean removed = false;

        try{
            db.delete(TABLA_ALQUILERES,"idAlquiler=?",new String[]{String.valueOf(id)});
            removed = true;
        }catch (Exception e){
            removed = false;
            e.toString();
        }finally {
            db.close();
        }

        return removed;
    }

}
