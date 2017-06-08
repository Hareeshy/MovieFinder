package com.example.harish.moviesassignment.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.harish.moviesassignment.Models.Movie;
import com.example.harish.moviesassignment.Network.NetworkManager;
import com.example.harish.moviesassignment.R;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Harish Reddy on 2/5/2017.
 */

public class MovielistFragment extends Fragment{

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    Adapter adapter;
    NetworkManager networkManager;
    List<String> movieList;
    String searchMname;
    private boolean dualPane;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        searchMname = getArguments().getString("movieName");
        Log.e("SearchTitle","Title: "+searchMname);
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.movielist_fragment,container,false);
        networkManager = new NetworkManager();
        fetchMovieList();
        if (view.findViewById(R.id.detailLayout)!=null){
            dualPane=true;
        }
        recyclerView= (RecyclerView) view.findViewById(R.id.recyclerView);
        layoutManager=new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(layoutManager);
        return view;
    }

    private void fetchMovieList() {
        Call<Movie> moviesList = networkManager.getmServices().getMovieList(searchMname);
        moviesList.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                if (response.isSuccessful()){
                    Log.e("onResponse","Success: "+response.body()+" Count: ");
                    if (response.body().getSearch()==null){
                        Toast.makeText(getActivity(),"Dude! Don't be lazy, type a word!!",Toast.LENGTH_SHORT).show();
                    }else {
                        adapter = new Adapter(getActivity(),dualPane,response.body().getSearch());
                        recyclerView.setAdapter(adapter);

                    }
                }else {
                    Log.e("onResponse","Custom error code: "+response.code());
                }
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
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
}


