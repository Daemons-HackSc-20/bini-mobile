package com.a.bini;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.a.bini.utils.Callbacks;
import com.a.bini.utils.NetworkUtils;
import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

public class GameActivity extends AppCompatActivity {
    private ImageView mImageViewPlayGuess;
    private Button mButtonSubmitYes;
    private Button mButtonSubmitNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        mImageViewPlayGuess = findViewById(R.id.img_play_guess);
        mButtonSubmitYes = findViewById(R.id.btn_submit_answer_yes);
        mButtonSubmitNo = findViewById(R.id.btn_submit_answer_no);

        setOnclickSubmitButtons();

        fetchQuestion();
    }

    private void setOnclickSubmitButtons() {
        mButtonSubmitYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast=Toast.makeText(getApplicationContext(),"yes",Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        mButtonSubmitNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast=Toast.makeText(getApplicationContext(),"no",Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }

    private void fetchQuestion() {
        NetworkUtils.fetchRandomQuestion(getApplicationContext(), new Callbacks.VolleyCallback() {
            @Override
            public void onSuccess(JSONObject response) {

                try {
                    String imageUri = response.getJSONObject("question").getString("image_uri");
                } catch (JSONException e) {
                }

            }

            @Override
            public void onError(VolleyError error) {
                Toast toast=Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }
}
