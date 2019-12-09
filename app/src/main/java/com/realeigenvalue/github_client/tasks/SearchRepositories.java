package com.realeigenvalue.github_client.tasks;

import android.os.AsyncTask;
import android.util.Base64;

import com.realeigenvalue.github_client.Repository;
import com.realeigenvalue.github_client.fragments.RepositoryAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Async task to get the repositories for search results, with authentication
 */
public class SearchRepositories extends AsyncTask<Void, Void, Void> {
    private JSONArray json;
    private ArrayList<Repository> repositories;
    private String URL;
    private String credential;
    private RepositoryAdapter listViewAdapter;

    public SearchRepositories(ArrayList<Repository> repositories, String URL, String credential, RepositoryAdapter listViewAdapter) {
        this.repositories = repositories;
        if(this.repositories != null) {
            this.repositories.clear();
        }
        this.URL = URL;
        this.credential = credential;
        this.listViewAdapter = listViewAdapter;
    }

    @Override
    protected Void doInBackground(Void... params) {
        try {
            URL url = new URL(this.URL);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            String authentication = "Basic " + (Base64.encodeToString(credential.getBytes(), Base64.NO_WRAP));
            httpURLConnection.setRequestProperty("Authorization", authentication);
            InputStream stream = httpURLConnection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
            StringBuilder builder = new StringBuilder();
            int character;
            while((character = reader.read()) != -1) {
                builder.append((char)character);
            }
            JSONObject search_json = new JSONObject(builder.toString());
            json = new JSONArray(search_json.getString("items"));
            for(int i = 0; i < json.length(); i++) {
                try {
                    JSONObject js = json.getJSONObject(i);
                    Repository repository = new Repository();
                    repository.setRepository_name(js.getString("name"));
                    JSONObject owner = (JSONObject) js.get("owner");
                    repository.setUsername(owner.getString("login"));
                    repository.setDescription(js.getString("description"));
                    repository.setUrl(js.getString("html_url"));
                    repositories.add(repository);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        this.listViewAdapter.notifyDataSetChanged();
    }
}