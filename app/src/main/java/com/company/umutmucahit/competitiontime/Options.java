package com.company.umutmucahit.competitiontime;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

/**
 * Options - Activity to turn sound effects and music on or off.
 * @author: Umut Mucahit Koksaldi
 * @version: 1.00 4/24/2015
 */

public class Options extends ActionBarActivity implements View.OnClickListener {

    static Switch music;
    static Switch sound;
    Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        // init gui elements
        music = (Switch) findViewById(R.id.musicSwitch);
        sound = (Switch) findViewById(R.id.soundSwitch);
        logout = (Button) findViewById(R.id.logout);

        logout.setOnClickListener(this);

        // for the music switch, turn the music on and off and set the control variable accordingly.
        music.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    TitleScreen.player.start();
                    TitleScreen.music = true;
                }
                else {
                    TitleScreen.player.pause();
                    TitleScreen.music = false;
                }
            }
        });

        sound.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {
                    TitleScreen.effects = true;
                }
                else
                    TitleScreen.effects = false;
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v == logout) {
            startActivity(new Intent("com.company.umutmucahit.competitiontime.Login"));
            finish();
        }
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
        if (TitleScreen.music) {
            music.setChecked(true);
            TitleScreen.player.start();
        }
        else
            music.setChecked(false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_options, menu);
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
