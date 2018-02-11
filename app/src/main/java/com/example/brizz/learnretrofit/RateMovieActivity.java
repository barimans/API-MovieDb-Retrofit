package com.example.brizz.learnretrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;

import com.example.brizz.learnretrofit.Model.PostRate;
import com.example.brizz.learnretrofit.Model.ResponsePostRate;
import com.example.brizz.learnretrofit.rest.ApiClient;
import com.example.brizz.learnretrofit.rest.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RateMovieActivity extends AppCompatActivity {

    private Button btnRate;
    private RatingBar ratingBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_movie);

        ratingBar = findViewById(R.id.rate_movie);
        btnRate = findViewById(R.id.btn_rate);

        btnRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float rating = ratingBar.getRating();
                postRate(rating);
            }
        });
    }

    private void postRate(float rate){
//        Log.d("Rating","Jumlah rating" + rate);

        PostRate postRate = new PostRate(rate);

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

        Call<ResponsePostRate> call = apiInterface
                .postRateMovie(284053,"4f56a8bdeba80a38532595760839bbd6",
                                "2eb2a4227763100e28e2e55964a2a7bb",
                                postRate);

        call.enqueue(new Callback<ResponsePostRate>() {
            @Override
            public void onResponse(Call<ResponsePostRate> call, Response<ResponsePostRate> response) {
                Log.d("Status Post Rating","Status " + response.body().getStatus_message());
            }

            @Override
            public void onFailure(Call<ResponsePostRate> call, Throwable t) {

            }
        });
    }
}
