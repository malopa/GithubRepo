package com.tumaini.githubuser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.tumaini.githubuser.adapters.UserAdapter;
import com.tumaini.githubuser.config.GithubApi;
import com.tumaini.githubuser.config.ServiceGenerator;
import com.tumaini.githubuser.models.Repositories;
import com.tumaini.githubuser.models.RespositoryAdapter;
import com.tumaini.githubuser.models.Users;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("Repositories");

        recyclerView = findViewById(R.id.recyclerview);
        searchView = findViewById(R.id.search);
        searchView.onActionViewExpanded();
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if(list.contains(query)){
                    adapter.getFilter().filter(query);
                }else{
                    Toast.makeText(MainActivity.this, "No Match found",Toast.LENGTH_LONG).show();
                }
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });

        getRepositories();
    }

    private void getRepositories() {
        GithubApi client = ServiceGenerator.createService(GithubApi.class);
        Call<List<Repositories>> call = client.getRepositories();

        call.enqueue(new Callback<List<Repositories>>() {
            @Override
            public void onResponse(Call<List<Repositories>> call, Response<List<Repositories>> response) {
                if (response.isSuccessful()){
                    ArrayList<Repositories> Repositories = (ArrayList<Repositories>) response.body();

                    RespositoryAdapter repositoryAdapter = new RespositoryAdapter(MainActivity.this,Repositories);
                    recyclerView.setAdapter(repositoryAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Repositories>> call, Throwable t) {

            }
        });

    }

    private void getUsers() {

        GithubApi client = ServiceGenerator.createService(GithubApi.class);
        Call<List<Users>> call = client.getGithuUsers();

        call.enqueue(new Callback<List<Users>>() {
            @Override
            public void onResponse(Call<List<Users>> call, Response<List<Users>> response) {
                if (response.isSuccessful()){
                    ArrayList<Users> users = (ArrayList<Users>) response.body();

                    UserAdapter userAdapter = new UserAdapter(MainActivity.this,users);
                    recyclerView.setAdapter(userAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Users>> call, Throwable t) {

            }
        });
    }
}