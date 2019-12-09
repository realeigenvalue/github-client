package com.realeigenvalue.github_client.tasks;

import android.graphics.drawable.Drawable;
import android.os.AsyncTask;

import com.realeigenvalue.github_client.User;
import com.realeigenvalue.github_client.fragments.UserAdapter;

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
 * Async task to get the users for search results, with authentication
 */
public class SearchUsers extends AsyncTask<Void, Void, Void> {
    private JSONArray json;
    private ArrayList<User> following;
    private String URL;
    private String credential;
    private UserAdapter listViewAdapter;

    public SearchUsers(ArrayList<User> following, String URL, String credential, UserAdapter listViewAdapter) {
        this.following = following;
        if(this.following != null) {
            this.following.clear();
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
                    User user = new User();
                    user.setUsername(js.getString("login"));
                    String imageURL = js.getString("avatar_url");
                    user.setProfile_pic(Drawable.createFromStream((InputStream)(new URL(imageURL).getContent()), "profile_pic"));
                    user.setURL(js.getString("html_url"));
                    following.add(user);
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
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