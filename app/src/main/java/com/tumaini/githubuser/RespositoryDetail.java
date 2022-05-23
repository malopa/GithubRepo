package com.tumaini.githubuser;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.tumaini.githubuser.config.GithubApi;
import com.tumaini.githubuser.config.ServiceGenerator;
import com.tumaini.githubuser.models.Repositories;
import com.tumaini.githubuser.models.Users;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RespositoryDetail extends AppCompatActivity implements View.OnClickListener {

    private CircleImageView avator;
    private TextView name;
    private TextView author,created_at,updated_at,language,forks,watcher,repoInfo,owner,
    full_name,public_repo,follower,company,blog;
    private LinearLayout repo;
    private String repo_url,user_url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_respository_detail);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_chevron_left_24);
        actionBar.setDisplayHomeAsUpEnabled(true);

        author = findViewById(R.id.author);
        created_at = findViewById(R.id.created_at);
        updated_at = findViewById(R.id.updated_at);
        language = findViewById(R.id.language);
        name = findViewById(R.id.name);
        avator = findViewById(R.id.avatar);
        repo = findViewById(R.id.repo);
        forks = findViewById(R.id.forks);
        watcher = findViewById(R.id.watcher);
        owner = findViewById(R.id.owner);
        repoInfo = findViewById(R.id.repoInfo);

        full_name =findViewById(R.id.full_name);
        public_repo = findViewById(R.id.public_repos);
        follower = findViewById(R.id.followers);
        company = findViewById(R.id.company);
        blog = findViewById(R.id.blog);


        owner.setOnClickListener(this);
        repoInfo.setOnClickListener(this);

        String user_info = getIntent().getStringExtra("id");
        user_url = getIntent().getStringExtra("user_url");
        repo_url = getIntent().getStringExtra("repo_url");
        String repo = getIntent().getStringExtra("repo");
        String user = getIntent().getStringExtra("user");


        getUserInfo(user_info);
        getSupportActionBar().setTitle(repo);

        getRepositoryData(repo,user);

    }

    private void getUserInfo(String user_info) {


        GithubApi client = ServiceGenerator.createService(GithubApi.class);
        Call<Users> call = client.getUserInfo(user_info);

        call.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {

                if(response.isSuccessful()){
                    Users user = response.body();

                    full_name.setText("Name : "+user.getName());
                    company.setText("Company: "+user.getCompany());
                    blog.setText("Blog : "+user.getBlog());
                    public_repo.setText("Public Repo: "+user.getPublic_repos());
                    follower.setText("Followers: "+user.getFollowers());

                }
            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });


    }

    private void getRepositoryData(String repo,String user) {


        GithubApi client = ServiceGenerator.createService(GithubApi.class);
        Call<Repositories> call = client.getRepositoryDetails(user,repo);

        call.enqueue(new Callback<Repositories>() {
            @Override
            public void onResponse(Call<Repositories> call, Response<Repositories> response) {

                if(response.isSuccessful()){
                    Repositories repositories = response.body();

                    name.setText("Repository: "+repositories.getName());
                    author.setText("Author: "+repositories.getOwner().getLogin());
                    created_at.setText("Created At: "+repositories.getCreated_at());
                    updated_at.setText("Updated At: "+repositories.getUpdated_at());
                    language.setText("Language: "+repositories.getLanguage());
                    watcher.setText("Watcher: "+repositories.getWatchers());
                    forks.setText("Forks: "+repositories.getForks());
                    Glide.with(getApplicationContext()).load(repositories.getOwner().getAvatar_url()).into(avator);

                }
            }

            @Override
            public void onFailure(Call<Repositories> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.repoInfo:
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(repo_url));
                startActivity(browserIntent);
                break;

            case R.id.owner:
                Intent userBrowserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(user_url));
                startActivity(userBrowserIntent);
                break;
        }
    }
}
