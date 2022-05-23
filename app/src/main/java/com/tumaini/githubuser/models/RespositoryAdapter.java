package com.tumaini.githubuser.models;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.tumaini.githubuser.MainActivity;
import com.tumaini.githubuser.R;
import com.tumaini.githubuser.RespositoryDetail;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class RespositoryAdapter extends RecyclerView.Adapter<RespositoryAdapter.MyViewHolder>{

    private ArrayList<Repositories> repositories;
    private Context  context;
    public RespositoryAdapter(MainActivity mainActivity, ArrayList<Repositories> repositories) {
        this.repositories = repositories;
        context = mainActivity;
    }

    @NonNull
    @Override
    public RespositoryAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_layout,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RespositoryAdapter.MyViewHolder holder, int position) {


        holder.name.setText("Repo Name: "+repositories.get(position).getName());
//        holder.name.setText("Forks Count "+repositories.get(position).getForks_count());
        holder.author.setText("Author:  "+repositories.get(position).getOwner().getLogin());
        holder.watcher.setText("Watches: "+repositories.get(position).getWatchers());
        holder.forks.setText("Forks: "+repositories.get(position).getForks());
        holder.issue.setText("Issues: "+repositories.get(position).getOpen_issues());
        Glide.with(context).load(repositories.get(position).getOwner().getAvatar_url()).into(holder.avator);


        holder.owner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String URL = "https://api.github.com/"+repo_url;
//                Toast.makeText(context,URL,Toast.LENGTH_LONG).show();
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(repositories.get(position).getOwner().getUrl()));
                context.startActivity(browserIntent);
            }
        });

        holder.repo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, RespositoryDetail.class);
                intent.putExtra("id",repositories.get(position).getFull_name());
                intent.putExtra("repo",repositories.get(position).getName());
                intent.putExtra("user",repositories.get(position).getOwner().getLogin());
                intent.putExtra("user_url",repositories.get(position).getOwner().getUrl());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return this.repositories.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private CircleImageView avator;
        private TextView name;
        private TextView author,watcher,forks,issue,owner;
        private LinearLayout repo;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            author = itemView.findViewById(R.id.author);
            issue = itemView.findViewById(R.id.issue);
            watcher = itemView.findViewById(R.id.watcher);
            forks = itemView.findViewById(R.id.forks);
            name = itemView.findViewById(R.id.name);
            avator = itemView.findViewById(R.id.avatar);
            repo = itemView.findViewById(R.id.repo);
            owner = itemView.findViewById(R.id.owner);

        }
    }
}
