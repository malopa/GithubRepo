package com.tumaini.githubuser.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.tumaini.githubuser.MainActivity;
import com.tumaini.githubuser.R;
import com.tumaini.githubuser.models.Users;


import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder>{

    ArrayList<Users> users;
    private Context context;
    public UserAdapter(MainActivity mainActivity, ArrayList<Users> users) {
        this.users = users;
        context = mainActivity;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_layout,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.name.setText(""+users.get(position).getLogin());

        Glide.with(context).load(users.get(position).getAvatar_url()).into(holder.avator);
    }

    @Override
    public int getItemCount() {
        return this.users.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView avator;
        private TextView name;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            avator = itemView.findViewById(R.id.avatar);
        }
    }
}
