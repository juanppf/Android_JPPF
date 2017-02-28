package com.example.juanp.android_jppf;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Nueva extends AppCompatActivity {
    Button back,add_el;
    EditText nombre,descripcion,lugar,fecha,otros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva);
        back = (Button) findViewById(R.id.back2);
        add_el = (Button) findViewById(R.id.add_element);
        nombre= (EditText) findViewById(R.id.nombre);
        descripcion= (EditText) findViewById(R.id.descripcion);
        lugar= (EditText) findViewById(R.id.lugar);
        otros= (EditText) findViewById(R.id.otros);
        fecha= (EditText) findViewById(R.id.fecha);

// para crear una nueva tarea
        add_el.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nombre.getText().toString().length()>0 && descripcion.getText().toString().length()>0){


                    Tarea c = new Tarea(getBaseContext());
                    c.open();
                    c.createContact(nombre.getText().toString(), descripcion.getText().toString(), lugar.getText().toString(), fecha.getText().toString(), otros.getText().toString());
                    nombre.setText("");
                    descripcion.setText("");
                    lugar.setText("");
                    fecha.setText("");
                    otros.setText("");
                    Toast.makeText(getBaseContext(), "Elemento Agregado!!",Toast.LENGTH_LONG).show();


                }else{
                    Toast.makeText(getBaseContext(), "Error!!",Toast.LENGTH_LONG).show();
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
