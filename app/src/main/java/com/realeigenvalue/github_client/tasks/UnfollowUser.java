package com.realeigenvalue.github_client.tasks;

import android.os.AsyncTask;
import android.util.Base64;

import com.realeigenvalue.github_client.MainActivity;
import com.realeigenvalue.github_client.User;
import com.realeigenvalue.github_client.tasks.GetFollowing;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Async task to un-follow a user, with authentication
 */
public class UnfollowUser extends AsyncTask<Void, Void, Void> {
    private String user;
    private String credential;
    private String URL;
    private ArrayList<User> following;

    public UnfollowUser(String user, String credential, String URL, ArrayList<User> following) {
        this.user = user;
        this.credential = credential;
        this.URL = URL;
        this.following = following;
    }

    @Override
    protected Void doInBackground(Void... params) {
        try {
            java.net.URL url = new URL(this.URL + "/" + this.user);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("DELETE");
            httpURLConnection.setDoOutput(true);
            String authentication = "Basic " + (Base64.encodeToString(credential.getBytes(), Base64.NO_WRAP));
            httpURLConnection.setRequestProperty("Authorization", authentication);
            httpURLConnection.setRequestProperty("Content-Length", "0");
            httpURLConnection.getInputStream();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        new GetFollowing(following, MainActivity.FOLLOWING, MainActivity.CREDENTIAL).execute();
    }
}