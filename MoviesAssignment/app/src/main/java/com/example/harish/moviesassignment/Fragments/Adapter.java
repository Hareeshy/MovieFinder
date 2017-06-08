package com.example.harish.moviesassignment.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.harish.moviesassignment.Models.MovieList;
import com.example.harish.moviesassignment.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Harish Reddy on 2/5/2017.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>{

    int index=0;
    List<MovieList> movieList;
    Context context;
    Boolean dualPane=false;

    public Adapter(Context context, Boolean dualPane,List<MovieList> movieList) {
        this.context = context;
        this.dualPane = dualPane;
        this.movieList = movieList;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_item,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        viewHolder.holderIndex=index;
        index++;
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.movietitleTv.setText(movieList.get(position).getTitle());
        holder.yearTv.setText(movieList.get(position).getYear());
        final String imdbID = movieList.get(position).getImdbID();
        Picasso.with(context).load(movieList.get(position).getPoster()).fit().into(holder.imageView);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity= (AppCompatActivity) v.getContext();
                DetailsFragment detailsFragment=new DetailsFragment();

                Bundle bundle = new Bundle();
                bundle.putString("Id", imdbID);
                detailsFragment.setArguments(bundle);
                if (dualPane){
                    activity.getFragmentManager().beginTransaction().replace(R.id.detailLayout, detailsFragment).addToBackStack(null).commit();
                }else {
                    activity.getFragmentManager().beginTransaction().replace(R.id.activity_main, detailsFragment).addToBackStack(null).commit();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView movietitleTv;
        public TextView yearTv;
        public ImageView imageView;
        public int holderIndex;

        public ViewHolder(View itemView) {
            super(itemView);
            movietitleTv = (TextView) itemView.findViewById(R.id.titleTv);
            yearTv = (TextView) itemView.findViewById(R.id.yearTV);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);

        }
    }
}





