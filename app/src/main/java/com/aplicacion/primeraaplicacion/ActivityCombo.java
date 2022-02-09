package com.aplicacion.primeraaplicacion;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Spinner;

import java.lang.reflect.Array;
import java.util.ArrayList;

import configuraciones.SQLiteConexion;
import configuraciones.Transacciones;
import tablas.Empleados;

public class ActivityCombo extends AppCompatActivity {

    SQLiteConexion conexion;
    Spinner spempleados;
    EditText acnombres, acapellidos, acedad, accorreo;


    ArrayList<String> lista_empleados;
    ArrayList<Empleados> lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combo);

        conexion = new SQLiteConexion(this, Transacciones.NameDatabase, null,1);
        spempleados = (Spinner) findViewById(R.id.spempleados);
        acnombres = (EditText) findViewById(R.id.actxtnombres);
        acapellidos = (EditText) findViewById(R.id.actxtapellidos);

        ObtenerListaEmpleados();
    }

    private void ObtenerListaEmpleados() {
        Empleados emple = null;
        SQLiteDatabase db = conexion.getReadableDatabase();

        Cursor cursor = db.rawQuery("Select * from "+Transacciones.tablaempleados, null);

        while (cursor.moveToNext()){
            emple = new Empleados();
            emple
        }
    }
}