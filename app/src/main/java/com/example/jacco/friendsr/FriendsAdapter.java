package com.example.jacco.friendsr;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jacco on 23-2-2018.
 */

public class FriendsAdapter extends ArrayAdapter<Friend> {
    private ArrayList friends;
    public FriendsAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Friend> objects) {
        super(context, resource, objects);
        friends = (ArrayList<Friend>) objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.grid_item, parent, false);
        }
        // find the right photo and name to set in main screen
        ImageView photo = (ImageView) convertView.findViewById(R.id.photo);
        TextView name = (TextView) convertView.findViewById(R.id.name);
        Friend f = (Friend) friends.get(position);
        name.setText(f.getName());
        int id = f.getDrawableId();
        photo.setImageDrawable(getContext().getResources().getDrawable(id, null));

        // find screenheight and screenwidth and set size pictures in main screen
        int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
        int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
        int screen;
        if (screenHeight > screenWidth) {
            screen = screenHeight/3;
        } else {
            screen = screenWidth/3;
        }
        convertView.setLayoutParams(new GridView.LayoutParams(GridView.AUTO_FIT, screen));


        return convertView;
    }
}
