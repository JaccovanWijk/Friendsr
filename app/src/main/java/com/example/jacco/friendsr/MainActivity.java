package com.example.jacco.friendsr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ArrayList<Friend> friends = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Friend arya = new Friend("Arya", "Arya's bio", R.drawable.arya);
        Friend cersei = new Friend("Cersei", "Cercei's bio", R.drawable.cersei);
        Friend daenerys = new Friend("Daenerys", "Daenerys bio", R.drawable.daenerys);
        Friend jaime = new Friend("Jaime", "Jaimes bio", R.drawable.jaime);
        Friend jon = new Friend("Jon", "Jons bio", R.drawable.jon);
        Friend jorah = new Friend("Jorah", "Jorahs bio", R.drawable.jorah);
        Friend margaery = new Friend("Margaery", "Margaery's bio", R.drawable.margaery);
        Friend melisandre = new Friend("Melisandre", "Melisandres bio", R.drawable.melisandre);
        Friend sansa = new Friend("Sansa", "Sansa's bio", R.drawable.sansa);
        Friend tyrion = new Friend("Tyrion", "Tyrions bio", R.drawable.tyrion);

        friends.addAll(Arrays.asList(arya,cersei,daenerys,jaime,jon,jorah,margaery,melisandre,sansa,tyrion));

        FriendsAdapter adapter = new FriendsAdapter(this, R.layout.grid_item, friends);
        GridView gv = (GridView) findViewById(R.id.GridView);
        gv.setAdapter(adapter);

        gv.setOnItemClickListener(new GridItemClickListener());
    }

    private class GridItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Friend clickedFriend = (Friend) adapterView.getItemAtPosition(i);

            Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
            intent.putExtra("clicked_friend", clickedFriend);
            startActivity(intent);
        }
    }
}
