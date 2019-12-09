package com.realeigenvalue.github_client.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.realeigenvalue.github_client.tasks.FollowUser;
import com.realeigenvalue.github_client.MainActivity;
import com.realeigenvalue.github_client.R;
import com.realeigenvalue.github_client.tasks.StarRepository;
import com.realeigenvalue.github_client.tasks.UnfollowUser;
import com.realeigenvalue.github_client.tasks.UnstarRepository;

/**
 * Fragment for the edit page
 */
public class EditFragment extends Fragment {
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.edit_layout, container, false);
        final EditText user_follow = (EditText) view.findViewById(R.id.user_follow_txt);
        Button follow = (Button) view.findViewById(R.id.follow_btn);
        follow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new FollowUser(user_follow.getText().toString(), MainActivity.CREDENTIAL, MainActivity.FOLLOW_USER, MainActivity.following).execute();
            }
        });
        Button unfollow = (Button) view.findViewById(R.id.unfollow_btn);
        unfollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new UnfollowUser(user_follow.getText().toString(), MainActivity.CREDENTIAL, MainActivity.FOLLOW_USER, MainActivity.following).execute();
            }
        });
        final EditText user_star = (EditText) view.findViewById(R.id.user_star_txt);
        final EditText repo_star = (EditText) view.findViewById(R.id.repo_star_txt);
        Button star = (Button) view.findViewById(R.id.star_btn);
        star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new StarRepository(user_star.getText().toString(), repo_star.getText().toString(), MainActivity.CREDENTIAL, MainActivity.STAR_REPO, MainActivity.starred).execute();
            }
        });
        Button unstar = (Button) view.findViewById(R.id.unstar_btn);
        unstar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new UnstarRepository(user_star.getText().toString(), repo_star.getText().toString(), MainActivity.CREDENTIAL, MainActivity.STAR_REPO, MainActivity.starred).execute();
            }
        });
        return view;
    }
}
