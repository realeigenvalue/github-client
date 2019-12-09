package com.realeigenvalue.github_client.tasks;

import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Base64;

import com.realeigenvalue.github_client.User;

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
 * Async task to get the followers, with authentication
 */
public class GetFollowers extends AsyncTask<Void, Void, Void> {
    private JSONArray json;
    private ArrayList<User> followers;
    private String URL;
    private String credential;

    public GetFollowers(ArrayList<User> followers, String URL, String credential) {
        this.followers = followers;
        if(this.followers != null) {
            this.followers.clear();
        }
        this.URL = URL;
        this.credential = credential;
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
            json = new JSONArray(builder.toString());
            for(int i = 0; i < json.length(); i++) {
                try {
                    JSONObject js = json.getJSONObject(i);
                    User user = new User();
                    user.setUsername(js.getString("login"));
                    String imageURL = js.getString("avatar_url");
                    user.setProfile_pic(Drawable.createFromStream((InputStream)(new URL(imageURL).getContent()), "profile_pic"));
                    user.setURL(js.getString("url"));
                    followers.add(user);
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
    }
}