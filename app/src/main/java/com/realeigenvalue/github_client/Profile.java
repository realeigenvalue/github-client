package com.realeigenvalue.github_client;

import android.graphics.drawable.Drawable;

/**
 * Class to hold profile data
 */
public class Profile {
    private String name;
    private String username;
    private String public_repos;
    private String followers_count;
    private String following_count;
    private String created_date;
    private Drawable profile_pic;

    public Profile() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPublic_repos() {
        return public_repos;
    }

    public void setPublic_repos(String public_repos) {
        this.public_repos = public_repos;
    }

    public String getFollowers_count() {
        return followers_count;
    }

    public void setFollowers_count(String followers_count) {
        this.followers_count = followers_count;
    }

    public String getFollowing_count() {
        return following_count;
    }

    public void setFollowing_count(String following_count) {
        this.following_count = following_count;
    }

    public String getCreated_date() {
        return created_date;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }

    public Drawable getProfile_pic() {
        return profile_pic;
    }

    public void setProfile_pic(Drawable profile_pic) {
        this.profile_pic = profile_pic;
    }
}
