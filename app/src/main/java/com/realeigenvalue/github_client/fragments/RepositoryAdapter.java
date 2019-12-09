package com.realeigenvalue.github_client.fragments;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.realeigenvalue.github_client.R;
import com.realeigenvalue.github_client.Repository;

import java.util.List;

/**
 * List view adapter for the repository page
 */
public class RepositoryAdapter extends ArrayAdapter<Repository> {
    private static class ViewHolder {
        TextView repository_name;
        TextView username;
        TextView description;
    }

    public RepositoryAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Repository> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Repository repository = getItem(position);
        ViewHolder viewHolder;
        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.repo_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.repository_name = (TextView) convertView.findViewById(R.id.repository_name);
            viewHolder.username = (TextView) convertView.findViewById(R.id.username);
            viewHolder.description = (TextView) convertView.findViewById(R.id.description);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.repository_name.setText("Repository: " + repository.getRepository_name());
        viewHolder.username.setText("Username: " + repository.getUsername());
        viewHolder.description.setText("Description: " + repository.getDescription());
        return convertView;
    }
}
