package com.a.bini;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class GameResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_result);

        Intent intent = getIntent();
        Boolean result = intent.getBooleanExtra("RESULT", true);

        Toast toast=Toast.makeText(getApplicationContext(),result.toString(),Toast.LENGTH_LONG);
        toast.show();
    }


}
