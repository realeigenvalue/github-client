package com.realeigenvalue.github_client.fragments;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.realeigenvalue.github_client.R;
import com.realeigenvalue.github_client.User;

import java.util.List;

/**
 * List view adapter for the following page and followers page
 */
public class UserAdapter extends ArrayAdapter<User> {
    private static class ViewHolder {
        ImageView avatar;
        TextView username;
    }

    public UserAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<User> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        User user = getItem(position);
        ViewHolder viewHolder;
        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.user_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.avatar = (ImageView) convertView.findViewById(R.id.avatar);
            viewHolder.username = (TextView) convertView.findViewById(R.id.username);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.avatar.setImageDrawable(user.getProfile_pic());
        viewHolder.username.setText(user.getUsername());
        return convertView;
    }
}
