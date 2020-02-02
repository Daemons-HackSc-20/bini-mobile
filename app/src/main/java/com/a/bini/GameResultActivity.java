package com.a.bini;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class GameResultActivity extends AppCompatActivity {
    private ImageView mImageViewResultObject;
    private ImageView mImageViewResultBox;
    private ImageView mImageViewRetry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_result);

        mImageViewResultObject = findViewById(R.id.img_result_object);
        mImageViewResultBox = findViewById(R.id.img_result_box);
        mImageViewRetry = findViewById(R.id.img_retry);

        Intent intent = getIntent();
        Boolean result = intent.getBooleanExtra("RESULT", true);
        String imageUri = intent.getStringExtra("IMAGE_URI");

        setResultBox(result, imageUri);
        setOnclickRetry();
    }

    private void setResultBox(boolean result, String imageUri) {
        Picasso.get().load(imageUri).into(mImageViewResultObject);
        if (result) {
            mImageViewResultBox.setImageResource(R.mipmap.feedbackpagecorrect);
            mImageViewRetry.setImageResource(R.mipmap.greenretrybutton);
        } else {
            mImageViewResultBox.setImageResource(R.mipmap.xbox);
            mImageViewRetry.setImageResource(R.mipmap.retry_red);
        }
    }

    private void setOnclickRetry() {
        mImageViewRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), GameActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }


}
