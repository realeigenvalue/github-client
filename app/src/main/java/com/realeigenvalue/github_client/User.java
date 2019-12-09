package com.realeigenvalue.github_client;

import android.graphics.drawable.Drawable;

/**
 * Class to hold user data
 */
public class User {
    private String username;
    private Drawable profile_pic;
    private String URL;

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Drawable getProfile_pic() {
        return profile_pic;
    }

    public void setProfile_pic(Drawable profile_pic) {
        this.profile_pic = profile_pic;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }
}
