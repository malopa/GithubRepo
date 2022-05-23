package com.tumaini.githubuser.models;

import com.google.gson.annotations.SerializedName;

public class Users {

    @SerializedName("login")
    private String login;

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("company")
    private String company;

    @SerializedName("location")
    private String location;

    @SerializedName("public_repos")
    private int public_repos;

    @SerializedName("followers")
    private int followers;

    @SerializedName("blog")
    private String blog;

    @SerializedName("node_id")
    private String node_id;

    @SerializedName("avatar_url")
    private String avatar_url;

    @SerializedName("url")
    private String url;

    @SerializedName("html_url")
    private String html_url;

    @SerializedName("followers_url")
    private String followers_url;

    @SerializedName("following_url")
    private String following_url;

    @SerializedName("gists_url")
    private String gists_url;

    @SerializedName("starred_url")
    private String starred_url;

    @SerializedName("subscriptions_url")
    private String subscriptions_url;

    @SerializedName("organizations_url")
    private String organizations_url;

    @SerializedName("repos_url")
    private  String repos_url;

    @SerializedName("type")
    private String type;

    @SerializedName("site_admin")
    private Boolean site_admin;


    public Users(String login, int id, String name, String company, String location, int public_repos, int followers, String blog, String node_id, String avatar_url, String url, String html_url, String followers_url, String following_url, String gists_url, String starred_url, String subscriptions_url, String organizations_url, String repos_url, String type, Boolean site_admin) {
        this.login = login;
        this.id = id;
        this.name = name;
        this.company = company;
        this.location = location;
        this.public_repos = public_repos;
        this.followers = followers;
        this.blog = blog;
        this.node_id = node_id;
        this.avatar_url = avatar_url;
        this.url = url;
        this.html_url = html_url;
        this.followers_url = followers_url;
        this.following_url = following_url;
        this.gists_url = gists_url;
        this.starred_url = starred_url;
        this.subscriptions_url = subscriptions_url;
        this.organizations_url = organizations_url;
        this.repos_url = repos_url;
        this.type = type;
        this.site_admin = site_admin;
    }

    public String getLogin() {
        return login;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCompany() {
        return company;
    }

    public String getLocation() {
        return location;
    }

    public int getPublic_repos() {
        return public_repos;
    }

    public int getFollowers() {
        return followers;
    }

    public String getBlog() {
        return blog;
    }

    public String getNode_id() {
        return node_id;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public String getUrl() {
        return url;
    }

    public String getHtml_url() {
        return html_url;
    }

    public String getFollowers_url() {
        return followers_url;
    }

    public String getFollowing_url() {
        return following_url;
    }

    public String getGists_url() {
        return gists_url;
    }

    public String getStarred_url() {
        return starred_url;
    }

    public String getSubscriptions_url() {
        return subscriptions_url;
    }

    public String getOrganizations_url() {
        return organizations_url;
    }

    public String getRepos_url() {
        return repos_url;
    }

    public String getType() {
        return type;
    }

    public Boolean getSite_admin() {
        return site_admin;
    }
}
