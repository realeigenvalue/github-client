package com.realeigenvalue.github_client.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.realeigenvalue.github_client.MainActivity;

/**
 * Fragment for the profile page for the user from the list-view
 */
public class ProfUserFragment extends Fragment {
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(com.realeigenvalue.github_client.R.layout.prof_layout, container, false);
        ImageView profile_pic = (ImageView) view.findViewById(com.realeigenvalue.github_client.R.id.avatar);
        profile_pic.setImageDrawable(MainActivity.profile.getProfile_pic());
        TextView name = (TextView) view.findViewById(com.realeigenvalue.github_client.R.id.name);
        TextView username = (TextView) view.findViewById(com.realeigenvalue.github_client.R.id.username);
        Button repo_count = (Button) view.findViewById(com.realeigenvalue.github_client.R.id.repo_count);
        Button followers_count = (Button) view.findViewById(com.realeigenvalue.github_client.R.id.followers_count);
        Button following_count = (Button) view.findViewById(com.realeigenvalue.github_client.R.id.following_count);
        TextView created_date = (TextView) view.findViewById(com.realeigenvalue.github_client.R.id.create_date);
        name.append(MainActivity.profile.getName());
        username.append(MainActivity.profile.getUsername());
        repo_count.append(MainActivity.profile.getPublic_repos());
        followers_count.append(MainActivity.profile.getFollowers_count());
        following_count.append(MainActivity.profile.getFollowing_count());
        created_date.append(MainActivity.profile.getCreated_date());
        if(MainActivity.profile != MainActivity.DEFAULT) {
            MainActivity.profile = MainActivity.DEFAULT;
        }
        return view;
    }
}
