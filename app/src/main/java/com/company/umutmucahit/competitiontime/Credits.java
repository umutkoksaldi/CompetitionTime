package com.company.umutmucahit.competitiontime;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

/**
 * Credits- Lists the developers of the game.
 * Created by UmutMucahit on 5/10/2015.
 */

public class Credits extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);

        // If the user is visiting this page for the first time, unlock the achievment and update the database.
        if (TitleScreen.user.creditsVisited.equals("0")) {
            TitleScreen.user.creditsVisited = "1";
            TitleScreen.user.setAchievements();
            TitleScreen.user.coins += 20;
            show(TitleScreen.user.credits.toString());
            SQLiteDatabase db = openOrCreateDatabase("db", MODE_PRIVATE, null);
            TitleScreen.user.updateDB(db);
            db.close();
        }
    }

    // Stop and start the music player on pause and resume.
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }


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
        getMenuInflater().inflate(R.menu.menu_credits, menu);
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
