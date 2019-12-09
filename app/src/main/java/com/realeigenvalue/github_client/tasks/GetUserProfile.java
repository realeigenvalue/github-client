package com.realeigenvalue.github_client.tasks;

import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Base64;

import com.realeigenvalue.github_client.MainActivity;
import com.realeigenvalue.github_client.Profile;
import com.realeigenvalue.github_client.fragments.ProfUserFragment;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Async task to get the user profile from the list-view, with authentication
 */
public class GetUserProfile extends AsyncTask<Void, Void, Void> {
    private JSONObject json;
    private Profile profile;
    private String URL;
    private String credential;

    public GetUserProfile(Profile profile, String URL, String credential) {
        this.profile = profile;
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
            JSONTokener tokener = new JSONTokener(builder.toString());
            json = new JSONObject(tokener);
            String imageURL = json.getString("avatar_url");
            profile.setProfile_pic(Drawable.createFromStream((InputStream)(new URL(imageURL).getContent()), "profile_pic"));
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
        try {
            profile.setName(json.getString("name"));
            profile.setUsername(json.getString("login"));
            profile.setPublic_repos(json.getString("public_repos"));
            profile.setFollowers_count(json.getString("followers"));
            profile.setFollowing_count(json.getString("following"));
            profile.setCreated_date(json.getString("created_at"));

            MainActivity.profile = this.profile;
            MainActivity.ACTIVITY_FRAGMENT_MANAGER.beginTransaction().replace(com.realeigenvalue.github_client.R.id.main_layout, new ProfUserFragment()).commit();

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}