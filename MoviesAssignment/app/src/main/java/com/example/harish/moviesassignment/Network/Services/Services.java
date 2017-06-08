package com.example.harish.moviesassignment.Network.Services;

import com.example.harish.moviesassignment.Models.Movie;
import com.example.harish.moviesassignment.Models.MovieDetails;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Harish Reddy on 2/5/2017.
 */

public interface Services {
    @GET("/")
    Call<Movie> getMovieList(@Query("s") String movieTitle);

    @GET("/")
    Call<MovieDetails> getMovieInfo(@Query("i") String imdbID);
}
