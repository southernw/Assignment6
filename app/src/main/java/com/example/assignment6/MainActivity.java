package com.example.assignment6;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity  {
    private final String COUNT_SELECT = "count";
    private int count=0, countBy;


    private String stringValue;
    private final int MAX_COUNT = 10;

    private TextView txtCount, txtCountBy;
    private Button btnCount, btnReset;
    private SharedPreferences preferences;
    private String sharedPrefFile = "com.example.assignment6.sharedprefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtCount = (TextView) findViewById(R.id.txtCurrentCount);
        txtCountBy = (TextView) findViewById(R.id.txtCountBy);

        preferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);

        countBy = preferences.getInt(COUNT_SELECT,0);
        txtCountBy.setText(String.valueOf(countBy));


        btnCount = (Button) findViewById(R.id.btnCount);
        btnCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if (count == MAX_COUNT) {
                Toast.makeText(MainActivity.this, "Reached 10", Toast.LENGTH_LONG).show();
                count=0;
                    }else if (Integer.valueOf(txtCountBy.getText().toString()) == 2) {
                         count += 2;
                            txtCount.setText(Integer.toString((count)));
                     } else if (Integer.valueOf(txtCountBy.getText().toString()) == 5) {
                            count += 5;
                                txtCount.setText(Integer.toString(count));
                    } else if (Integer.valueOf(txtCountBy.getText().toString()) == 10) {
                            count += 10;
                                txtCount.setText(Integer.toString(count));
                }

            }
        });
        btnReset = (Button) findViewById(R.id.btnReset);
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor preferencesEditor = preferences.edit();
                preferencesEditor.clear();
                preferencesEditor.apply();
                txtCount.setText("0");
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor preferencesEditor = preferences.edit();
        preferencesEditor.putInt(COUNT_SELECT, ((Integer.valueOf(txtCountBy.getText().toString()))));
        preferencesEditor.apply();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.settings_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.itemCount2:
                txtCountBy.setText("2");
                break;
            case R.id.itemCount5:
                txtCountBy.setText("5");
                break;

            case R.id.itemCount10:
                txtCountBy.setText("10");
                break;

            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
