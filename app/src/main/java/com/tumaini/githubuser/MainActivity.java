package com.tumaini.githubuser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Toast;

import com.tumaini.githubuser.adapters.UserAdapter;
import com.tumaini.githubuser.config.GithubApi;
import com.tumaini.githubuser.config.ServiceGenerator;
import com.tumaini.githubuser.models.Repositories;
import com.tumaini.githubuser.models.RespositoryAdapter;
import com.tumaini.githubuser.models.SearchFeedback;
import com.tumaini.githubuser.models.Users;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {


    private RecyclerView recyclerView;
    private SearchView searchView;
    private LinearLayout sort;

    private RadioButton star,fork,update;

    private String searchWord;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("Github Repositories");

        recyclerView = findViewById(R.id.recyclerview);
        searchView = findViewById(R.id.search);
        sort = findViewById(R.id.sort);
        star = findViewById(R.id.stars);
        fork = findViewById(R.id.forks);
        update = findViewById(R.id.updated);
        sort.setVisibility(View.GONE);


        star.setOnCheckedChangeListener(this);
        fork.setOnCheckedChangeListener(this);
        update.setOnCheckedChangeListener(this);

        searchView.onActionViewExpanded();
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchWord = query;
                searchRepositories(query);
                return true;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        getRepositories();
    }

    private void searchRepositories(String searchWord) {

        GithubApi client = ServiceGenerator.createService(GithubApi.class);
        Call<SearchFeedback> call = client.searchRepositories(searchWord);

        call.enqueue(new Callback<SearchFeedback>() {
            @Override
            public void onResponse(Call<SearchFeedback> call, Response<SearchFeedback> response) {
                if (response.isSuccessful()){
                    SearchFeedback repositories =  response.body();
                        if(repositories.getTotal_count() > 0){
                            sort.setVisibility(View.VISIBLE);
                        }
                    RespositoryAdapter repositoryAdapter = new RespositoryAdapter(MainActivity.this,repositories.getItems());
                    recyclerView.setAdapter(repositoryAdapter);
                }
            }

            @Override
            public void onFailure(Call<SearchFeedback> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });


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

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        if(!searchWord.equals("")){

            switch(buttonView.getId()){
                case R.id.stars:
                    sortRepositories(searchWord,"stars");
                    break;

                case R.id.forks:
                    sortRepositories(searchWord,"forks");
                    break;

                case R.id.updated:
                    sortRepositories(searchWord,"updated");
                    break;
            }
        }else{
            sort.setVisibility(View.GONE);
            Toast.makeText(getApplicationContext(),"Please search first to sort",Toast.LENGTH_LONG).show();
        }

    }

    private void  sortRepositories(String searchWord,String sortType){
        GithubApi client = ServiceGenerator.createService(GithubApi.class);
        Call<SearchFeedback> call = client.sortSearchRepositories(searchWord,sortType);

        call.enqueue(new Callback<SearchFeedback>() {
            @Override
            public void onResponse(Call<SearchFeedback> call, Response<SearchFeedback> response) {
                if (response.isSuccessful()){
                    SearchFeedback repositories =  response.body();
                    if(repositories.getTotal_count() > 0){
                        sort.setVisibility(View.VISIBLE);
                    }
                    RespositoryAdapter repositoryAdapter = new RespositoryAdapter(MainActivity.this,repositories.getItems());
                    recyclerView.setAdapter(repositoryAdapter);
                }
            }

            @Override
            public void onFailure(Call<SearchFeedback> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });


    }

}