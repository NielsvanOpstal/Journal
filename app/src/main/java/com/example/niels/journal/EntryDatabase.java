package com.example.niels.journal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Map;

import static android.icu.text.MessagePattern.ArgType.SELECT;

public class EntryDatabase extends SQLiteOpenHelper {
    private static EntryDatabase INSTANCE = null;
    public static final String DBNAME = "entries";
    public static int DBVERSION = 1;
    public static final String TITLE = "title";
    public static final String CONTENT = "content";
    public static final String MOOD = "mood";
    public static final String TIMESTAMP = "timestamp";


    private EntryDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String createDB = "CREATE TABLE entries (_id INTEGER PRIMARY KEY AUTOINCREMENT, " + TITLE + " TEXT, " + CONTENT + " TEXT, " + MOOD + " TEXT, "+ TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP );";
        db.execSQL(createDB);
        db.execSQL("INSERT INTO " + DBNAME +" (" + TITLE + ", " + CONTENT + ", " + MOOD + ") VALUES('De dag van Lucas', 'Het gaat niet zo goed', 'beetje boos')");
        db.execSQL("INSERT INTO " + DBNAME +" (" + TITLE + ", " + CONTENT + ", " + MOOD + ") VALUES('De dag van Niels', 'Het gaat wel oke', 'beetje oke')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + "entries");
        onCreate(db);

    }

    public static EntryDatabase getInstance(Context context) {
        if (INSTANCE != null) {
            return INSTANCE;
        }
        else {
            INSTANCE = new EntryDatabase(context, DBNAME, null, DBVERSION);
            return INSTANCE;
        }
    }

    public Cursor selectAll() {
        SQLiteDatabase database = getWritableDatabase();
        String query = "SELECT * FROM entries";
        Cursor cursor = database.rawQuery(query,null);
        return cursor;

    }

    public void insert(JournalEntry entry) {
        SQLiteDatabase database = getWritableDatabase();
        String query = "INSERT INTO " + DBNAME + " (" + TITLE + ", " + CONTENT + ", " + MOOD + ") VALUES (?, ?, ?);";
        database.rawQuery(query, new String[]{entry.getTitle(), entry.getContent(), entry.getMood()});
    }
}
