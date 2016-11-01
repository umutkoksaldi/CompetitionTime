package com.company.umutmucahit.competitiontime;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

/**
 * TryAgain - Is shown after the user is done playing the game. Updates the leaderboard and the variables of the user.
 * Created by UmutMucahit on 5/10/2015.
 */

public class TryAgain extends ActionBarActivity implements View.OnClickListener {

    ImageButton check, no;
    TextView tryagain;
    SQLiteDatabase db;
    SQLiteDatabase leaderboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_try_again);
        // Initialize the GUI elements.
        check = (ImageButton) findViewById(R.id.checkButton);
        no = (ImageButton) findViewById(R.id.noButton);
        tryagain = (TextView) findViewById(R.id.tryAgain);

        // Check if the category is finished or the user answered incorrectly, update the text accordingly.
        if (TitleScreen.isFinished) {
            tryagain.setText("Congratulations! You have finished the whole category! Do you want to pick another category?");
            // Check which category was finished, give the achievement accordingly, and update the database.
            if (TitleScreen.id == R.raw.bilkent) {
                if (TitleScreen.user.bilkentCompleted.equals("0")) {
                    TitleScreen.user.bilkentCompleted = "1";
                    TitleScreen.user.setAchievements();
                    TitleScreen.user.coins += 20;
                    show(TitleScreen.user.bilcomplete.toString());
                    db = openOrCreateDatabase("db", MODE_PRIVATE, null);
                    TitleScreen.user.updateDB(db);
                    db.close();
                }
            }
            if (TitleScreen.id == R.raw.geography)
            {
                if (TitleScreen.user.geographyCompleted.equals("0")) {
                    TitleScreen.user.geographyCompleted = "1";
                    TitleScreen.user.setAchievements();
                    TitleScreen.user.coins += 20;
                    show(TitleScreen.user.geocomplete.toString());
                    db = openOrCreateDatabase("db", MODE_PRIVATE, null);
                    TitleScreen.user.updateDB(db);
                    db.close();
                }
            }
            if (TitleScreen.id == R.raw.general)
            {
                if (TitleScreen.user.generalCompleted.equals("0")) {
                    TitleScreen.user.generalCompleted = "1";
                    TitleScreen.user.setAchievements();
                    TitleScreen.user.coins += 20;
                    show(TitleScreen.user.gencomplete.toString());
                    db = openOrCreateDatabase("db", MODE_PRIVATE, null);
                    TitleScreen.user.updateDB(db);
                    db.close();
                }
            }
            if (TitleScreen.id == R.raw.programming)
            {
                if (TitleScreen.user.mathCompleted.equals("0")) {
                    TitleScreen.user.mathCompleted = "1";
                    TitleScreen.user.setAchievements();
                    TitleScreen.user.coins += 20;
                    show(TitleScreen.user.mathcomplete.toString());
                    db = openOrCreateDatabase("db", MODE_PRIVATE, null);
                    TitleScreen.user.updateDB(db);
                    db.close();
                }
            }
            if (TitleScreen.id == R.raw.math)
            {
                if (TitleScreen.user.mathCompleted.equals("0")) {
                    TitleScreen.user.mathCompleted = "1";
                    TitleScreen.user.setAchievements();
                    TitleScreen.user.coins += 20;
                    show(TitleScreen.user.mathcomplete.toString());
                    db = openOrCreateDatabase("db", MODE_PRIVATE, null);
                    TitleScreen.user.updateDB(db);
                    db.close();
                }
            }
            if (TitleScreen.id == R.raw.history)
            {
                if (TitleScreen.user.historyCompleted.equals("0")) {
                    TitleScreen.user.historyCompleted = "1";
                    TitleScreen.user.setAchievements();
                    TitleScreen.user.coins += 20;
                    show(TitleScreen.user.histcomplete.toString());
                    db = openOrCreateDatabase("db", MODE_PRIVATE, null);
                    TitleScreen.user.updateDB(db);
                    db.close();
                }
            }
            if (TitleScreen.id == R.raw.sports)
            {
                if (TitleScreen.user.sportsCompleted.equals("0")) {
                    TitleScreen.user.sportsCompleted = "1";
                    TitleScreen.user.setAchievements();
                    TitleScreen.user.coins += 20;
                    show(TitleScreen.user.sportscomplete.toString());
                    db = openOrCreateDatabase("db", MODE_PRIVATE, null);
                    TitleScreen.user.updateDB(db);
                    db.close();
                }
            }

        }
        // If the user failed, set the text.
        else
            tryagain.setText("You lost! You answered incorrectly! Do you want to pick another category?");

        // Update the leaderboard.
        db = openOrCreateDatabase("leaderboards", MODE_PRIVATE, null);
        TitleScreen.data.updateLeaderboard(TitleScreen.user.getName(),TitleScreen.score, db);
        db.close();

        check.setOnClickListener(this);
        no.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // Start choosecategory if the user wants to play again, main menu if they dont.
        if (v == check)
            startActivity(new Intent("com.company.umutmucahit.competitiontime.ChooseCategory"));
        else if (v == no)
            startActivity(new Intent("com.company.umutmucahit.competitiontime.MainMenu"));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
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
        getMenuInflater().inflate(R.menu.menu_try_again, menu);
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

    public void show (String str) {
        Toast toast = Toast.makeText(this, str, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.TOP,0,0);
        toast.show();
    }
}
