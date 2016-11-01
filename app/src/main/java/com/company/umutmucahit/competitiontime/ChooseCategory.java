package com.company.umutmucahit.competitiontime;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Scanner;

/**
 * ChooseCategory Activity - Allows the user to choose the category of the questions
 *
 * @author: Umut Mucahit Koksaldi
 * @version: 1.00 4/24/2015
 */

public class ChooseCategory extends ActionBarActivity implements View.OnClickListener {

    SQLiteDatabase db;
    SQLiteDatabase leaderboard;
    Button geo,hist,bil,gen,prog,sports,math;
    static int skip;
    static int count;
    ImageButton geolock, histlock, genlock, mathlock, proglock, sportslock;
    TextView geocoin, histcoin, gencoin, mathcoin, progcoin, sportscoin, usercoin;
    ImageView geoicon, histicon, genicon, mathicon, progicon, sportsicon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_category);

        // Initialize the buttons from the xml file.
        geo = (Button) findViewById(R.id.geo);
        hist = (Button) findViewById(R.id.hist);
        bil = (Button) findViewById(R.id.bil);
        gen = (Button) findViewById(R.id.gen);
        prog = (Button) findViewById(R.id.prog);
        sports = (Button) findViewById(R.id.sport);
        math = (Button) findViewById(R.id.math);

        geo.setOnClickListener(this);
        hist.setOnClickListener(this);
        bil.setOnClickListener(this);
        gen.setOnClickListener(this);
        prog.setOnClickListener(this);
        sports.setOnClickListener(this);
        math.setOnClickListener(this);
    }

    // Method is called when a button is pressed.
    @Override
    public void onClick(View v) {

        // Checks which button was pressed and initializes the id accordingly.
        // Also sets at which round the user was when they last played.
        if (v == sports) {
            TitleScreen.id = R.raw.sports;
            skip = TitleScreen.user.sportscount / 10;
        }
        else if (v == gen) {
            TitleScreen.id = R.raw.general;
            skip = TitleScreen.user.gencount / 10;
        }
        else if (v == bil) {
            TitleScreen.id = R.raw.bilkent;
            skip = TitleScreen.user.bilcount / 10;
        }
        else if (v == prog) {
            TitleScreen.id = R.raw.programming;
            skip = TitleScreen.user.progcount / 10;
        }
        else if (v == math) {
            TitleScreen.id = R.raw.math;
            skip = TitleScreen.user.mathcount / 10;
        }
        else if (v == geo) {
            TitleScreen.id = R.raw.geography;
            skip = TitleScreen.user.geocount / 10;
        }
        else if (v == hist) {
            TitleScreen.id = R.raw.history;
            skip = TitleScreen.user.histcount / 10;
        }
        // Set the question count
        count = (skip * 10) + 1;

        // Initialize the bonus variables to false.
        TitleScreen.x2 = false;
        TitleScreen.skip = false;
        TitleScreen.fifty = false;

        // Set the x2 bonus answered counter to 0.
        TitleScreen.x2count = 0;

        // Start an input stream according to the id that was set before.
        TitleScreen.qs = getResources().openRawResource(TitleScreen.id);

        // Put the inputStream into a scanner.
        TitleScreen.scan = new Scanner(TitleScreen.qs);

        // Initialize the category finished boolean value to false.
        TitleScreen.isFinished = false;

        // Skip to the user's round when they last played the game.
        for (int i = 0; i < skip * 60 && TitleScreen.scan.hasNext(); i++)
            TitleScreen.scan.nextLine();

        // Set the score to 0.
        TitleScreen.score = 0;

        // If the scanner file has no next line, then that means the activity is finished, start try again activity.
        if (!TitleScreen.scan.hasNext()) {
            startActivity(new Intent("com.company.umutmucahit.competitiontime.TryAgain"));
            TitleScreen.isFinished = true;
        }
        // Otherwise start the game.
        else
            startActivity(new Intent("com.company.umutmucahit.competitiontime.Game"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_choose_category, menu);
        return true;
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
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            openOptionsMenu();
        }

        return super.onOptionsItemSelected(item);
    }

    public void show (String str)
    {
        Toast toast = Toast.makeText(this, str, Toast.LENGTH_LONG);
        toast.show();
    }
}
