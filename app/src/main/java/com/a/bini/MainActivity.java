package com.a.bini;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button snapActivityBtn;
    private Button gameActivityBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        snapActivityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToSnapActivity();
            }
        });

        gameActivityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToGameActivity();
            }
        });
    }

    private void init() {
        snapActivityBtn = findViewById(R.id.snapActivityBtn);
        gameActivityBtn = findViewById(R.id.gameActivityBtn);
    }

    private void goToSnapActivity() {
        Intent intent = new Intent(this, SnapActivity.class);
//        EditText editText = (EditText) findViewById(R.id.editText);
//        String message = editText.getText().toString();
//        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    private void goToGameActivity() {
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }
}
