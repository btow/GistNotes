package com.example.samsung.gistnotes.model;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import com.example.samsung.gistnotes.R;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static com.example.samsung.gistnotes.controller.MainActivity.LOG_TAG;

public class GithubPublic {
    /**
     * url : https://api.github.com/gists/1eacccd58dcb7671451ca1b80926e5ac
     * forks_url : https://api.github.com/gists/1eacccd58dcb7671451ca1b80926e5ac/forks
     * commits_url : https://api.github.com/gists/1eacccd58dcb7671451ca1b80926e5ac/commits
     * id : 1eacccd58dcb7671451ca1b80926e5ac
     * git_pull_url : https://gist.github.com/1eacccd58dcb7671451ca1b80926e5ac.git
     * git_push_url : https://gist.github.com/1eacccd58dcb7671451ca1b80926e5ac.git
     * html_url : https://gist.github.com/1eacccd58dcb7671451ca1b80926e5ac
     * files : {"gistfile1.txt":{"filename":"gistfile1.txt","type":"text/plain","language":"Text","raw_url":"https://gist.githubusercontent.com/klaas/1eacccd58dcb7671451ca1b80926e5ac/raw/eb8834fe379ecb5892fa7212dceea9055a48cff2/gistfile1.txt","size":3173}}
     * public-gists.txt : true
     * created_at : 2017-03-26T11:34:15Z
     * updated_at : 2017-03-26T11:34:15Z
     * description : DirectoryMonitor - Swift 3
     * comments : 0
     * user : null
     * comments_url : https://api.github.com/gists/1eacccd58dcb7671451ca1b80926e5ac/comments
     * owner : {"login":"klaas","id":320967,"avatar_url":"https://avatars3.githubusercontent.com/u/320967?v=3","gravatar_id":"","url":"https://api.github.com/users/klaas","html_url":"https://github.com/klaas","followers_url":"https://api.github.com/users/klaas/followers","following_url":"https://api.github.com/users/klaas/following{/other_user}","gists_url":"https://api.github.com/users/klaas/gists{/gist_id}","starred_url":"https://api.github.com/users/klaas/starred{/owner}{/repo}","subscriptions_url":"https://api.github.com/users/klaas/subscriptions","organizations_url":"https://api.github.com/users/klaas/orgs","repos_url":"https://api.github.com/users/klaas/repos","events_url":"https://api.github.com/users/klaas/events{/privacy}","received_events_url":"https://api.github.com/users/klaas/received_events","type":"User","site_admin":false}
     * truncated : false
     */

    private String url;
    private String forks_url;
    private String commits_url;
    private String id;
    private String git_pull_url;
    private String git_push_url;
    private String html_url;
    private String files;
    //    private Files files;
    @SerializedName("public-gists")
    private boolean publicX;
    private String created_at;
    private String updated_at;
    private String description;
    private int comments;
    private Object user;
    private String comments_url;
    private Owner owner;
    private boolean truncated;

    public static GithubPublic objectFromData(String str) {

        return new Gson().fromJson(str, GithubPublic.class);
    }

    public static GithubPublic objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), GithubPublic.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<GithubPublic> arrayGithubPublicFromData(String str) {

        Type listType = new TypeToken<ArrayList<GithubPublic>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public static List<GithubPublic> arrayGithubPublicFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<GithubPublic>>() {
            }.getType();

            return new Gson().fromJson(jsonObject.getString(str), listType);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new ArrayList();


    }

    public static List<GithubPublic> readGists(Context context) {

        List<String> fieldsOfClass = new ArrayList();
        for (Field fieldOfClass : GithubPublic.class.getDeclaredFields()) {
            fieldsOfClass.add("\"" + fieldOfClass.getName().toString() + "\":");
        }

        GithubPublic githubPublic = null;
        List<GithubPublic> githubPublics = new LinkedList<>();
//        FileInputStream stream = null;
        AssetManager assetManager = context.getAssets();
        InputStream input;

        try {
//            stream = context.openFileInput("public-gists.txt");
            input = assetManager.open(context.getString(R.string.name_of_filq));
            String line = null, fieldValueString = null;
            int tag = 0;

            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(input, "UTF-8"));
                while ((line = reader.readLine()) != null) {

                    if (line.indexOf("{", 2) < 3) {
                        githubPublic = new GithubPublic();
                        continue;
                    } else if (line.indexOf("},", 2) < 4) {
                        githubPublics.add(githubPublic);
                    }

                    for (String fieldOfClass : fieldsOfClass) {

                        tag = line.indexOf(fieldOfClass, 4);
                        if (tag < 6) {

                            tag += (fieldOfClass.length() + 1);
                            if (fieldOfClass.equals("\"id\":")) {
                                githubPublic.setId(line.substring(tag, line.length() - 1));
                                break;
                            } else if (fieldOfClass.equals("\"description\":")) {
                                githubPublic.setDescription(line.substring(tag, line.length() - 1));
                                break;
                            } else if (fieldOfClass.equals("\"user\":")) {
                                githubPublic.setUser(line.substring(tag, line.length() - 1));
                                break;
                            }
                        }
                    }
                }
            } finally {
                input.close();
            }
            Log.d(LOG_TAG, context.getString(R.string.Data_from_file_dp) + context.getString(R.string.name_of_filq));

        } catch (Exception e) {
            Log.d(LOG_TAG, context.getString(R.string.file_does_not_exist_or_an_error_occurred_while_reading));
        }

        return githubPublics;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getForks_url() {
        return forks_url;
    }

    public void setForks_url(String forks_url) {
        this.forks_url = forks_url;
    }

    public String getCommits_url() {
        return commits_url;
    }

    public void setCommits_url(String commits_url) {
        this.commits_url = commits_url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGit_pull_url() {
        return git_pull_url;
    }

    public void setGit_pull_url(String git_pull_url) {
        this.git_pull_url = git_pull_url;
    }

    public String getGit_push_url() {
        return git_push_url;
    }

    public void setGit_push_url(String git_push_url) {
        this.git_push_url = git_push_url;
    }

    public String getHtml_url() {
        return html_url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }

    public String getFiles() {
        return files;
    }

    public void setFiles(String files) {
        this.files = files;
    }

    public boolean isPublicX() {
        return publicX;
    }

    public void setPublicX(boolean publicX) {
        this.publicX = publicX;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    public Object getUser() {
        return user;
    }

    public void setUser(Object user) {
        this.user = user;
    }

    public String getComments_url() {
        return comments_url;
    }

    public void setComments_url(String comments_url) {
        this.comments_url = comments_url;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public boolean isTruncated() {
        return truncated;
    }

    public void setTruncated(boolean truncated) {
        this.truncated = truncated;
    }

}
