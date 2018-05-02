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
        int titleIndex = cursor.getColumnIndex(TITLE);
        int contentIndex = cursor.getColumnIndex(CONTENT);
        int moodIndex = cursor.getColumnIndex(MOOD);
        int timeStampIndex = cursor.getColumnIndex(TIMESTAMP);

        String title = cursor.getString(titleIndex);
        String content = cursor.getString(contentIndex);
        String mood = cursor.getString(moodIndex);
        String timestamp = cursor.getString(timeStampIndex);

        TextView listTitle = view.findViewById(R.id.listTitle);
        TextView listTimestamp = view.findViewById(R.id.listTimestamp);

        listTitle.setText(title);
        listTimestamp.setText(timestamp);

    }
}
