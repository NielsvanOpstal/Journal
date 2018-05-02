package com.example.niels.journal;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class InputActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input_activity);
    }


    public void addEntry(View view) {
        EntryDatabase instance = EntryDatabase.getInstance(this);

        String title = ((TextView) findViewById(R.id.entryTitle)).getText().toString();
        String content = ((TextView) findViewById(R.id.entryContent)).getText().toString();
        String mood = ((TextView) findViewById(R.id.entryMood)).getText().toString();

        JournalEntry entry = new JournalEntry();

        entry.setTitle(title);
        entry.setContent(content);
        entry.setMood(mood);


        instance.insert(entry);
    }
}
