package com.joeymejias.jobschedulerlab;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements DataSingleton.DataChangeListener{

    TextView oldText, newText, evenNewerText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        oldText = (TextView) findViewById(R.id.textview_1);
        newText = (TextView) findViewById(R.id.textview_2);
        evenNewerText = (TextView) findViewById(R.id.textview_3);

        oldText.setText("Time: unknown");
        newText.setText("Date: unknown");
        evenNewerText.setText("Year: unknown");

        final Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, JobActivity.class);
                startActivity(intent);
            }
        });


    }

    @Override
    public void onDataChanged(String oldText) {
        Toast.makeText(MainActivity.this, "Data changed", Toast.LENGTH_SHORT).show();
    }
}

