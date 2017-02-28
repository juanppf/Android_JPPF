package com.example.juanp.android_jppf;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

public class Elementos extends ListActivity {
    Button volver;
    Tarea data;

     // muestra los elementos
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elementos);

        volver= (Button) findViewById(R.id.volver);
        data = new Tarea(this);
        data.open();

        final List<Tarea> values = data.getAll();
        ArrayAdapter<Tarea> adapter = new ArrayAdapter<Tarea>(this, android.R.layout.simple_expandable_list_item_1,values);
        setListAdapter(adapter);

        ListView listView = getListView();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent i = new Intent(Elementos.this,Editar.class);
                i.putExtra("id",values.get(position).id);
                i.putExtra("nombre",values.get(position).nombre);
                i.putExtra("descripcion",values.get(position).descripcion);
                i.putExtra("lugar",values.get(position).lugar);
                i.putExtra("fecha",values.get(position).fecha);
                i.putExtra("otros",values.get(position).otros);
                startActivity(i);

            }

        });


        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    @Override
    public void onResume()
    {  // Después de una pausa o al inicio
        super.onResume();
        //Refresca la lista
        final List<Tarea>  values = data.getAll();
        ArrayAdapter<Tarea> adapter = new ArrayAdapter<Tarea>(this, android.R.layout.simple_expandable_list_item_1,values);
        setListAdapter(adapter);
    }
}
