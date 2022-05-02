package com.example.rentacar_sql.base_datos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;

public class helperDB extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "alquiler.db";
    public static final String TABLA_VEHICULOS = "vehiculos";
    public static final String TABLA_CLIENTES = "clientes";
    public static final String TABLA_ALQUILERES = "alquileres";

    public helperDB(@NonNull Context context){
        super(context, DATABASE_NAME, null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE "+TABLA_ALQUILERES+"("+
                "idVehiculo INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "placa TEXT,"+
                "tipo TEXT,"+
                "estado TEXT,"+
                "modelo TEXT)");

        sqLiteDatabase.execSQL("CREATE TABLE "+TABLA_CLIENTES+"("+
                "idCliente INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "nombre TEXT,"+
                "telefono TEXT,"+
                "direccion TEXT)");

        sqLiteDatabase.execSQL("CREATE TABLE "+TABLA_VEHICULOS+"("+
                "idAlquiler INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "fechaIni TEXT,"+
                "fechFin TEXT,"+
                "tiempoAlq TEXT,"+
                "precio REAL,"+
                "idVehiculo INTEGER,"+
                "idCliente INTEGER,"+
                "FOREIGN KEY(idV) REFERENCES vehiculos(idVehiculo),"+
                "FOREIGN KEY(idC) REFERENCES clientes(idCliente))");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE "+TABLA_VEHICULOS);
        sqLiteDatabase.execSQL("DROP TABLE "+TABLA_CLIENTES);
        sqLiteDatabase.execSQL("DROP TABLE "+TABLA_ALQUILERES);
        onCreate(sqLiteDatabase);
    }
}
