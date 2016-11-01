package com.company.umutmucahit.competitiontime;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.io.BufferedReader;
import java.io.InputStream;
import java.util.Scanner;


/**
 * TitleScreen Activity - Displays the name and the icon of the app. Also holds all of the static variables
 * that are used throughout the code. All boolean, integer counts and the user objects are created here.
 * Also accesses the leaderboard through the database and initializes it.
 * Some variables could have been distributed to other activities,
 * but this was done in order to preserve consistency and prevent confusion.
 *
 * @author: Umut Mucahit Koksaldi
 * @version: 1.00 4/24/2015
 */

public class TitleScreen extends ActionBarActivity {

    static MediaPlayer player; // music player
    static boolean music = true; // music is on or off
    static boolean effects = true;
    static int score; // score of the player, used in the game activity
    static boolean fifty, skip, x2; // boolean variables for the bonuses that are used, used in game activity
    static int id, x2count; // x2 count for the x2 bonus, id to determine which category was chosen in choosecategory activity
    static InputStream qs; // inputstream which is opened in choosecategory from the id
    static Scanner scan; // scanner that is initialized in choosecategroy from the inputstream
    static boolean isFinished; // boolean variable to check if the category is finished, used in choosecategory and game actvity
    static Player user; // the user that is playing the game
    static LeaderboardData data; // leaderboards data instance for the leaderboards activity.
    SQLiteDatabase lead;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize and start the media player
        player = MediaPlayer.create(this, R.raw.song);
        player.setLooping(true);
        player.start();

        // Initialize the leaderboards from the database and close it.
        lead = openOrCreateDatabase("leaderboards", MODE_PRIVATE, null);
        lead.execSQL("create table if not exists person(name varchar,score varchar)");
        data = new LeaderboardData();
        data.setLeaderboard(lead);
        lead.close();

        // Countdown timer which waits 1.5 seconds before moving on to the login activity.
        // Finishing the current activity.
        CountDownTimer timer = new CountDownTimer(1500, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
            }

            @Override
            public void onFinish() {
                startActivity(new Intent("com.company.umutmucahit.competitiontime.Login"));
                finish();
            }
        };
        timer.start();
    }

    // Stop and start the music player on pause and resume.
    @Override
    protected void onPause() {
        super.onPause();
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
        getMenuInflater().inflate(R.menu.menu_main_menu, menu);
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

