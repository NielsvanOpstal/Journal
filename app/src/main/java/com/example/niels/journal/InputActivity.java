package com.example.niels.journal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class InputActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input_activity);
        getIntent();
    }


    public void addEntry(View view) {

        EntryDatabase db = EntryDatabase.getInstance(this);

        // gets the title, content and mood from the corresponding text views in input_activity.xml
        String title = ((TextView) findViewById(R.id.entryTitle)).getText().toString();
        String content = ((TextView) findViewById(R.id.entryContent)).getText().toString();
        String mood = ((TextView) findViewById(R.id.entryMood)).getText().toString();

        // Creates a new entry and fills it with the content entered by the user
        JournalEntry entry = new JournalEntry();
        entry.setTitle(title);
        entry.setContent(content);
        entry.setMood(mood);

        // Calls the insert function to insert the entry in the database
        db.insert(entry);

        // Goes back to the mainactivity
        Intent intent = new Intent(InputActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
