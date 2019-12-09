package com.realeigenvalue.github_client.tasks;

import android.os.AsyncTask;
import android.util.Base64;

import com.realeigenvalue.github_client.MainActivity;
import com.realeigenvalue.github_client.Repository;
import com.realeigenvalue.github_client.tasks.GetRepositories;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Async task to star a repository, with authentication
 */
public class StarRepository extends AsyncTask<Void, Void, Void> {
    private String user;
    private String repository;
    private String credential;
    private String URL;
    private ArrayList<Repository> starred;

    public StarRepository(String user, String repository, String credential, String URL, ArrayList<Repository> starred) {
        this.user = user;
        this.repository = repository;
        this.credential = credential;
        this.URL = URL;
        this.starred = starred;
    }

    @Override
    protected Void doInBackground(Void... params) {
        try {
            java.net.URL url = new URL(this.URL + "/" + this.user + "/" + this.repository);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("PUT");
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
        new GetRepositories(starred, MainActivity.STARRED, MainActivity.CREDENTIAL).execute();
    }
}