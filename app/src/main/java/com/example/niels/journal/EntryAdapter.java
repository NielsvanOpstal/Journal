package com.example.niels.journal;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;

import static com.example.niels.journal.EntryDatabase.CONTENT;
import static com.example.niels.journal.EntryDatabase.MOOD;
import static com.example.niels.journal.EntryDatabase.TIMESTAMP;
import static com.example.niels.journal.EntryDatabase.TITLE;

public class EntryAdapter extends ResourceCursorAdapter {

    public EntryAdapter(Context context, int layout, Cursor c, int flags) {
        super(context, R.layout.entry_row, c, flags);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        // Gets the text views in the entry_row.xml
        TextView listTitle = view.findViewById(R.id.listTitle);
        TextView listTimestamp = view.findViewById(R.id.listTimestamp);
        TextView listMood = view.findViewById(R.id.listMood);

        // Gets the index from the cursor, gets the string from that index, set the string to the textView
        listTitle.setText(cursor.getString(cursor.getColumnIndex(TITLE)));
        listTimestamp.setText(cursor.getString(cursor.getColumnIndex(TIMESTAMP)));
        listMood.setText(cursor.getString(cursor.getColumnIndex(MOOD)));

    }
}
