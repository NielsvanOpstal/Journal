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

public class MainActivity extends AppCompatActivity {
    private EntryDatabase db;
    private EntryAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = EntryDatabase.getInstance(getApplicationContext());

        Cursor cursor = db.selectAll();
        Log.d("hoi", String.valueOf(cursor.getCount()));
        adapter = new EntryAdapter(this, R.layout.entry_row, cursor, 1);
        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);
        listView.setOnItemLongClickListener(new onItemLongListener());
        listView.setOnItemClickListener(new onItemClickListener());


    }

    public void floatButtonClicked(View view) {
        Intent intent = new Intent(MainActivity.this, InputActivity.class);
        startActivity(intent);
    }

    private class onItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        }
    }

    private class onItemLongListener implements AdapterView.OnItemLongClickListener {
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            Cursor itemClicked = (Cursor) parent.getItemAtPosition(position);
            int idIndex = itemClicked.getColumnIndex("_id");
            long idClicked = Long.parseLong(itemClicked.getString(idIndex));
            db.delete(idClicked);
            updateData();

            return true;
        }
    }

    private void updateData() {
        Cursor updatedCursor = db.selectAll();
        adapter.swapCursor(updatedCursor);

    }

    @Override
    protected void onResume() {
        super.onResume();
        updateData();
    }
}
