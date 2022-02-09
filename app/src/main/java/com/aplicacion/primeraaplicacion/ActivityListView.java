package com.aplicacion.primeraaplicacion;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import configuraciones.SQLiteConexion;
import configuraciones.Transacciones;
import tablas.Empleados;

public class ActivityListView extends AppCompatActivity {

    /*VARIABLES GLOBALES*/

    SQLiteConexion conexion;
    ListView lista;
    ArrayList<Empleados> listaEmpleados;
    ArrayList<String> ArregloEmpleados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        conexion = new SQLiteConexion(this, Transacciones.NameDatabase, null, 1);
        lista = (ListView) findViewById(R.id.list);

        obtenerListaEmpleados();

        ArrayAdapter adp = new ArrayAdapter(this, android.R.layout.simple_list_item_1, ArregloEmpleados);
        lista.setAdapter(adp);

    }

    private void obtenerListaEmpleados() {
        SQLiteDatabase db = conexion.getReadableDatabase();
        Empleados list_emple = null;
        listaEmpleados = new ArrayList<Empleados>();

        Cursor cursor = db.rawQuery("SELECT * FROM " + Transacciones.tablaempleados, null);


        while (cursor.moveToNext()){
            list_emple = new Empleados();
            list_emple.setId(cursor.getInt(0));
            list_emple.setNombres(cursor.getString(1));
            list_emple.setApellidos(cursor.getString(2));
            list_emple.setEdad(cursor.getInt(3));
            list_emple.setCorreo(cursor.getString(4));

            listaEmpleados.add(list_emple);
        }

        cursor.close();

        llenarlista();

    }

    private void llenarlista() {
        ArregloEmpleados = new ArrayList<String>();

        for (int i=0; i < listaEmpleados.size();i++){
            ArregloEmpleados.add(listaEmpleados.get(i).getId()+" | "+
                    listaEmpleados.get(i).getNombres()+" | "+
                    listaEmpleados.get(i).getApellidos()
                    );
        }
    }
}