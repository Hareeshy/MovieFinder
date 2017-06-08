package com.example.harish.moviesassignment.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Harish Reddy on 2/5/2017.
 */

public class Movie {


    @Expose
    private List<MovieList> Search = null;

    @Expose
    private String totalResults;

    @Expose
    private String Response;

    public String getResponse() {
        return Response;
    }

    public void setResponse(String response) {
        Response = response;
    }

    public List<MovieList> getSearch() {
        return Search;
    }

    public void setSearch(List<MovieList> search) {
        Search = search;
    }

    public String getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(String totalResults) {
        this.totalResults = totalResults;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "Response='" + Response + '\'' +
                ", Search=" + Search +
                ", totalResults='" + totalResults + '\'' +
                '}';
    }
}
