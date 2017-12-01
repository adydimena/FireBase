package com.example.ady.firebase;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ady on 11/14/2017.
 */

public class Recycleadapter extends RecyclerView.Adapter<Recycleadapter.ViewHolder> {
    List<Movie> list = new ArrayList<>();



    public Recycleadapter(List<Movie> list) {

        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycleviewlayout,null);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (list.get(position) != null);
        holder.movieName.setText("Movie Name is: " + list.get(position).getName());
        holder.movieYear.setText("Moviie Year is: "+list.get(position).getYear());
        holder.movieDirector.setText("The Boss of the movie is: "+list.get(position).getDirector());


    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView movieName;
        private final TextView movieDirector;
        private final TextView movieYear;
        public ViewHolder(View itemView) {
            super(itemView);
            movieName = itemView.findViewById(R.id.name);
            movieDirector = itemView.findViewById(R.id.Director);
            movieYear = itemView.findViewById(R.id.Year);
        }
    }

}
