package com.realeigenvalue.github_client.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.realeigenvalue.github_client.MainActivity;
import com.realeigenvalue.github_client.R;
import com.realeigenvalue.github_client.Repository;
import com.realeigenvalue.github_client.tasks.SearchRepositories;
import com.realeigenvalue.github_client.tasks.SearchUsers;
import com.realeigenvalue.github_client.User;

import java.util.ArrayList;

/**
 * Fragment for the search page
 */
public class SearchFragment extends Fragment {
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.search_layout, container, false);
        final EditText search_txt = (EditText) view.findViewById(R.id.search_txt);
        Button repo_stars = (Button) view.findViewById(R.id.repo_stars_btn);
        Button repo_commit = (Button) view.findViewById(R.id.repo_commit_btn);
        Button users_followers = (Button) view.findViewById(R.id.users_followers_btn);
        final ListView listView = (ListView) view.findViewById(R.id.search_list);
        repo_stars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String search_url = "https://api.github.com/search/repositories?q=" + search_txt.getText().toString() + "&sort=stars";
                final ArrayList<Repository> results = new ArrayList<Repository>();
                RepositoryAdapter listViewAdapater = new RepositoryAdapter(MainActivity.CONTEXT, 0, results);
                listView.setAdapter(listViewAdapater);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String URL = results.get(position).getUrl();
                        Intent chrome_intent = new Intent(Intent.ACTION_VIEW, Uri.parse(URL));
                        startActivity(chrome_intent);
                    }
                });
                new SearchRepositories(results, search_url, MainActivity.CREDENTIAL, listViewAdapater).execute();
            }
        });
        repo_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String search_url = "https://api.github.com/search/repositories?q=" + search_txt.getText().toString() + "&sort=updated";
                final ArrayList<Repository> results = new ArrayList<Repository>();
                RepositoryAdapter listViewAdapater = new RepositoryAdapter(MainActivity.CONTEXT, 0, results);
                listView.setAdapter(listViewAdapater);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String URL = results.get(position).getUrl();
                        Intent chrome_intent = new Intent(Intent.ACTION_VIEW, Uri.parse(URL));
                        startActivity(chrome_intent);
                    }
                });
                new SearchRepositories(results, search_url, MainActivity.CREDENTIAL, listViewAdapater).execute();
            }
        });
        users_followers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String search_url = "https://api.github.com/search/users?q=" + search_txt.getText().toString() + "&sort=followers";
                final ArrayList<User> results = new ArrayList<User>();
                UserAdapter listViewAdapater = new UserAdapter(MainActivity.CONTEXT, 0, results);
                listView.setAdapter(listViewAdapater);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String URL = results.get(position).getURL();
                        Intent chrome_intent = new Intent(Intent.ACTION_VIEW, Uri.parse(URL));
                        startActivity(chrome_intent);
                    }
                });
                new SearchUsers(results, search_url, MainActivity.CREDENTIAL, listViewAdapater).execute();
            }
        });
        return view;
    }
}
