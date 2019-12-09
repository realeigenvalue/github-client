package com.realeigenvalue.github_client.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.realeigenvalue.github_client.tasks.GetUserProfile;
import com.realeigenvalue.github_client.MainActivity;
import com.realeigenvalue.github_client.Profile;
import com.realeigenvalue.github_client.R;

/**
 * Fragment for the following page
 */
public class FollowingFragment extends Fragment {
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(com.realeigenvalue.github_client.R.layout.following_layout, container, false);
        ListView listView = (ListView) view.findViewById(R.id.user_list);
        UserAdapter userAdapter = new UserAdapter(MainActivity.CONTEXT, 0, MainActivity.following);
        listView.setAdapter(userAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String URL = MainActivity.following.get(position).getURL();
                Profile profile = new Profile();
                GetUserProfile userProfile_request = new GetUserProfile(profile, URL, MainActivity.CREDENTIAL);
                userProfile_request.execute();
            }
        });
        return view;
    }
}
