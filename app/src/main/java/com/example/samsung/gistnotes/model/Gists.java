package com.example.samsung.gistnotes.model;

import java.util.List;

/**
 * Created by btow on 26.03.2017.
 */

public class Gists {

    private int total_count;
    private boolean incomplete_ressults;

    private List<GithubPublic> githubPublicList;

    public int getTotal_count() {
        return total_count;
    }

    public void setTotal_count(int total_count) {
        this.total_count = total_count;
    }

    public boolean isIncomplete_ressults() {
        return incomplete_ressults;
    }

    public void setIncomplete_ressults(boolean incomplete_ressults) {
        this.incomplete_ressults = incomplete_ressults;
    }

    public List<GithubPublic> getGithubPublicList() {
        return githubPublicList;
    }

    public void setGithubPublicList(List<GithubPublic> githubPublicList) {
        this.githubPublicList = githubPublicList;
    }
}
