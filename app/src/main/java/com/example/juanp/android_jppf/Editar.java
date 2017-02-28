package com.example.juanp.android_jppf;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Editar extends AppCompatActivity {
    Button back,upd_el,del_btn;
    EditText nombre,descripcion,lugar,fecha,otros;
    long id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);
        back = (Button) findViewById(R.id.back2);
        upd_el = (Button) findViewById(R.id.upd_element);
        del_btn = (Button) findViewById(R.id.del_btn);
        nombre= (EditText) findViewById(R.id.nombre);
        descripcion= (EditText) findViewById(R.id.descripcion);
        lugar= (EditText) findViewById(R.id.lugar);
        fecha= (EditText) findViewById(R.id.fecha);
        otros= (EditText) findViewById(R.id.otros);

        Intent i = getIntent();
        id = i.getLongExtra("id",0);
        nombre.setText(i.getStringExtra("nombre"));
        descripcion.setText(i.getStringExtra("descripcion"));
        fecha.setText(i.getStringExtra("fecha"));
        lugar.setText(i.getStringExtra("lugar"));
        otros.setText(i.getStringExtra("otros"));
// llamada de actialización
        upd_el.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nombre.getText().toString().length() > 0 && descripcion.getText().toString().length() > 0) {


                    Tarea c = new Tarea(getBaseContext());
                    c.open();
                    c.updateContact(id, nombre.getText().toString(), descripcion.getText().toString(), lugar.getText().toString(), fecha.getText().toString(), otros.getText().toString());
                    Toast.makeText(getBaseContext(), "Elemento Actualizado!!", Toast.LENGTH_LONG).show();


                } else {
                    Toast.makeText(getBaseContext(), "Error!!", Toast.LENGTH_LONG).show();
                }
            }
        });
// cuadro de confirmación de borrado
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        del_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Editar.this);

                builder.setTitle(" - Confirmar - ");
                builder.setMessage("Estas seguro que deseas eliminar ?");

                builder.setPositiveButton("SI", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        Tarea c = new Tarea(getBaseContext());
                        c.open();
                        c.deleteContact(id);
                        finish();
                        dialog.dismiss();
                        Toast.makeText(getBaseContext(), "Elemento eliminado !!", Toast.LENGTH_LONG).show();

                    }

                });

                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Hacer nada
                        dialog.dismiss();
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }
}
