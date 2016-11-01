package com.company.umutmucahit.competitiontime;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.io.InputStream;

/**
 * Leaderboards activity - Lets the user view the leaderboard.
 * Created by UmutMucahit on 5/10/2015.
 */

public class Leaderboards extends ActionBarActivity {

    TextView leaderboards, userPart;
    SQLiteDatabase db;
    SQLiteDatabase leaderboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboards);

        // Init gui elements.
        leaderboards = (TextView) findViewById(R.id.leaderboards);
        userPart = (TextView) findViewById(R.id.userPart);

        // Set the leaderboard table.
        leaderboards.setText(TitleScreen.data.getLeaderboard());

        boolean isDone = false;
        String result = "";

        // Find the first occurence of the current user in the list and display them separately.
        for (int i = 0; i < TitleScreen.data.leaderboard.size() && !isDone; i++)
        {
            if (TitleScreen.data.leaderboard.get(i).getName().equals(TitleScreen.user.getName()))
            {
                isDone = true;
                result = TitleScreen.data.leaderboard.get(i).toString();
            }
        }

        userPart.setText(result);

    }

    // Stop and start the music player on pause and resume.
    @Override
    protected void onPause() {
        super.onPause();
        finish();
        TitleScreen.player.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (TitleScreen.music)
            TitleScreen.player.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_leaderboards, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
