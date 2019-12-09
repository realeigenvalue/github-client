package com.realeigenvalue.github_client;

import android.app.FragmentManager;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.realeigenvalue.github_client.fragments.EditFragment;
import com.realeigenvalue.github_client.fragments.FollowersFragment;
import com.realeigenvalue.github_client.fragments.FollowingFragment;
import com.realeigenvalue.github_client.fragments.NotificationFragment;
import com.realeigenvalue.github_client.fragments.ProfFragment;
import com.realeigenvalue.github_client.fragments.RepoFragment;
import com.realeigenvalue.github_client.fragments.SearchFragment;
import com.realeigenvalue.github_client.fragments.StarredFragment;
import com.realeigenvalue.github_client.fragments.VisualFragment;
import com.realeigenvalue.github_client.tasks.GetFollowers;
import com.realeigenvalue.github_client.tasks.GetFollowing;
import com.realeigenvalue.github_client.tasks.GetProfile;
import com.realeigenvalue.github_client.tasks.GetRepositories;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    /**
     * URLs for the application to work with
     */
    public static String USER;
    public static String REPOS;
    public static String FOLLOWERS;
    public static String FOLLOWING;
    public static String STARRED;
    public static String FOLLOW_USER;
    public static String STAR_REPO;
    public static String USERNAME;
    public static String TOKEN;
    public static String CREDENTIAL;

    /**
     * Local data structures to hold application data
     */
    public static Profile profile = new Profile();
    public final static Profile DEFAULT = profile;
    public static ArrayList<Repository> repositories = new ArrayList<Repository>();
    public static ArrayList<User> followers = new ArrayList<User>();
    public static ArrayList<User> following = new ArrayList<User>();
    public static ArrayList<Repository> starred = new ArrayList<>();

    public static FragmentManager ACTIVITY_FRAGMENT_MANAGER;
    public static Context CONTEXT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.realeigenvalue.github_client.R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(com.realeigenvalue.github_client.R.id.toolbar);
        setSupportActionBar(toolbar);

/*      FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(com.realeigenvalue.github_client.R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, com.realeigenvalue.github_client.R.string.navigation_drawer_open, com.realeigenvalue.github_client.R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(com.realeigenvalue.github_client.R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ACTIVITY_FRAGMENT_MANAGER = getFragmentManager();
        CONTEXT = getApplicationContext();

        generateGitHubStrings();

        /**
         * Start Async tasks to get all of our data we need
         */
        new GetProfile(profile, MainActivity.USER, MainActivity.CREDENTIAL).execute();

        new GetRepositories(repositories, MainActivity.REPOS, MainActivity.CREDENTIAL).execute();

        new GetRepositories(starred, MainActivity.STARRED, MainActivity.CREDENTIAL).execute();

        new GetFollowers(followers, MainActivity.FOLLOWERS, MainActivity.CREDENTIAL).execute();

        new GetFollowing(following, MainActivity.FOLLOWING, MainActivity.CREDENTIAL).execute();
    }

    private void generateGitHubStrings() {
        String username = null;
        String token = null;
        InputStream file = null;
        try {
            file = getAssets().open("credentials.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(file));
            username = reader.readLine();
            token = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        USERNAME = username;
        TOKEN = token;
        USER = "https://api.github.com/users/" + USERNAME;
        REPOS = "https://api.github.com/users/" + USERNAME + "/repos";
        FOLLOWERS = "https://api.github.com/users/" + USERNAME + "/followers";
        FOLLOWING = "https://api.github.com/users/" + USERNAME + "/following";
        STARRED = "https://api.github.com/users/" + USERNAME + "/starred";
        FOLLOW_USER = "https://api.github.com/user/following";
        STAR_REPO = "https://api.github.com/user/starred";
        CREDENTIAL = USERNAME + ":" + TOKEN;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(com.realeigenvalue.github_client.R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(com.realeigenvalue.github_client.R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == com.realeigenvalue.github_client.R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        /**
         * Switch between each of our custom fragments for each page
         */
        if (id == com.realeigenvalue.github_client.R.id.prof_page) {
            getFragmentManager().beginTransaction().replace(com.realeigenvalue.github_client.R.id.main_layout, new ProfFragment()).commit();
        } else if (id == com.realeigenvalue.github_client.R.id.repo_page) {
            getFragmentManager().beginTransaction().replace(com.realeigenvalue.github_client.R.id.main_layout, new RepoFragment()).commit();
        } else if (id == com.realeigenvalue.github_client.R.id.following_page) {
            getFragmentManager().beginTransaction().replace(com.realeigenvalue.github_client.R.id.main_layout, new FollowingFragment()).commit();
        } else if (id == com.realeigenvalue.github_client.R.id.followers_page) {
            getFragmentManager().beginTransaction().replace(com.realeigenvalue.github_client.R.id.main_layout, new FollowersFragment()).commit();
        } else if (id == R.id.starred_page) {
            getFragmentManager().beginTransaction().replace(com.realeigenvalue.github_client.R.id.main_layout, new StarredFragment()).commit();
        } else if (id == R.id.edit_page) {
            getFragmentManager().beginTransaction().replace(com.realeigenvalue.github_client.R.id.main_layout, new EditFragment()).commit();
        } else if (id == R.id.search_page) {
            getFragmentManager().beginTransaction().replace(com.realeigenvalue.github_client.R.id.main_layout, new SearchFragment()).commit();
        } else if (id == R.id.visual_page) {
            getFragmentManager().beginTransaction().replace(com.realeigenvalue.github_client.R.id.main_layout, new VisualFragment()).commit();
        } else if (id == R.id.notification_page) {
            getFragmentManager().beginTransaction().replace(com.realeigenvalue.github_client.R.id.main_layout, new NotificationFragment()).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(com.realeigenvalue.github_client.R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onConfigurationChanged(Configuration configuration)
    {
        super.onConfigurationChanged(configuration);
    }
}