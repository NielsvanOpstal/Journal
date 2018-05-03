package com.example.niels.journal;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);

        TextView title = findViewById(R.id.detailTitle);
        TextView timestamp = findViewById(R.id.detailTimestamp);
        TextView content = findViewById(R.id.detailContent);


        Intent intent = getIntent();
        Bundle received = intent.getBundleExtra("bundle");
        title.setText(received.getString("title"));
        timestamp.setText(received.getString("timestamp"));
        content.setText(received.getString("content"));

    }
}