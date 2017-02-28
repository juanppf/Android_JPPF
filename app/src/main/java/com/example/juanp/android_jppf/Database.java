package com.example.juanp.android_jppf;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/* Created by JuanP on 28/02/2017.
        */
public class Database extends SQLiteOpenHelper {
    public static final String TABLE_ELEMENTOS = "tareas";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NOMBRE = "nombre";
    public static final String COLUMN_DESCRIPCION = "descripcion";
    public static final String COLUMN_LUGAR = "lugar";
    public static final String COLUMN_FECHA = "fecha";
    public static final String COLUMN_OTROS = "otros";

    private static final String DATABASE_NOMBRE = "tareas.db";
    private static final int DATABASE_VERSION = 1;

    // para la creación de la base de datos
    private static final String DATABASE_CREATE = "create table "
            + TABLE_ELEMENTOS+ "(" + COLUMN_ID
            + " integer primary key autoincrement, "
            + COLUMN_NOMBRE+ " text not null,"
            + COLUMN_DESCRIPCION+ " text not null,"
            + COLUMN_LUGAR+ " text not null,"
            + COLUMN_FECHA+ " text not null,"
            + COLUMN_OTROS+ " text not null"
            +");";

    public Database(Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }
    // creación de la base de datos
    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }
// en caso de que exista la base de datos no volver a crearla
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(Database.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ELEMENTOS);
        onCreate(db);
    }
}
