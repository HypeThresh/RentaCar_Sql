package com.example.rentacar_sql.base_datos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.rentacar_sql.objects.Vehiculos;

import java.util.ArrayList;

public class vehiculosDB extends helperDB{

    Context context;

    public vehiculosDB(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long createVehiculo(String placa, String tipo, String estado,String modelo){
        long id=0;

        try{
            helperDB DBHelper = new helperDB(context);
            SQLiteDatabase db = DBHelper.getReadableDatabase();

            ContentValues values= new ContentValues();
            values.put("placa",placa);
            values.put("tipo",tipo);
            values.put("estado",estado);
            values.put("modelo",modelo);

            id = db.insert(TABLA_VEHICULOS,null,values);
            db.close();
        }catch (Exception e){
            e.toString();
        }

        return id;
    }
    public ArrayList<String> spinnerVehiculos(){
        ArrayList<String> lista = new ArrayList<>();
        String ve = null;
        Cursor cursorVehiculos = null;

        try{

            helperDB DBHelper = new helperDB(context);
            SQLiteDatabase db = DBHelper.getReadableDatabase();
            cursorVehiculos = db.rawQuery("SELECT idVehiculo,modelo FROM "+TABLA_VEHICULOS+" WHERE estado=\"Disponible\"",null);

            if(cursorVehiculos.moveToFirst()){
                do{
                    ve = String.valueOf(cursorVehiculos.getInt(0));
                    ve += "-";
                    ve += cursorVehiculos.getString(1);
                    lista.add(ve);
                }while(cursorVehiculos.moveToNext());
            }
            db.close();
        }catch (Exception e){
            e.toString();
        }
        cursorVehiculos.close();
        return  lista;
    }

    public ArrayList<Vehiculos> readVehiculos (){
        ArrayList<Vehiculos> lista = new ArrayList<>();
        Vehiculos ve = null;
        Cursor cursorVehiculos = null;

        try{

            helperDB DBHelper = new helperDB(context);
            SQLiteDatabase db = DBHelper.getReadableDatabase();
            cursorVehiculos = db.rawQuery("SELECT * FROM "+TABLA_VEHICULOS,null);

            if(cursorVehiculos.moveToFirst()){
                do{
                    ve = new Vehiculos();
                    ve.setId(cursorVehiculos.getInt(0));
                    ve.setPlaca(cursorVehiculos.getString(1));
                    ve.setTipo(cursorVehiculos.getString(2));
                    ve.setEstado(cursorVehiculos.getString(3));
                    ve.setModelo(cursorVehiculos.getString(4));

                    lista.add(ve);
                }while(cursorVehiculos.moveToNext());
            }
            db.close();
        }catch (Exception e){
            e.toString();
        }
        cursorVehiculos.close();
        return  lista;
    }

    public Vehiculos findVehiculo(String clave, String valor){
        Vehiculos ve = null;
        Cursor cursorVehiculo = null;

        try {
            helperDB DBHelper = new helperDB(context);
            SQLiteDatabase db = DBHelper.getReadableDatabase();
            cursorVehiculo = db.rawQuery("SELECT * FROM "+TABLA_VEHICULOS+" WHERE "+clave+" = \""+valor+"\" LIMIT 1",null);
            if(cursorVehiculo.moveToFirst()){
                ve = new Vehiculos();
                ve.setId(cursorVehiculo.getInt(0));
                ve.setPlaca(cursorVehiculo.getString(1));
                ve.setTipo(cursorVehiculo.getString(2));
                ve.setEstado(cursorVehiculo.getString(3));
                ve.setModelo(cursorVehiculo.getString(4));
            }

        }catch (Exception e){
            e.toString();
        }

        cursorVehiculo.close();
        return  ve;
    }

    public boolean updateVehiculo(int id,String placa, String tipo, String estado,String modelo){
        helperDB DBHelper = new helperDB(context);
        SQLiteDatabase db = DBHelper.getReadableDatabase();

        boolean updated = false;

        try {
            ContentValues values= new ContentValues();
            values.put("placa",placa);
            values.put("tipo",tipo);
            values.put("estado",estado);
            values.put("modelo",modelo);

            db.update(TABLA_VEHICULOS,values,"idVehiculo=?",new String[]{String.valueOf(id)});
            updated = true;
        }catch (Exception e){
            updated = false;
            e.toString();
        }finally {
            db.close();
        }

        return updated;
    }

    public boolean updateEstadoVehiculo(int id, String estado){
        helperDB DBHelper = new helperDB(context);
        SQLiteDatabase db = DBHelper.getReadableDatabase();

        boolean updated = false;

        try {
            ContentValues values= new ContentValues();
            values.put("estado",estado);
            db.update(TABLA_VEHICULOS,values,"idVehiculo=?",new String[]{String.valueOf(id)});
            updated = true;
        }catch (Exception e){
            updated = false;
            e.toString();
        }finally {
            db.close();
        }

        return updated;
    }

    public boolean deleteVehiculo(int id){
        helperDB helperDB = new helperDB(context);
        SQLiteDatabase db = helperDB.getReadableDatabase();

        boolean removed = false;

        try{
            db.delete(TABLA_VEHICULOS,"idVehiculo=?",new String[]{String.valueOf(id)});
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
