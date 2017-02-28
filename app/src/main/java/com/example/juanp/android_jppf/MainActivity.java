package com.example.juanp.android_jppf;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button todo,nuevo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        todo = (Button)findViewById(R.id.elementos);
        nuevo= (Button)findViewById(R.id.nuevo_elemento);
        // abre la activity donde se muestran la tareas
        todo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Elementos.class);
                startActivity(i);
            }
        });
        // abre la activity donde se crean las tareas
        nuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Nueva.class);
                startActivity(i);
            }
        });

    }
}
