package com.tumaini.githubuser.models;

import com.google.gson.annotations.SerializedName;

public class Repositories {

    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("repos_url")
    private String repos_url;

    @SerializedName("full_name")
    private String full_name;

    @SerializedName("url")
    private String url;

    @SerializedName("blobs_url")
    private String blobs_url;

    @SerializedName("created_at")
    private String created_at;

    @SerializedName("updated_at")
    private String updated_at;

    @SerializedName("stargazers_count")
    private int stargazers_count;

    @SerializedName("fork")
    private Boolean fork;

    @SerializedName("forks_url")
    private String forks_url;

    @SerializedName("forks_count")
    private int forks_count;

    @SerializedName("owner")
    private Users owner;


    @SerializedName("forks")
    private int forks;

    @SerializedName("language")
    private String language;

    @SerializedName("watchers")
    private int watchers;

    @SerializedName("open_issues")
    private int open_issues;


    public Repositories(String id, String name, String repos_url, String full_name, String url, String blobs_url, String created_at, String updated_at, int stargazers_count, Boolean fork, String forks_url, int forks_count, Users owner, int forks, String language, int watchers, int open_issues) {
        this.id = id;
        this.name = name;
        this.repos_url = repos_url;
        this.full_name = full_name;
        this.url = url;
        this.blobs_url = blobs_url;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.stargazers_count = stargazers_count;
        this.fork = fork;
        this.forks_url = forks_url;
        this.forks_count = forks_count;
        this.owner = owner;
        this.forks = forks;
        this.language = language;
        this.watchers = watchers;
        this.open_issues = open_issues;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRepos_url() {
        return repos_url;
    }

    public String getFull_name() {
        return full_name;
    }

    public String getUrl() {
        return url;
    }

    public String getBlobs_url() {
        return blobs_url;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public int getStargazers_count() {
        return stargazers_count;
    }

    public Boolean getFork() {
        return fork;
    }

    public String getForks_url() {
        return forks_url;
    }

    public int getForks_count() {
        return forks_count;
    }

    public Users getOwner() {
        return owner;
    }

    public int getForks() {
        return forks;
    }

    public String getLanguage() {
        return language;
    }

    public int getWatchers() {
        return watchers;
    }

    public int getOpen_issues() {
        return open_issues;
    }
}


