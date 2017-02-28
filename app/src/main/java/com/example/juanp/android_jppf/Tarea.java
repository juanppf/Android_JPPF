package com.example.juanp.android_jppf;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.name;

/**
 * Created by JuanP on 28/02/2017.
 */

public class Tarea {

    public long id;
    public String nombre;
    public String descripcion;
    public String lugar;
    public String fecha;
    public String otros;

    public long getId() {
        return id;
    }

    // Será utilizado por el ArrayAdapter en el ListView
    @Override
    public String toString() {
        return nombre + ", "+descripcion + ", "+lugar;
    }
    private SQLiteDatabase database;
    private Database dbHelper;
    private String[] allColumns = { Database.COLUMN_ID,
            Database.COLUMN_NOMBRE,
            Database.COLUMN_DESCRIPCION,
            Database.COLUMN_LUGAR,
            Database.COLUMN_FECHA,
            Database.COLUMN_OTROS};

    public Tarea(Context context) {
        dbHelper = new Database(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }
// Para crear nuevas tareas
    public Tarea createContact(String nombre,String descripcion, String lugar, String fecha, String otros) {
        ContentValues values = new ContentValues();
        values.put(Database.COLUMN_NOMBRE, nombre);
        values.put(Database.COLUMN_DESCRIPCION, descripcion);
        values.put(Database.COLUMN_LUGAR, lugar);
        values.put(Database.COLUMN_FECHA, fecha);
        values.put(Database.COLUMN_OTROS, otros);
        long insertId = database.insert(Database.TABLE_ELEMENTOS, null,
                values);
        Cursor cursor = database.query(Database.TABLE_ELEMENTOS,
                allColumns, Database.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Tarea nuevaTarea= cursorToContact(cursor);
        cursor.close();
        return nuevaTarea;
    }
 // Para actualizar tareas
    public void updateContact(long id, String nombre,String descripcion, String lugar, String fecha, String otros) {
        ContentValues values = new ContentValues();
        values.put(Database.COLUMN_NOMBRE, nombre);
        values.put(Database.COLUMN_DESCRIPCION, descripcion);
        values.put(Database.COLUMN_LUGAR, lugar);
        values.put(Database.COLUMN_FECHA, fecha);
        values.put(Database.COLUMN_OTROS, otros);
        String where = "id=?";
        String[] whereargs = new String[]{String.valueOf(id)};
        long insertId = database.update(Database.TABLE_ELEMENTOS,
                values,where,whereargs );
        Cursor cursor = database.query(Database.TABLE_ELEMENTOS,
                allColumns, Database.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        cursor.close();

    }
// para borrar tareas
    public void deleteContact(long id) {
        System.out.println("Contact deleted with id: " + id);
        database.delete(Database.TABLE_ELEMENTOS, Database.COLUMN_ID
                + " = " + id, null);
    }

    public List<Tarea> getAll() {
        List<Tarea> comments = new ArrayList<Tarea>();

        Cursor cursor = database.query(Database.TABLE_ELEMENTOS,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Tarea tarea= cursorToContact(cursor);
            comments.add(tarea);
            cursor.moveToNext();
        }
        // asegura el cierre del cursor
        cursor.close();
        return comments;
    }

    private Tarea cursorToContact(Cursor cursor) {
        Tarea c = new Tarea(null);
        c.id = cursor.getLong(0);
        c.nombre = cursor.getString(1);
        c.descripcion = cursor.getString(2);
        c.lugar = cursor.getString(3);
        c.fecha = cursor.getString(4);
        c.otros = cursor.getString(5);
        return c;
    }
}
