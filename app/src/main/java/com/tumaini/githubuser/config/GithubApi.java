package com.tumaini.githubuser.config;

import com.tumaini.githubuser.models.Repositories;
import com.tumaini.githubuser.models.SearchFeedback;
import com.tumaini.githubuser.models.Users;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GithubApi {

    @Headers("User-Agent:malopa")
    @GET("users")
    Call<List<Users>> getGithuUsers();


    @Headers("User-Agent:malopa")
    @GET("users/{username}/repos")
    Call<List<Repositories>> getUserRepositories(@Path("username") String username);

    @Headers({"Accept: application/vnd.github.v3+json",
            "Authorization: Basic bWFsb3Bh"})
    @GET("repositories")
    Call<List<Repositories>> getRepositories();


    @Headers({"Accept: application/vnd.github.v3+json",
            "Authorization: Basic bWFsb3Bh"})
    @GET("repos/{user}/{repo}")
    Call<Repositories> getRepositoryDetails(@Path("user") String user,@Path("repo") String repo);

    @Headers({"Accept: application/vnd.github.v3+json",
            "Authorization: Basic bWFsb3Bh"})
    @GET("search/repositories")
    Call<SearchFeedback> searchRepositories(@Query("q") String query);
}
