package com.tumaini.githubuser.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class SearchFeedback {
    @SerializedName("total_count")
    private int total_count;

    @SerializedName("incomplete_results")
    private Boolean incomplete_results;

    @SerializedName("items")
    private ArrayList<Repositories> items;

    public SearchFeedback(int total_count, Boolean incomplete_results, ArrayList<Repositories> items) {
        this.total_count = total_count;
        this.incomplete_results = incomplete_results;
        this.items = items;
    }

    public int getTotal_count() {
        return total_count;
    }

    public Boolean getIncomplete_results() {
        return incomplete_results;
    }

    public ArrayList<Repositories> getItems() {
        return items;
    }
}
