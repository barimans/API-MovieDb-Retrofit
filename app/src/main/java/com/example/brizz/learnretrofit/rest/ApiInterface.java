package com.example.brizz.learnretrofit.rest;

import com.example.brizz.learnretrofit.Model.MovieResponse;
import com.example.brizz.learnretrofit.Model.PostRate;
import com.example.brizz.learnretrofit.Model.ResponsePostRate;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by brizz on 11/1/17.
 */

public interface ApiInterface {
    @GET("movie/popular")
    Call<MovieResponse> getPopularMovie(@Query("api_key") String apiKey);

    @GET("movie/{id}")
    Call<MovieResponse> getMovieDetails(@Path("id") int id, @Query("api_key") String apiKey);

    @Headers("Content-Type: application/json;charset=utf-8")
    @POST("movie/{movie_id}/rating")
    Call<ResponsePostRate> postRateMovie(@Path("movie_id") int movieId,
                                         @Query("api_key") String apiKey,
                                         @Query("guest_session_id") String sessionId,
                                         @Body PostRate postRate);
}
