package com.example.brizz.learnretrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    private ImageView ivDetailPoster;
    private TextView tvDetailTitle, tvDetailDate, tvDetailDesc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvDetailTitle = findViewById(R.id.tv_detail_title);
        tvDetailDate = findViewById(R.id.tv_detail_date);
        tvDetailDesc = findViewById(R.id.tv_detail_desc);

        tvDetailTitle.setText(getIntent().getStringExtra("title"));
        tvDetailDate.setText(getIntent().getStringExtra("date"));
        tvDetailDesc.setText(getIntent().getStringExtra("desc"));

        ivDetailPoster = findViewById(R.id.iv_detail_poster);
        Picasso.with(this)
                .load("https://image.tmdb.org/t/p/w300_and_h450_bestv2" + getIntent().getStringExtra("bg"))
                .resize(400,600)
                .into(ivDetailPoster);
    }
}
