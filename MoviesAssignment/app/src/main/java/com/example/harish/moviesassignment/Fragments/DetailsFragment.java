package com.example.harish.moviesassignment.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.harish.moviesassignment.Models.MovieDetails;
import com.example.harish.moviesassignment.Network.NetworkManager;
import com.example.harish.moviesassignment.R;
import com.squareup.picasso.Picasso;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Harish Reddy on 2/5/2017.
 */

public class DetailsFragment extends Fragment {


    TextView titleTv,yearTv,actorsTv,directorTv,movieGenreTV,movieRuntimeTV,moviePlotTV;
    ImageView imageView;
    String imdbID;
    NetworkManager networkManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        imdbID=getArguments().getString("Id");
        networkManager = new NetworkManager();
        fetchMovieDetails();
    }

    private void fetchMovieDetails() {
        Call<MovieDetails> movieDetails = networkManager.getmServices().getMovieInfo(imdbID);
        movieDetails.enqueue(new Callback<MovieDetails>() {
            @Override
            public void onResponse(Call<MovieDetails> call, Response<MovieDetails> response) {
                if (response.isSuccessful()){
                    Log.e("onResponse","Success: "+response.body());
                    titleTv.setText("Title: "+ response.body().getTitle());
                    yearTv.setText("Year: "+ response.body().getYear());
                    actorsTv.setText("Actor(s): "+ response.body().getActors());
                    directorTv.setText("Director: "+ response.body().getDirector());
                    movieGenreTV.setText("Genre: "+ response.body().getGenre());
                    movieRuntimeTV.setText("Runtime: "+ response.body().getRuntime());
                    moviePlotTV.setText("Plot: "+ response.body().getPlot());
                    Picasso.with(getActivity()).load(response.body().getPoster()).fit().into(imageView);
                }
                else {
                    Log.e("onResponse","Custom error code: "+response.code());
                }
            }

            @Override
            public void onFailure(Call<MovieDetails> call, Throwable t) {
                if (t instanceof UnknownHostException) {
                    Log.e("onFailure", "No Network" + t.getMessage());
                } else if (t instanceof SocketTimeoutException) {
                    Log.e("onResponse", "TimeoutException" + t.getMessage());
                } else {
                    Log.e("onResponse", "Error" + t.getMessage());
                }
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.details_fragment,container,false);

        titleTv = (TextView) view.findViewById(R.id.titleTV);
        yearTv = (TextView) view.findViewById(R.id.yearTV);
        actorsTv = (TextView) view.findViewById(R.id.actorsTV);
        actorsTv.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        directorTv = (TextView) view.findViewById(R.id.directorTV);
        movieGenreTV = (TextView) view.findViewById(R.id.genreTV);
        movieRuntimeTV = (TextView) view.findViewById(R.id.runtimeTV);
        moviePlotTV = (TextView) view.findViewById(R.id.plotTV);
        imageView = (ImageView) view.findViewById(R.id.imageView);

        return view;
    }

}