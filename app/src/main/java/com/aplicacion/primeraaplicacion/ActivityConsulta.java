package com.aplicacion.primeraaplicacion;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import configuraciones.SQLiteConexion;
import configuraciones.Transacciones;

public class ActivityConsulta extends AppCompatActivity {

    SQLiteConexion conexion;
    EditText id, nombres, apellidos, edad, correo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta);

        conexion = new SQLiteConexion(this, Transacciones.NameDatabase,null, 1);
        Button btnConsulta = (Button) findViewById(R.id.btnConsulta);

        id = (EditText) findViewById(R.id.codigoFind);
        nombres = (EditText) findViewById(R.id.txtnom);
        apellidos = (EditText) findViewById(R.id.txtape);
        edad = (EditText) findViewById(R.id.txtage);
        correo = (EditText) findViewById(R.id.txtemail);

        btnConsulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Buscar();
            }
        });

    }

    private void Buscar() {

        SQLiteDatabase db = conexion.getWritableDatabase();
        //Parametros de consulta para realizar la sentencia SELECT

        String [] params = { id.getText().toString() };
        String [] fields = {
                Transacciones.nombres,
                Transacciones.apellidos,
                Transacciones.edad,
                Transacciones.correo,
        };

        String whereCondition = Transacciones.id + " =?";

        try {

            Cursor cdata = db.query(Transacciones.tablaempleados, fields, whereCondition,params, null, null, null);
            cdata.moveToFirst(); // moveToFirst = Obtener solo el primer elemento

            nombres.setText(cdata.getString(0));
            apellidos.setText(cdata.getString(1));
            edad.setText(cdata.getString(2));
            correo.setText(cdata.getString(3));

            Toast.makeText(getApplicationContext(), "Consultado con exito", Toast.LENGTH_LONG).show();

        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(), "Elemento no encontrado "+ex.getMessage(), Toast.LENGTH_LONG).show();
        }


    }
}