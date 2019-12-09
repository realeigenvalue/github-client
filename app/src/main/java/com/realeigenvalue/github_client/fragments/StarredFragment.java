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
import android.widget.ListView;

import com.realeigenvalue.github_client.MainActivity;
import com.realeigenvalue.github_client.R;

/**
 * Fragment for the starred page
 */
public class StarredFragment extends Fragment {
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.starred_layout, container, false);
        ListView listView = (ListView) view.findViewById(R.id.starred_list);
        RepositoryAdapter listViewAdapater = new RepositoryAdapter(MainActivity.CONTEXT, 0, MainActivity.starred);
        listView.setAdapter(listViewAdapater);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String URL = MainActivity.starred.get(position).getUrl();
                Intent chrome_intent = new Intent(Intent.ACTION_VIEW, Uri.parse(URL));
                startActivity(chrome_intent);
            }
        });
        return view;
    }
}
