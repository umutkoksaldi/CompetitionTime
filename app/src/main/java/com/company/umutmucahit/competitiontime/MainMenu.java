package com.company.umutmucahit.competitiontime;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * MainMenu - Sets the main menu of the game and initializes some variables form the title screen.
 * @author: Umut Mucahit Koksaldi
 * @version: 1.00 4/24/2015
 */

public class MainMenu extends ActionBarActivity implements View.OnClickListener{

    private Button game,ach,lead,opt,cred;
    SQLiteDatabase db;
    SQLiteDatabase leaderboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        // Sets the values and the achievements of the user from the database.
        db = openOrCreateDatabase("db", MODE_PRIVATE, null);
        TitleScreen.user.setValues(db);
        TitleScreen.user.setAchievements();

        // If the user is visiting for the first time, unlock the achievement
        if (TitleScreen.user.welcomeEarned.equals("0")) {
            TitleScreen.user.welcomeEarned = "1";
            TitleScreen.user.setAchievements();
            TitleScreen.user.coins += 20;
            TitleScreen.user.updateDB(db);
            show(TitleScreen.user.welcome.toString());
        }
        db.close();

        // Toast welcome maessage.
        show("Welcome: " + TitleScreen.user.getName());

        // init gui elements
        game = (Button) findViewById(R.id.newGameButton);
        ach = (Button) findViewById(R.id.achievementsButton);
        lead = (Button) findViewById(R.id.leaderboardsButton);
        opt = (Button) findViewById(R.id.optionsButton);
        cred = (Button) findViewById(R.id.creditsButton);

        game.setOnClickListener(this);
        ach.setOnClickListener(this);
        lead.setOnClickListener(this);
        opt.setOnClickListener(this);
        cred.setOnClickListener(this);


    }
    // Start new activity based on the button pressed.
    public void onClick (View v){
        if (v == game)
            startActivity(new Intent("com.company.umutmucahit.competitiontime.Tutorial"));
        else if (v == ach)
            startActivity(new Intent("com.company.umutmucahit.competitiontime.Achievements"));
        else if (v == lead)
            startActivity(new Intent("com.company.umutmucahit.competitiontime.Leaderboards"));
        else if (v == opt)
            startActivity(new Intent("com.company.umutmucahit.competitiontime.Options"));
        else if (v == cred)
            startActivity(new Intent("com.company.umutmucahit.competitiontime.Credits"));
    }

    // Show notification to the user if they want to quit.
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                    }
                })
                .setNegativeButton("No", null)
                .show();
                if (isFinishing())
                    TitleScreen.player.pause();
                else if (TitleScreen.music)
                    TitleScreen.player.start();
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
    public void show(String str)
    {
        Toast toast = Toast.makeText(this, str, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.TOP,0,0);
        toast.show();
    }
}
