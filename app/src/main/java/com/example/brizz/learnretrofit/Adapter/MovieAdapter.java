package com.example.brizz.learnretrofit.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.brizz.learnretrofit.Model.Movie;
import com.example.brizz.learnretrofit.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by brizz on 11/1/17.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder>{

    private List<Movie> movies;
    private Context context;

    public MovieAdapter(List<Movie> movies, Context context) {
        this.movies = movies;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_movie_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

//        holder.ivPoster.setImageResource(Integer.parseInt(movies.get(position).getPosterPath()));
        holder.tvTitleMovie.setText(movies.get(position).getTitle());
        Picasso.with(context).load("https://image.tmdb.org/t/p/w300_and_h450_bestv2" + movies.get(position).getBackdropPath()).into(holder.ivPoster);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivPoster;
        TextView tvTitleMovie;
        public ViewHolder(View itemView) {
            super(itemView);
            ivPoster = itemView.findViewById(R.id.iv_Movie);
            tvTitleMovie = itemView.findViewById(R.id.tv_TitleMovie);
        }
    }
}
