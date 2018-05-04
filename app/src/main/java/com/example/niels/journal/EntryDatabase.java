package com.example.niels.journal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.Map;

import static android.icu.text.MessagePattern.ArgType.SELECT;

public class EntryDatabase extends SQLiteOpenHelper {

    private static EntryDatabase INSTANCE = null;
    private static final String DBNAME = "entries";
    public static final String TITLE = "title";
    public static final String CONTENT = "content";
    public static final String MOOD = "mood";
    public static final String TIMESTAMP = "timestamp";
    private SQLiteDatabase db;

    private EntryDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // Query to create the database
        String createDB = "CREATE TABLE entries (_id INTEGER PRIMARY KEY AUTOINCREMENT, " + TITLE + " TEXT, " + CONTENT + " TEXT, " + MOOD + " TEXT, "+ TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP );";

        // Creates the database and two entries
        db.execSQL(createDB);
        db.execSQL("INSERT INTO " + DBNAME +" (" + TITLE + ", " + CONTENT + ", " + MOOD + ") VALUES('De dag van Lucas', 'Het gaat niet zo goed', 'beetje boos')");
        db.execSQL("INSERT INTO " + DBNAME +" (" + TITLE + ", " + CONTENT + ", " + MOOD + ") VALUES('De dag van Niels', 'Het gaat wel oke', 'beetje oke')");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // Drops the database and creates a new one
        db.execSQL("DROP TABLE IF EXISTS " + "entries");
        onCreate(db);

    }

    public static EntryDatabase getInstance(Context context) {

        // If there is an instance of the databse, return it
        if (INSTANCE != null) {
            return INSTANCE;
        }

        // Else create one
        else {
            INSTANCE = new EntryDatabase(context, DBNAME, null, 1);
            return INSTANCE;
        }
    }

    public Cursor selectAll() {
        // fills the SQLiteDatabase
        db = getWritableDatabase();

        // Query to get everything from the database and returns it as a cursor
        String query = "SELECT * FROM " + DBNAME + "";
        Cursor cursor = db.rawQuery(query,null);
        return cursor;

    }

    public void insert(JournalEntry entry) {

        // Inserts the entry into the database
        db.execSQL("INSERT INTO " + DBNAME +" (" + TITLE + ", " + CONTENT + ", " + MOOD + ") VALUES('" + entry.getTitle() + "', '" + entry.getContent() + "', '" + entry.getMood()  + "')");

    }

    public void delete(long id) {

        // Deletes the entry from the database with _id id
        db.execSQL("DELETE FROM " + DBNAME + " WHERE _id = " + id + ";");
    }

}
