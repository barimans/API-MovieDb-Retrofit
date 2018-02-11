package com.example.brizz.learnretrofit;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Adapter;

import com.example.brizz.learnretrofit.Adapter.MovieAdapter;
import com.example.brizz.learnretrofit.Model.Movie;
import com.example.brizz.learnretrofit.Model.MovieResponse;
import com.example.brizz.learnretrofit.rest.ApiClient;
import com.example.brizz.learnretrofit.rest.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDbActivity extends AppCompatActivity {

    private final static String API_KEY ="4f56a8bdeba80a38532595760839bbd6";

    private List<Movie> movies = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_db);

        final RecyclerView recyclerView = findViewById(R.id.recView);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<MovieResponse> call = apiService.getPopularMovie(API_KEY);
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                movies = response.body().getResults();
                recyclerView.setAdapter(new MovieAdapter(movies, getApplicationContext()));
//                Log.d("Movie Size", "Jumlah Data " + movies.size());

                recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
                    GestureDetector gestureDetector = new GestureDetector(getApplicationContext(), new GestureDetector.SimpleOnGestureListener(){
                        @Override
                        public boolean onSingleTapUp(MotionEvent e) {
                            return true;
                        }
                    });
                    @Override
                    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                        View child = rv.findChildViewUnder(e.getX(), e.getY());
                        if (child != null && gestureDetector.onTouchEvent(e)) {
                            int position = rv.getChildAdapterPosition(child);
                            Intent intent = new Intent(MovieDbActivity.this, DetailActivity.class);
                            intent.putExtra("title",movies.get(position).getTitle());
                            intent.putExtra("date", movies.get(position).getReleaseDate());
                            intent.putExtra("desc", movies.get(position).getOverview());
                            intent.putExtra("bg",movies.get(position).getPosterPath());
                            MovieDbActivity.this.startActivity(intent);
                        }
                        return false;
                    }

                    @Override
                    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

                    }

                    @Override
                    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

                    }
                });
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {

            }
        });

    }
}
