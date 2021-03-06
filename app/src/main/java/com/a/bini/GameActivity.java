package com.a.bini;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.a.bini.utils.Callbacks;
import com.a.bini.utils.NetworkUtils;
import com.android.volley.VolleyError;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

public class GameActivity extends AppCompatActivity {
    private ImageView mImageViewPlayGuess;
    private ImageView mButtonSubmitYes;
    private ImageView mButtonSubmitNo;
    private String mObjectId;
    private String mImageUri;

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
                submitAnswer(true);
            }
        });

        mButtonSubmitNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               submitAnswer(false);
            }
        });
    }

    private void fetchQuestion() {
        NetworkUtils.fetchRandomQuestion(getApplicationContext(), new Callbacks.VolleyCallback() {
            @Override
            public void onSuccess(JSONObject response) {

                try {
                    String imageUri = response.getJSONObject("question").getString("image_uri");
                    Picasso.get().load(imageUri).into(mImageViewPlayGuess);
                    mImageUri = imageUri;
                    mObjectId = response.getJSONObject("question").getString("_id");
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

    private void submitAnswer(boolean answer) {
        NetworkUtils.submitAnswer(Boolean.toString(answer), mObjectId, getApplicationContext(), new Callbacks.VolleyCallback() {
            @Override
            public void onSuccess(JSONObject response) {
                try {
                    Boolean result = response.getBoolean("result");
                    Intent intent = new Intent(getApplicationContext(), GameResultActivity.class);
                    intent.putExtra("RESULT", result);
                    intent.putExtra("IMAGE_URI", mImageUri);
                    startActivity(intent);;
                } catch (JSONException e) {
                }
            }

            @Override
            public void onError(VolleyError error) {
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        fetchQuestion();
    }
}
