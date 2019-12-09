package com.realeigenvalue.github_client.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.realeigenvalue.github_client.tasks.GetRepositoryStats;
import com.realeigenvalue.github_client.MainActivity;
import com.realeigenvalue.github_client.R;

import java.util.ArrayList;

/**
 * Fragment for the visual page
 */
public class VisualFragment extends Fragment {
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.visual_layout, container, false);
        final EditText owner = (EditText) view.findViewById(R.id.vs_owner_txt);
        final EditText repo = (EditText) view.findViewById(R.id.vs_repo_txt);
        Button submit = (Button) view.findViewById(R.id.vs_submit_btn);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stats_url = "https://api.github.com/repos/" + owner.getText().toString() + "/" + repo.getText().toString() + "/stats/participation";
                ArrayList<Integer> results = new ArrayList<Integer>();
                new GetRepositoryStats(results, stats_url, MainActivity.CREDENTIAL, view).execute();
            }
        });
        return view;
    }
}
