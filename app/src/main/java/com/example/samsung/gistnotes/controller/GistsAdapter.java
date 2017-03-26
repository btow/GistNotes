package com.example.samsung.gistnotes.controller;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.samsung.gistnotes.R;
import com.example.samsung.gistnotes.model.Gists;

/**
 * Created by btow on 26.03.2017.
 */

class GistsAdapter extends RecyclerView.Adapter<GistsAdapter.MyViewHolder> {

    private Gists gists;

    public GistsAdapter(Gists gists) {
        this.gists = gists;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_gists, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tvDescription.setText(gists.getGithubPublicList().get(position).getDescription());
        holder.tvUser.setText(gists.getGithubPublicList().get(position).getUser().toString());
    }

    @Override
    public int getItemCount() {
        return gists.getGithubPublicList().size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView tvDescription, tvUser;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvDescription = (TextView) itemView.findViewById(R.id.tvDescription);
            tvUser = (TextView) itemView.findViewById(R.id.tvUser);
        }
    }
}
