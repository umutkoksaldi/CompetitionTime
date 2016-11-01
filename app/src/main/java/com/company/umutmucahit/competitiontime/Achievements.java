package com.company.umutmucahit.competitiontime;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ScrollView;
import android.widget.TextView;

/**
 * Achievements - Activity that displays the list of achievements that the user has or has not unlocked.
 * Created by UmutMucahit on 5/10/2015.
 */

public class Achievements extends ActionBarActivity {

    ScrollView view;
    TextView achiev;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achievements);

        // Initialize textviews from the xml file and set the text to the achievements of the user.
        achiev = (TextView) findViewById(R.id.achievements);
        achiev.setText(TitleScreen.user.retrieveAchievements());
    }

    // Pauses the music and finished the acitivity when called.
    @Override
    protected void onPause() {
        super.onPause();
        finish();
        TitleScreen.player.pause();
    }

    // If the music is on, resumes the music.
    @Override
    protected void onResume() {
        super.onResume();
        achiev.setText(TitleScreen.user.retrieveAchievements());
        if (TitleScreen.music)
            TitleScreen.player.start();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_achievements, menu);
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
