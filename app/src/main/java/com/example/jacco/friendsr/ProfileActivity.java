package com.example.jacco.friendsr;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    Friend retrievedFriend;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // load in data from when it's called
        Intent intent = getIntent();
        retrievedFriend = (Friend) intent.getSerializableExtra("clicked_friend");

        // find info from loaded friend
        int aPhoto = retrievedFriend.getDrawableId();
        String aName = retrievedFriend.getName();
        String aBio = retrievedFriend.getBio();
        float aRating = retrievedFriend.getRating();

        // find photo from id and set photo
        ImageView photo = (ImageView) findViewById(R.id.photo);
        photo.setImageResource(aPhoto);

        // find name from id and set name
        TextView name = (TextView) findViewById(R.id.name);
        name.setText(aName);

        // find bio from id and set bio
        TextView bio = (TextView) findViewById(R.id.bio);
        bio.setText(aBio);

        // find rating from id and set rating
        RatingBar ratingBar = (RatingBar) findViewById(R.id.rating);
        ratingBar.setOnRatingBarChangeListener(new onRatingBarChangeListener());

        // save rating in sharedPrefs
        SharedPreferences prefs = getSharedPreferences(aName, MODE_PRIVATE);
        Float aStoredFloat = prefs.getFloat(aName, 0);
        if (aStoredFloat != 0) {
            aRating = aStoredFloat;
            ratingBar.setRating(aRating);
        }
    }

    private class onRatingBarChangeListener implements RatingBar.OnRatingBarChangeListener{
        @Override
        public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
            String aName = retrievedFriend.getName();
            SharedPreferences prefs = getSharedPreferences(aName, MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();

            editor.putFloat(aName, v);
            editor.apply();

            retrievedFriend.setRating(v);
        }
    }
}
