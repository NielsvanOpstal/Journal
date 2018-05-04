package com.example.niels.journal;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import static android.widget.CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER;
import static com.example.niels.journal.EntryDatabase.CONTENT;
import static com.example.niels.journal.EntryDatabase.MOOD;
import static com.example.niels.journal.EntryDatabase.TIMESTAMP;
import static com.example.niels.journal.EntryDatabase.TITLE;

public class MainActivity extends AppCompatActivity {

    private EntryDatabase db;
    private EntryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Gets the databse
        db = EntryDatabase.getInstance(getApplicationContext());

        // Gets the cursor with all the entries in the database
        Cursor cursor = db.selectAll();

        // Gets the list view and sets an adapter, onItemClickListener and onItemLongClickListener
        ListView listView = findViewById(R.id.listView);
        adapter = new EntryAdapter(this, R.layout.entry_row, cursor, 1);
        listView.setAdapter(adapter);
        listView.setOnItemLongClickListener(new onItemLongListener());
        listView.setOnItemClickListener(new onItemClickListener());


    }

    public void floatButtonClicked(View view) {

        // Goes the input activity
        Intent intent = new Intent(MainActivity.this, InputActivity.class);
        startActivity(intent);
    }

    private class onItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        // Creates intent to the detail activity
        Intent intent = new Intent(MainActivity.this, DetailActivity.class);

        // Gets the cursor from the item clicked
        Cursor itemClicked = (Cursor) parent.getItemAtPosition(position);

        // Creates a bundle and fills it with the data from the cursor clicked, and adds it to the intent
        Bundle bundle = new Bundle();
        bundle.putString("title", itemClicked.getString(itemClicked.getColumnIndex(TITLE)));
        bundle.putString("content", itemClicked.getString(itemClicked.getColumnIndex(CONTENT)));
        bundle.putString("mood", itemClicked.getString(itemClicked.getColumnIndex(MOOD)));
        bundle.putString("timestamp", itemClicked.getString(itemClicked.getColumnIndex(TIMESTAMP)));
        intent.putExtra("bundle", bundle);

        // Starts the intent
        startActivity(intent);
        }
    }

    private class onItemLongListener implements AdapterView.OnItemLongClickListener {
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

            // Gets the cursor clicked and the id from the entry
            Cursor itemClicked = (Cursor) parent.getItemAtPosition(position);
            long idClicked = Long.parseLong(itemClicked.getString(itemClicked.getColumnIndex("_id")));

            // Calls the delete function to delete the entry clicked and refreshes the screen
            db.delete(idClicked);
            updateData();

            return true;
        }
    }

    private void updateData() {

        // Gets all the entries from the database
        Cursor updatedCursor = db.selectAll();

        // Sets the new cursor in the adapter
        adapter.swapCursor(updatedCursor);

    }

    @Override
    protected void onResume() {
        super.onResume();

        // Refreshes the data on the main activity
        updateData();
    }
}
